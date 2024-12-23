package EditCreateForm.Controllers;

import EditCreateForm.EditCreateWindow;
import Main.UpdateDataInterface;
import org.example.student_list.StudentListComponent;
import student.Student;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class EditCreateWindowController implements UpdateDataInterface {
    public EditCreateWindow editCreateWindow;
    public InputFieldController surnameFieldController;
    public InputFieldController nameFieldController;
    public InputFieldController patronymicFieldController;
    public InputFieldController phoneNumberFieldController;
    public InputFieldController telegramFieldController;
    public InputFieldController emailFieldController;
    public InputFieldController gitHubFieldController;
    public StudentListComponent studentList;
    public EditCreateWindowController(EditCreateWindow editCreateWindow, StudentListComponent studentList)
    {
        this.editCreateWindow = editCreateWindow;

        //Записываем контроллеры
        this.surnameFieldController = editCreateWindow.lastNameField.inputFieldController;
        this.nameFieldController = editCreateWindow.nameField.inputFieldController;
        this.patronymicFieldController = editCreateWindow.surnameField.inputFieldController;
        this.phoneNumberFieldController = editCreateWindow.phoneNumberField.inputFieldController;
        this.telegramFieldController = editCreateWindow.telegramField.inputFieldController;
        this.emailFieldController = editCreateWindow.emailField.inputFieldController;
        this.gitHubFieldController = editCreateWindow.gitHubField.inputFieldController;

        //Кидаем форму в подписчки к моделям полей ввода
        this.surnameFieldController.inputFieldModel.setSubscriber(this.editCreateWindow);
        this.nameFieldController.inputFieldModel.setSubscriber(this.editCreateWindow);
        this.patronymicFieldController.inputFieldModel.setSubscriber(this.editCreateWindow);
        this.phoneNumberFieldController.inputFieldModel.setSubscriber(this.editCreateWindow);
        this.telegramFieldController.inputFieldModel.setSubscriber(this.editCreateWindow);
        this.emailFieldController.inputFieldModel.setSubscriber(this.editCreateWindow);
        this.gitHubFieldController.inputFieldModel.setSubscriber(this.editCreateWindow);


        this.editCreateWindow.acceptButton.setEnabled(false);

        //Вешаем действия
        this.editCreateWindow.declineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindowController.this.editCreateWindow.dispose();
            }
        });

        this.editCreateWindow.acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindowController.this.processForm();
            }
        });
        this.studentList = studentList;
    }

    @Override
    public void updatePage() {
        this.editCreateWindow.acceptButton.setEnabled(this.surnameFieldController.checkInputCorrect()
                && this.nameFieldController.checkInputCorrect()
                && this.patronymicFieldController.checkInputCorrect()
                && this.gitHubFieldController.checkInputCorrect()
                && this.phoneNumberFieldController.checkInputCorrect()
                && this.telegramFieldController.checkInputCorrect()
                && this.emailFieldController.checkInputCorrect());
    }

    public void processForm(){
        HashMap<String,Object> hashForStudent = new HashMap<>();


        hashForStudent.put("lastname",checkEmpty(this.surnameFieldController.getTextValue()));
        hashForStudent.put("firstname",checkEmpty(this.nameFieldController.getTextValue()));
        hashForStudent.put("patronymic",checkEmpty(this.patronymicFieldController.getTextValue()));
        hashForStudent.put("telephone",checkEmpty(this.phoneNumberFieldController.getTextValue()));
        hashForStudent.put("telegram",checkEmpty(this.telegramFieldController.getTextValue()));
        hashForStudent.put("email",checkEmpty(this.emailFieldController.getTextValue()));
        hashForStudent.put("git",checkEmpty(this.gitHubFieldController.getTextValue()));
        this.studentList.addStudent(new Student(hashForStudent));
        this.editCreateWindow.dispose();
    }
    protected String checkEmpty(String input){
        return input.isEmpty()?null:input;
    }
}
