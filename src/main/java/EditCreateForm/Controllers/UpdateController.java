package EditCreateForm.Controllers;

import EditCreateForm.EditCreateWindow;
import org.example.student_list.StudentListComponent;
import student.Student;

import java.util.HashMap;


public class UpdateController extends EditCreateWindowController{
    public UpdateController(EditCreateWindow editCreateWindow, StudentListComponent studentList){
        super(editCreateWindow, studentList);
    }
    public int id;
    public void setStudentInfo(){
        Student st = studentList.getStudentById(id);
        assert st != null;
        this.surnameFieldController.inputFieldModel.setInputText(st.getFirstName());
        this.surnameFieldController.setInputText();

        this.nameFieldController.inputFieldModel.setInputText(st.getLastName());
        this.nameFieldController.setInputText();

        this.patronymicFieldController.inputFieldModel.setInputText(st.getPatronymic());
        this.patronymicFieldController.setInputText();

        this.gitHubFieldController.inputFieldModel.setInputText(st.getGit());
        this.gitHubFieldController.setInputText();

        this.emailFieldController.inputFieldModel.setInputText(st.getMail());
        this.emailFieldController.setInputText();

        this.phoneNumberFieldController.inputFieldModel.setInputText(st.getTelephone());
        this.phoneNumberFieldController.setInputText();

        this.telegramFieldController.inputFieldModel.setInputText(st.getTelegram());
        this.telegramFieldController.setInputText();
    }

    @Override
    public void processForm(){
        HashMap<String,Object> hashForStudent = new HashMap<>();


        hashForStudent.put("lastname",checkEmpty(this.surnameFieldController.getTextValue()));
        hashForStudent.put("firstname",checkEmpty(this.nameFieldController.getTextValue()));
        hashForStudent.put("surname",checkEmpty(this.patronymicFieldController.getTextValue()));
        hashForStudent.put("telephoneNumber",checkEmpty(this.phoneNumberFieldController.getTextValue()));
        hashForStudent.put("telegram",checkEmpty(this.telegramFieldController.getTextValue()));
        hashForStudent.put("email",checkEmpty(this.emailFieldController.getTextValue()));
        hashForStudent.put("git",checkEmpty(this.gitHubFieldController.getTextValue()));
        this.studentList.updateStudent(id,new Student(hashForStudent));
        this.editCreateWindow.dispose();
    }
}
