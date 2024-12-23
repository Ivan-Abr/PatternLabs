package EditCreateForm.Controllers;

import EditCreateForm.EditCreateWindow;
import org.example.student_list.StudentListComponent;


public class UpdateTelegramController extends UpdateController{
    public UpdateTelegramController(EditCreateWindow editCreateWindow, StudentListComponent studentList) {
        super(editCreateWindow, studentList);
        this.gitHubFieldController.changeEditable(false);
        this.phoneNumberFieldController.changeEditable(false);
        this.emailFieldController.changeEditable(false);
        this.surnameFieldController.changeEditable(false);
        this.nameFieldController.changeEditable(false);
        this.patronymicFieldController.changeEditable(false);
    }
}
