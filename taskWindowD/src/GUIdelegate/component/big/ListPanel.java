package GUIdelegate.component.big;

import javax.swing.*;
import java.awt.*;

public class ListPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.CYAN);
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
