package MainPack.Factory;

import TableGridPack.TableView;

import javax.swing.*;

public class MainViewsFactory {
    public static TableView createTableView() {
        return new TableView();
    }

    public static JPanel createTab2() {
        JPanel tab2 = new JPanel();
        tab2.add(new JLabel("Tab 2"));
        return new JPanel();
    }

    public static JPanel createTab3() {
        JPanel tab3 = new JPanel();
        tab3.add(new JLabel("Tab 3"));
        return new JPanel();
    }
}
