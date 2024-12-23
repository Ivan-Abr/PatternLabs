package ButtonCRUDPanel;

import ButtonCRUDPanel.Controllers.ButtonPanelController;
import Main.UpdateDataInterface;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel implements UpdateDataInterface {
    public JButton createButton;
    public JButton deleteButton;
    public JButton updateButton;

    public JButton updateGitButton;
    public JButton updateEmailButton;
    public JButton updateTelegramButton;
    public JButton updatePhoneButton;

    public JButton reloadButton;
    public JButton clearButton;

    //Создаем контроллер панели
    public ButtonPanelController buttonPanelController;
    public ButtonPanel(){

        this.createButton = new CrudButton("create");

        this.deleteButton = new CrudButton("delete");

        this.updateButton = new CrudButton("update");

        this.updateGitButton = new CrudButton("update github");
        this.updateEmailButton = new CrudButton("update email");
        this.updateTelegramButton = new CrudButton("update telegram");
        this.updatePhoneButton = new CrudButton("update phone");


        this.reloadButton = new CrudButton("reload");

        this.clearButton = new CrudButton("clear");

        GridLayout layout = new GridLayout(1,9);
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(createButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(updateGitButton);
        panel.add(updateEmailButton);
        panel.add(updateTelegramButton);
        panel.add(updatePhoneButton);
        panel.add(reloadButton);
        panel.add(clearButton);

        this.add(panel);
        //Создание контроллера
        this.buttonPanelController = new ButtonPanelController(this);
    }

    @Override
    public void updatePage() {
        this.buttonPanelController.updatePage();
    }
}
