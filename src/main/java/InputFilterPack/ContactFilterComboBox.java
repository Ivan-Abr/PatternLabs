package InputFilterPack;

import javax.swing.*;
import java.awt.*;

public class ContactFilterComboBox extends JComboBox {
    public ContactFilterComboBox(){
        super(new String[]{"Включено","Отсутствует","Не важно"});
        this.setPreferredSize(new Dimension(80, 30));
    }
}
