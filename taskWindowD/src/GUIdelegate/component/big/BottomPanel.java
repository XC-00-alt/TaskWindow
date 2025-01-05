package GUIdelegate.component.big;

import GUIdelegate.component.small.CustomizedLabel;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private CustomizedLabel urgentLabel=new CustomizedLabel("URGENT",0);
    private CustomizedLabel nonUrgentLabel =new CustomizedLabel("non-urgent",0);

    public BottomPanel(int width,int height)
    {
        this.setPreferredSize(new Dimension(width,height));
        urgentLabel.setRatios(0.20f,0.5f,0.20,0.5);
        nonUrgentLabel.setRatios(0.56f,0.5f,0.56,0.5);
    }
    @Override
    public void paintComponent(Graphics g)
    {
//        g.setColor(Color.BLUE);
//        g.fillRect(0,0,getWidth(),getHeight());
        Graphics2D g2d=(Graphics2D)g;
        urgentLabel.drawString(g2d,getWidth(),getHeight());
        nonUrgentLabel.drawString(g2d,getWidth(),getHeight());
    }
}
