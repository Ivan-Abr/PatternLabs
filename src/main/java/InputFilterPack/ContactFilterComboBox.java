package InputFilterPack;

import javax.swing.*;
import java.awt.*;

public class ContactFilterComboBox extends JComboBox {
    public ContactFilterComboBox(){
        super(new String[]{"��������","�����������","�� �����"});
        this.setPreferredSize(new Dimension(80, 30));
    }
}
