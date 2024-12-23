package InputFilterPack.Controllers;

import InputFilterPack.FilterPanel;
import student.Student;

import java.util.List;
import java.util.function.Function;

public class FilterPanelController {
    public FilterPanel filterPanel;
    public SurnameInitialsFieldController surnameInitialsFieldController;
    public ContactFieldController emailController;
    public ContactFieldController telegramController;
    public ContactFieldController gitHubController;
    public ContactFieldController phoneController;

    public FilterPanelController(
            FilterPanel filterPanel,
            SurnameInitialsFieldController surnameInitialsFieldController,
            ContactFieldController emailController,
            ContactFieldController telegramController,
            ContactFieldController gitHubController,
            ContactFieldController phoneController
    )
    {
        this.filterPanel = filterPanel;
        this.surnameInitialsFieldController = surnameInitialsFieldController;
        this.emailController = emailController;
        this.telegramController = telegramController;
        this.gitHubController = gitHubController;
        this.phoneController = phoneController;
    }
    public void clearFilters(){
        this.surnameInitialsFieldController.clearInput();
        this.emailController.clearInput();
        this.telegramController.clearInput();
        this.gitHubController.clearInput();
        this.phoneController.clearInput();
    }

    public Function<List<Student>, List<Student>>[] getFilters(){
        return new Function[]{
                this.surnameInitialsFieldController.getRequest(),
                this.emailController.getRequest(),
                this.telegramController.getRequest(),
                this.gitHubController.getRequest(),
                this.phoneController.getRequest(),
        };
    }
}
