package TableGridPack.Controllers;

import ButtonCRUDPanel.Controllers.ButtonPanelController;
import InputFilterPack.Controllers.FilterPanelController;
import Main.UpdateDataInterface;
import TableGridPack.Navigator.Controllers.NavigatorController;
import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.TableParamsInterfaceSetter;
import TableGridPack.TableView;
import data.DataList;
import data.DataTable;
import org.example.student_db.StudentListDB;
import org.example.student_db.StudentListDBImpl;
import org.example.student_list.StudentListComponent;
import org.example.student_list.StudentListDBAdapter;
import org.example.student_list.StudentListDecorator;
import student.ShortStudent;
import student.Student;
import student_list.StudentList;
import student_list.StudentStrategy;
import StudentList.StudentListYaml;
import StudentList.StudentListJson;
import org.example.student_list.StudentListTxt;

import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class TableViewController implements UpdateDataInterface, TableParamsInterfaceSetter {
    public TableView tableView;
    private MainTableController mainTableController;
    private NavigatorController navigatorController;
    private ButtonPanelController buttonPanelController;
    private FilterPanelController filterPanelController;

    private NavigationPageModel navigationPageModel;

    public StudentListComponent studentList;
    public DataList<ShortStudent> currentDataList;

    public TableViewController(TableView tableView, MainTableController mainTableController, NavigatorController navigatorController, ButtonPanelController buttonPanelController, FilterPanelController filterPanelController) {
        this.tableView = tableView;
        this.mainTableController = mainTableController;
        this.navigatorController = navigatorController;
        this.buttonPanelController = buttonPanelController;
        this.filterPanelController = filterPanelController;

        this.filterPanelController.clearFilters();

        this.studentList = new StudentListDecorator(new StudentList(new StudentListDBAdapter(new StudentListDBImpl())), this);
        this.studentList.subscribe(this);

        this.navigationPageModel = new NavigationPageModel(this.studentList.getCount());

        this.navigatorController.navigationPageModel = this.navigationPageModel;
        this.mainTableController.navigationPageModel = this.navigationPageModel;
        this.buttonPanelController.tableViewController = this;

        this.navigationPageModel.subscribePageUpdate(this);
        this.navigationPageModel.subscribeNavigatorUpdate(this.navigatorController);

        this.mainTableController.mainTableModel.subscribe(this);

    }

    @Override
    public void updatePage(){
        this.checkStudentList();

        this.studentList.sortBy(this.mainTableController.mainTableModel.order,this.mainTableController.mainTableModel.columnName);

        this.navigationPageModel.setMaxCountOfPages(this.studentList.getCount());
        this.currentDataList = this.studentList.getStudentList(this.navigationPageModel.currentPage,this.navigationPageModel.elementsPerPage);
        this.currentDataList.setTableView(this.tableView);


        this.mainTableController.dataStudentListModel = this.currentDataList;
        this.mainTableController.dataStudentListModel.subscribe(this.buttonPanelController.buttonPanel);
        this.buttonPanelController.dataListModel = this.currentDataList;

    }

    private void checkStudentList(){
        //Описываем обработку случая отсутствия списка
        if(studentList.checkConnection()) return;
        this.studentList = this.createSourceStudentList();
        this.studentList.subscribe(this);
        if(!studentList.checkConnection()){
            SwingUtilities.invokeLater(()->{
                JOptionPane.showMessageDialog(this.tableView, "Не удалось подключиться к базе данных", "Info", JOptionPane.INFORMATION_MESSAGE);
            });
        }
    }

    private StudentListDecorator createSourceStudentList(){
        //Создаем модель списка студентов
        StudentListDB st = new StudentListDBImpl();
        if(st.checkConnection()){
            return new StudentListDecorator(new StudentList(new StudentListDBAdapter(st)),this);
        }

        //Проверка для других списков
        LinkedList<StudentStrategy> list = new LinkedList<>();
        list.add(new StudentListTxt("src/main/resources/input.txt", "src/main/resources/input.txt") {});
        //list.add(new StudentListYaml("src/main/resources/input.yaml","src/main/resources/input.yaml"));
        list.add(new StudentListJson("src/main/resources/input.json","src/main/resources/input.json"));

        StudentStrategy resultList = null;
        for (StudentStrategy studentListAdapter : list) {
            if (studentListAdapter.checkConnection()) {
                resultList = studentListAdapter;
                return new StudentListDecorator(new StudentList(resultList),this);
            }
        }
        return new StudentListDecorator(new StudentList(new StudentListDBAdapter(new StudentListDBImpl())),this);
    }

    public void refreshData() {
        this.updatePage();
    }

    @Override
    public void setTableParams(String[] columnNames, int wholeEntitiesCount) {
        this.mainTableController.setTableParams(columnNames, wholeEntitiesCount);
    }

    @Override
    public void setTableData(DataTable dataTable) {

    }



    public void setDefaultParams() {
        this.navigationPageModel.setDefaultParams();
        this.filterPanelController.clearFilters();
    }

    public void filterStudentList(){
        this.navigationPageModel.setDefaultWithoutNotify();
        this.studentList.restoreOrderList();
        Function<List<Student>, List<Student>>[] filters = this.filterPanelController.getFilters();
        Arrays.stream(filters).toList().forEach(it->this.studentList.filterList(it));
    }
}
