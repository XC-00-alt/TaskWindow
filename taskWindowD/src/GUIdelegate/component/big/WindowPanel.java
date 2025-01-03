package GUIdelegate.component.big;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
