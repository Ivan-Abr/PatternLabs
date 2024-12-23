package EditCreateForm.Controllers;

import EditCreateForm.EditCreateWindow;
import org.example.student_list.StudentListComponent;

public class UpdatePhoneController extends UpdateController{

    public UpdatePhoneController(EditCreateWindow editCreateWindow, StudentListComponent studentList) {
        super(editCreateWindow, studentList);
        this.gitHubFieldController.changeEditable(false);
        this.surnameFieldController.changeEditable(false);
        this.nameFieldController.changeEditable(false);
        this.patronymicFieldController.changeEditable(false);
        this.emailFieldController.changeEditable(false);
        this.telegramFieldController.changeEditable(false);
    }
}
