package Main;

import javax.swing.*;
import java.awt.*;

public class CustomTabComponent extends JPanel {
    public CustomTabComponent(String title,int fontSize,Color color){
        this.setOpaque(false);
//        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Убираем отступы по бокам

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, fontSize)); // Установка размера текста
        titleLabel.setBackground(Color.CYAN);
        this.add(titleLabel);
    }
}
