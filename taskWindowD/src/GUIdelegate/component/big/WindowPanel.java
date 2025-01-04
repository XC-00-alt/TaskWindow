package GUIdelegate.component.big;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {
    Color IMPORTANT_URGENT=new Color(0xFFE562);
    Color IMPORTANT_NON_URGENT=new Color(0xA6EE9D);
    Color UNIMPORTANT_URGENT=new Color(0xFFB662);
    Color UNIMPORTANT_NON_URGENT=new Color(0xEE9D9D);
    @Override
    public void paintComponent(Graphics g)
    {
        int halfWidth=getWidth()/2;
        int halfHeight=getHeight()/2;
        // Q2
        g.setColor(IMPORTANT_URGENT);
        g.fillRect(0,0,halfWidth,halfHeight);
        // Q1
        g.setColor(IMPORTANT_NON_URGENT);
        g.fillRect(getWidth()-halfWidth,0,halfWidth,halfHeight);
        //Q3
        g.setColor(UNIMPORTANT_URGENT);
        g.fillRect(0,getHeight()-halfHeight,halfWidth,halfHeight);
        //Q4
        g.setColor(UNIMPORTANT_NON_URGENT);
        g.fillRect(getWidth()-halfWidth,getHeight()-halfHeight,halfWidth,halfHeight);
    }
}
