package TableGridPack.Controllers;

import TableGridPack.MainTable;
import TableGridPack.Models.MainTableModel;
import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.TableParamsInterfaceSetter;
import data.DataList;
import data.DataTable;
import student.ShortStudent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;


public class MainTableController implements TableParamsInterfaceSetter {
    public MainTable mainTable;
    public NavigationPageModel navigationPageModel;

    public MainTableModel mainTableModel = new MainTableModel();
    public DataList<ShortStudent> dataStudentListModel;

    public MainTableController(MainTable mainTable) {
        this.mainTable = mainTable;
        this.mainTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = MainTableController.this.mainTable.columnAtPoint(e.getPoint());
                MainTableController.this.mainTableModel.sortOrder(MainTableController.this.mainTable.getColumnName(col));
            }
        });
        this.mainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int[] selectedRows = MainTableController.this.mainTable.getSelectedRows();
                    MainTableController.this.dataStudentListModel.unSelectAll();
                    for (int rowIndex : selectedRows) {
                        MainTableController.this.dataStudentListModel.select(rowIndex);
                    }
                    MainTableController.this.dataStudentListModel.notifySubs();
                }
            }
        });
    }

    @Override
    public void setTableParams(String[] columnNames, int wholeEntitiesCount) {
        DefaultTableModel model = (DefaultTableModel) this.mainTable.getModel();
        model.setColumnIdentifiers(columnNames);
    }

    @Override
    public void setTableData(DataTable dataTable) {
        DefaultTableModel model = (DefaultTableModel) this.mainTable.getModel();
        String[] currentColumnNames = new String[model.getColumnCount()];
        for (int i = 0; i < currentColumnNames.length; i++) {
            currentColumnNames[i] = model.getColumnName(i); // Получаем имя каждого столбца
        }

        Object[][] arr = new Object[dataTable.getRowCount() - 1][dataTable.getColumnCount()];
        for (int i = 1; i < dataTable.getRowCount(); i++) {
            for (int j = 0; j < dataTable.getColumnCount(); j++) {
                var elem = dataTable.getElement(i, j);

                if (elem instanceof Map) {
                    arr[i - 1][j] = mapToString((Map<?, ?>) elem);
                } else {
                    arr[i - 1][j] = elem;
                }
            }
        }
        model.setDataVector(arr, currentColumnNames);
    }

    String mapToString(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        // Удаляем последнюю запятую и пробел, если строка не пустая
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }
}