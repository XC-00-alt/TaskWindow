package GUIdelegate.component.big;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel{
    private JLabel dateLabel;
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
