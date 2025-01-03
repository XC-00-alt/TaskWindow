package GUIdelegate.component.big;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel{
    private JLabel importantLabel=new JLabel("");
    private JLabel unimportantLabel;
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
