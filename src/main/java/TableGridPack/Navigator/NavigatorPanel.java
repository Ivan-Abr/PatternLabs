package TableGridPack.Navigator;

import Main.UpdateDataInterface;
import TableGridPack.Navigator.Controllers.NavigatorController;

import javax.swing.*;
import java.awt.*;

public class NavigatorPanel extends JPanel implements UpdateDataInterface {
    public JButton prevButton;
    public JButton nextButton;
    public JLabel pageLabel;
    public ElemsForPageSelector elemsForPageSelector;

    int currentPage = 1;
    int maxCountOfPages;

    public NavigatorController navigatorController;

    public NavigatorPanel(int maxCountOfPages){
        //Создаем кнопку предыдущей страницы
        this.prevButton = new JButton("<--");
        Font font = this.prevButton.getFont();
        this.prevButton.setFont(new Font(font.getFontName(),font.getStyle(),15));

        //Создаем кнопку следующей страницы
        this.nextButton = new JButton("-->");
        this.nextButton.setFont(new Font(font.getFontName(),font.getStyle(),15));

        //Создаем кнопку выбора количества элементов на странице
        this.elemsForPageSelector = new ElemsForPageSelector();
        this.elemsForPageSelector.setFont(new Font(font.getFontName(),font.getStyle(),15));


        this.maxCountOfPages = maxCountOfPages;
        this.pageLabel = new JLabel("",JLabel.CENTER);
        this.pageLabel.setFont(new Font(font.getFontName(),font.getStyle(),15));

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
//        this.setBorder(new EmptyBorder(20,0,0,0));

        JPanel panel = new JPanel();
        FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER);
        panel.setLayout(layout1);
        panel.add(this.prevButton);
        panel.add(this.pageLabel);
        panel.add(this.nextButton);
        panel.add(this.elemsForPageSelector);

        this.add(panel);

        this.navigatorController = new NavigatorController(this);

    }


    @Override
    public void updatePage() {
        this.navigatorController.updateLabel();
    }
}
