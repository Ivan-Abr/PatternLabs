package InputFilterPack;

import InputFilterPack.Controllers.SurnameInitialsFieldController;
import Main.UpdateDataInterface;

import javax.swing.*;
import java.awt.*;

public class SurnameInitialsField extends JPanel implements UpdateDataInterface {
    public InputField nameField;
    public SurnameInitialsFieldController surnameInitialsFieldController;
    public SurnameInitialsField(){
        //Настройка окошка
        super();
        this.setLayout(new GridLayout(2,1));
        this.setBackground(Color.WHITE);

        //Настройка поля ввода
        this.nameField = new InputField();

        //Создаем заголовок
        JLabel label = new JLabel("last name and initials:");
        label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN, 15));

        this.add(label);
        this.add(this.nameField);

        //Создаем контроллер
        this.surnameInitialsFieldController = new SurnameInitialsFieldController(this);
    }

    @Override
    public void updatePage() {
        this.surnameInitialsFieldController.setParams();
    }
}
