package GUIdelegate.component.small;

import javax.swing.*;
import java.awt.*;

public class BoldButton extends JButton {
    public BoldButton(int length)
    {
        setPreferredSize(new Dimension(length,length));
        setText("B");
    }
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.BLACK);
//        g.drawRect(0,0,getWidth(),getHeight());
//    }
}
