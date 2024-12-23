package EditCreateForm;

import EditCreateForm.Controllers.EditCreateWindowController;
import Main.UpdateDataInterface;
import student.Validator;

import javax.swing.*;
import java.awt.*;

public class EditCreateWindow extends JDialog implements UpdateDataInterface {
    public JPanel mainPanel;
    public InputField lastNameField;
    public InputField nameField;
    public InputField surnameField;
    public InputField phoneNumberField;
    public InputField emailField;
    public InputField telegramField;
    public InputField gitHubField;

    public JButton acceptButton;
    public JButton declineButton;

    public EditCreateWindowController editCreateWindowController;

    public EditCreateWindow(){
        super();
        this.setSize(300,750);
        this.setTitle("Student edit form");

        this.setModal(true);

        this.createWindow();
        this.setVisible(false);
    }

    //��������� ����������
    public void createWindow(){
        this.setLayout(new FlowLayout());
        this.mainPanel = new JPanel(new GridLayout(9,1,0,10));


        this.phoneNumberField = new InputField(Validator.Companion::isTelephoneNumberValid$patternLabs2,"TelephoneNumber",true);
        this.telegramField = new InputField(Validator.Companion::isTelegramValid$patternLabs2,"Telegram",true);
        this.emailField = new InputField(Validator.Companion::isEmailValid$patternLabs2,"Email",true);
        this.gitHubField = new InputField(Validator.Companion::isGitValid$patternLabs2,"GitHub",true);

        this.mainPanel.add(lastNameField);
        this.mainPanel.add(nameField);
        this.mainPanel.add(surnameField);
        this.mainPanel.add(phoneNumberField);
        this.mainPanel.add(telegramField);
        this.mainPanel.add(emailField);
        this.mainPanel.add(gitHubField);

        this.acceptButton = new JButton("Ok");
        Font font = this.acceptButton.getFont();
        this.acceptButton.setFont(new Font(font.getFontName(), font.getStyle(), 16));

        this.declineButton = new JButton("Decline");
        this.declineButton.setFont(new Font(font.getFontName(), font.getStyle(), 16));

        this.mainPanel.add(acceptButton,BorderLayout.WEST);
        this.mainPanel.add(declineButton,BorderLayout.EAST);


        this.getContentPane().add(mainPanel);
    }

    public void setEditCreateWindowController(EditCreateWindowController editCreateWindowController){
        this.editCreateWindowController = editCreateWindowController;
    }

    @Override
    public void updatePage() {
        this.editCreateWindowController.updatePage();
    }
}
