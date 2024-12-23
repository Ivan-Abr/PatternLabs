package Main;

import Main.Controllers.TabbedPanelController;
import Main.Factory.MainViewsFactory;
import TableGridPack.TableView;

import javax.swing.*;

public class MainViews extends JTabbedPane {

    public TabbedPanelController tabbedPanelController;
    public TableView tableView;
    public JPanel tab2;
    public JPanel tab3;

    public MainViews() {

        this.tableView = MainViewsFactory.createTableView();

        this.tab2 = MainViewsFactory.createTab2();

        this.tab3 = MainViewsFactory.createTab3();

        this.addTab("Student View List", this.tableView);
        this.addTab("Tab 2", this.tab2);
        this.addTab("Tab 3", this.tab3);

        this.tabbedPanelController = new TabbedPanelController(this, this.tableView.tableViewController);

    }

}