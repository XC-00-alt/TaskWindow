package GUIdelegate.component.big;

import GUIdelegate.component.small.CustomizedLabel;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel{
    int width;
    int height;
//    private CustomizedLabel importantLabel=new CustomizedLabel("I",0);
//    private CustomizedLabel unimportantLabel=new CustomizedLabel("unimportant",0);
    private String importantLabel="IMPORTANT";
    private String unimportantLabel="unimportant";
    private double rotationRadius=Math.toRadians(-90);
    public LeftPanel(int width,int height)
    {
        this.width=width;
        this.height=height;
        this.setPreferredSize(new Dimension(width,height));
    }
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.black);
        Graphics2D g2d=(Graphics2D)g;
        g2d.rotate(rotationRadius,getWidth()/2,getHeight()/3);
        g2d.drawString(importantLabel,0,getHeight()/3);
        // should rotate back first
        g2d.rotate(-rotationRadius,getWidth()/2,getHeight()/3);
        g2d.rotate(rotationRadius,getWidth()/2,getHeight()*2/3);
        g2d.drawString(unimportantLabel,0,getHeight()*2/3);
    }
}
