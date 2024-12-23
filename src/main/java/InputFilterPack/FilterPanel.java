package InputFilterPack;

import InputFilterPack.Controllers.FilterPanelController;

import javax.swing.*;
import java.awt.*;

public class FilterPanel extends JPanel {
    public SurnameInitialsField surnameInitialsField;
    public ContactField githubField;
    public ContactField emailField;
    public ContactField phoneField;
    public ContactField telegramField;

    public FilterPanelController filterPanelController;

    public FilterPanel(){
        GridLayout layout =new GridLayout(1,5);
//        layout.setHgap(30);
        this.setLayout(layout);
        this.setBackground(Color.WHITE);
//        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        //Создаем поле для ввода фамилии и инициалов
        this.surnameInitialsField = new SurnameInitialsField();
        this.add(surnameInitialsField);

        //Создаем поле для фильтрации гита
        this.githubField = new ContactField("Git","git");
        this.add(this.githubField);

        //Создаем поле для фильтрации почты
        this.emailField = new ContactField("Email","email");
        this.add(this.emailField);

        //Создаем поле для фильтрации телеграмма
        this.phoneField = new ContactField("Telegram","telegram");
        this.add(this.phoneField);


        //Создаем поле для фильтрации телефон
        this.telegramField = new ContactField("Phone","telephoneNumber");
        this.add(this.telegramField);

        //Создаем контроллер
        this.filterPanelController = new FilterPanelController(
                this,
                this.surnameInitialsField.surnameInitialsFieldController,
                this.emailField.contactFieldController,
                this.phoneField.contactFieldController,
                this.githubField.contactFieldController,
                this.telegramField.contactFieldController
                );
    }
}
