package GUIdelegate.component.big;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
