package MainPack;

import javax.swing.*;
import java.awt.*;

public class StudentManagementApp extends JFrame {
    public StudentManagementApp(String name){
        super(name);
        this.setTitle(name);
    }
    public void createWindow(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(new MainViews());

        this.pack();
        this.setVisible(true);
    }
    public static void start(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentManagementApp m = new StudentManagementApp("Manager");
                m.createWindow();
            }
        });
    }
}
