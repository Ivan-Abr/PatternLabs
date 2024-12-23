package TableGridPack;

import ButtonCRUDPanel.ButtonPanel;
import InputFilterPack.FilterPanel;
import TableGridPack.Controllers.TableViewController;
import TableGridPack.Navigator.NavigatorPanel;
import data.DataTable;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel implements TableParamsInterfaceSetter {
    public FilterPanel filterPanel;
    public MainTable mainTable;
    public NavigatorPanel navigatorPanel;
    public ButtonPanel buttonPanel;

    public TableViewController tableViewController;

    public TableView() {
        super(new BorderLayout());
        this.filterPanel = new FilterPanel();
        this.add(filterPanel, BorderLayout.NORTH);

        this.mainTable = new MainTable();
        JScrollPane scrollPane = new JScrollPane(this.mainTable);
        this.add(scrollPane, BorderLayout.CENTER);

        // Создаем панель для navigatorPanel и buttonPanel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); // Вертикальное расположение

        this.buttonPanel = new ButtonPanel();
        bottomPanel.add(this.buttonPanel);

        this.navigatorPanel = new NavigatorPanel(5);
        bottomPanel.add(this.navigatorPanel);

        // Добавляем bottomPanel в TableView
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.tableViewController = new TableViewController(
                this,
                this.mainTable.mainTableController,
                this.navigatorPanel.navigatorController,
                this.buttonPanel.buttonPanelController,
                this.filterPanel.filterPanelController
        );
        this.tableViewController.refreshData();
    }

    @Override
    public void setTableParams(String[] columnNames, int wholeEntitiesCount) {
        this.tableViewController.setTableParams(columnNames, wholeEntitiesCount);
    }

    @Override
    public void setTableData(DataTable dataTable) {
        this.tableViewController.setTableData(dataTable);
    }
}
