package Main.Controllers;

import Main.MainViews;
import TableGridPack.Controllers.TableViewController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPanelController {
    public MainViews screens;
    public TableViewController tableViewController;

    public TabbedPanelController(MainViews screens, TableViewController tableViewController){
        this.screens = screens;
        this.tableViewController = tableViewController;
        this.screens.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               if(((JTabbedPane)e.getSource()).getSelectedIndex()==0) TabbedPanelController.this.updateFirstTab();
            }
        });
    }
    public void updateFirstTab(){
        TabbedPanelController.this.tableViewController.setDefaultParams();
    }
}
