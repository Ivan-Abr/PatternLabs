package ButtonCRUDPanel.Controllers;

import ButtonCRUDPanel.ButtonPanel;
import EditCreateForm.Controllers.*;
import EditCreateForm.EditCreateWindow;
import Main.UpdateDataInterface;
import TableGridPack.Controllers.TableViewController;
import data.DataList;
import student.ShortStudent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ButtonPanelController implements UpdateDataInterface {
    public ButtonPanel buttonPanel;
    public TableViewController tableViewController;
    public DataList<ShortStudent> dataListModel;


    public ButtonPanelController(ButtonPanel buttonPanel) {
        this.buttonPanel = buttonPanel;

        this.buttonPanel.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.createButtonClick();
                ButtonPanelController.this.reloadButtonClick();
            }
        });

        this.buttonPanel.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(new UpdateController(window, ButtonPanelController.this.tableViewController.studentList), window);
                ButtonPanelController.this.reloadButtonClick();
            }
        });
        this.buttonPanel.updateGitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(new UpdateGitController(window, ButtonPanelController.this.tableViewController.studentList), window);
                ButtonPanelController.this.reloadButtonClick();
            }
        });
        this.buttonPanel.updatePhoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(new UpdatePhoneController(window, ButtonPanelController.this.tableViewController.studentList), window);
                ButtonPanelController.this.reloadButtonClick();
            }
        });
        this.buttonPanel.updateTelegramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(new UpdateTelegramController(window, ButtonPanelController.this.tableViewController.studentList), window);
                ButtonPanelController.this.reloadButtonClick();
            }
        });
        this.buttonPanel.updateEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(new UpdateEmailController(window, ButtonPanelController.this.tableViewController.studentList), window);
                ButtonPanelController.this.reloadButtonClick();
            }
        });


        this.buttonPanel.clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.clearButtonClick();
                ButtonPanelController.this.reloadButtonClick();
            }
        });

        this.buttonPanel.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.deleteButtonClick();
                ButtonPanelController.this.reloadButtonClick();
            }
        });

        this.buttonPanel.reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.reloadButtonClick();
            }
        });

        turnOffButtons(0);
    }


    public void turnOnEditButton(int count) {
        if (count == 1) {
            this.buttonPanel.updateButton.setEnabled(true);
            this.buttonPanel.updateGitButton.setEnabled(true);
            this.buttonPanel.updateEmailButton.setEnabled(true);
            this.buttonPanel.updateTelegramButton.setEnabled(true);
            this.buttonPanel.updatePhoneButton.setEnabled(true);
        }
    }

    public void turnOnDeleteButton(int count) {
        if (count >= 1) {
            this.buttonPanel.deleteButton.setEnabled(true);
        }
    }

    public void reloadButtonClick(){
        this.tableViewController.filterStudentList();
        this.tableViewController.refreshData();
    }

    public void turnOffButtons(int stringCount) {
        this.buttonPanel.updateButton.setEnabled(false);
        this.buttonPanel.deleteButton.setEnabled(false);
        this.buttonPanel.updateGitButton.setEnabled(false);
        this.buttonPanel.updateEmailButton.setEnabled(false);
        this.buttonPanel.updateTelegramButton.setEnabled(false);
        this.buttonPanel.updatePhoneButton.setEnabled(false);
        this.turnOnDeleteButton(stringCount);
        this.turnOnEditButton(stringCount);
    }

    public void clearButtonClick() {
        this.tableViewController.setDefaultParams();
    }

    public void createButtonClick() {
        EditCreateWindow window = new EditCreateWindow();
        window.setEditCreateWindowController(new EditCreateWindowController(window, this.tableViewController.studentList));
        window.setVisible(true);
        reloadPage();
    }

    public void updateButtonClick(UpdateController controller, EditCreateWindow window) {
        int selectedIds = this.dataListModel.getSelectedIds()[0];

        controller.id = (int) this.dataListModel.getData().getElement(selectedIds + 1, 0);
        window.setEditCreateWindowController(controller);
        controller.setStudentInfo();
        window.setVisible(true);
    }

    public void deleteButtonClick() {
        int[] selectedIds = this.dataListModel.getSelectedIds();
        LinkedList<Integer> selectedIndexes = new LinkedList<>();
        for (int i : selectedIds) {
            selectedIndexes.add((Integer) this.dataListModel.getData().getElement(i + 1, 0));
        }
        for (int i : selectedIndexes) {
            this.tableViewController.studentList.deleteStudent(i);
        }
        reloadPage();
    }

    public void reloadPage() {
        ButtonPanelController.this.tableViewController.setDefaultParams();
    }

    @Override
    public void updatePage() {
        System.out.println(this.dataListModel.getSelectedIds().length);
        turnOffButtons(this.dataListModel.getSelectedIds().length);
    }
}
