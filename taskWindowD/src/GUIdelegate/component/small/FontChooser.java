package GUIdelegate.component.small;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// ref:https://blog.csdn.net/qq_45660997/article/details/105966853
// https://blog.csdn.net/Qinjy_sir/article/details/133684508
// https://blog.csdn.net/msh2016/article/details/104410383
public class FontChooser extends JPanel {
    String[] fonts;
    JComboBox<String> comboBox;
    public FontChooser(int width,int height)
    {
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonts=ge.getAvailableFontFamilyNames();
        comboBox=new JComboBox<>(fonts);
        this.add(comboBox);
        setPreferredSize(new Dimension(width,height));
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
    public void addActionListener(ActionListener al)
    {
        comboBox.addActionListener(al);
    }
}
