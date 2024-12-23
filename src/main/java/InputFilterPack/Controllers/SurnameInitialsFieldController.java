package InputFilterPack.Controllers;

import InputFilterPack.Models.SurnameInitialsModel;
import InputFilterPack.SurnameInitialsField;
import student.Student;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.function.Function;

public class SurnameInitialsFieldController implements InputControllerInterface {
    public SurnameInitialsField surnameInitialsField;

    public SurnameInitialsModel surnameInitialsModel;
    public SurnameInitialsFieldController(SurnameInitialsField surnameInitialsField){
        this.surnameInitialsField = surnameInitialsField;

        this.surnameInitialsField.nameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Действия при получении фокуса (не обязательно)
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Действия при потере фокуса
                SurnameInitialsFieldController.this.changeInputText();
            }
        });


        this.surnameInitialsModel = new SurnameInitialsModel(this);
        this.surnameInitialsModel.subscribe(this.surnameInitialsField);

    }
    public void clearInput(){
        this.surnameInitialsModel.clearFilterInput();
        this.setParams();
    }

    public void setParams(){
        this.surnameInitialsField.nameField.setText(this.surnameInitialsModel.filterInput);
    }
    public void changeInputText(){
        System.out.println("Текущий текст: " + this.surnameInitialsField.nameField.getText());
        this.surnameInitialsModel.setFilterInput(this.surnameInitialsField.nameField.getText());
    }

    @Override
    public Function<List<Student>, List<Student>> getRequest() {
        return this.surnameInitialsModel.getRequest();
    }
}
