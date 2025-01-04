package GUIdelegate.component.small;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CustomizedLabel extends JLabel {
    private double rotationRadius;

    public CustomizedLabel(String text,double degree)
    {
        super(text);
        rotationRadius=Math.toRadians(degree);
//        FontMetrics metrics = new FontMetrics(getFont()){};
//        Rectangle2D bounds = metrics.getStringBounds(text, null);
//        setBounds(0, 0, (int) bounds.getWidth(), (int) bounds.getHeight());
    }
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d=(Graphics2D)g;
        g2d.rotate(rotationRadius,getX()+getWidth()/2,getY()+getHeight()/2);
        super.paintComponent(g);
    }
}
