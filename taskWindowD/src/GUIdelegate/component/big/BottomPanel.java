package GUIdelegate.component.big;

import GUIdelegate.component.small.CustomizedLabel;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private CustomizedLabel urgentLabel=new CustomizedLabel("URGENT",0);
    public BottomPanel(int width,int height)
    {
        this.setPreferredSize(new Dimension(width,height));
        urgentLabel.setRatios(0.25f,0.5f,0.25,0.5);
//        urgentLabel.setRatios(0,0,0,0);

    }
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(0,0,getWidth(),getHeight());
        Graphics2D g2d=(Graphics2D)g;
//        g.setColor(Color.black);
//        g2d.drawString(urgentLabel.getText(),getWidth()/4,getHeight()/2);
        urgentLabel.drawString(g2d,getWidth(),getHeight());
    }
}
