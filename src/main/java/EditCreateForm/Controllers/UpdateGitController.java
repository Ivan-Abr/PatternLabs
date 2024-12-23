package EditCreateForm.Controllers;

import EditCreateForm.EditCreateWindow;
import org.example.student_list.StudentListComponent;

public class UpdateGitController extends UpdateController{
    public UpdateGitController(EditCreateWindow editCreateWindow, StudentListComponent studentList) {
        super(editCreateWindow, studentList);
        this.surnameFieldController.changeEditable(false);
        this.nameFieldController.changeEditable(false);
        this.patronymicFieldController.changeEditable(false);
        this.phoneNumberFieldController.changeEditable(false);
        this.emailFieldController.changeEditable(false);
        this.telegramFieldController.changeEditable(false);
    }
}
