package EditCreateForm;

import EditCreateForm.Controllers.InputFieldController;

import javax.swing.*;
import java.awt.*;
import java.util.function.Predicate;

public class InputField extends JPanel {
    public JLabel mainLabel;
    public JTextField mainInputField;

    public InputFieldController inputFieldController;
    public InputField(Predicate<String> validateFieldFunction,String label,boolean optional){

        //Создаем верстку
        this.setLayout(new GridLayout(2,1));

        //Создаем заголовок
        this.mainLabel = new JLabel();
        this.mainLabel.setText(label);
        Font font = this.mainLabel.getFont();
        this.mainLabel.setFont(new Font(font.getFontName(), font.getStyle(), 20));

        //Создаем поле ввода
        this.mainInputField = new JTextField(15);
        this.mainInputField.setFont(new Font(font.getFontName(), font.getStyle(), 20));

        this.add(mainLabel);
        this.add(mainInputField);

        this.inputFieldController = new InputFieldController(this,validateFieldFunction,optional);
    }
}
