package ButtonCRUDPanel;

import javax.swing.*;
import java.awt.*;

public class CrudButton extends JButton {
    public CrudButton(String title){
        super(title);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font font = this.getFont();
        this.setFont(new Font(font.getFontName(),font.getStyle(),15));
    }
}
