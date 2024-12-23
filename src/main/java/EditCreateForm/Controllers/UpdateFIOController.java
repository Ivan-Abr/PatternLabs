package EditCreateForm.Controllers;

import EditCreateForm.EditCreateWindow;
import org.example.student_list.StudentListComponent;

public class UpdateFIOController extends UpdateController{

    public int id;
    public UpdateFIOController(EditCreateWindow editCreateWindow, StudentListComponent studentList) {
        super(editCreateWindow, studentList);
        this.gitHubFieldController.changeEditable(false);
        this.phoneNumberFieldController.changeEditable(false);
        this.emailFieldController.changeEditable(false);
        this.telegramFieldController.changeEditable(false);
    }
}
