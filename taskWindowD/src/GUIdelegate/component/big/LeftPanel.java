package GUIdelegate.component.big;

import GUIdelegate.component.small.CustomizedLabel;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel{
    int width;
    int height;
    private CustomizedLabel importantLabel=new CustomizedLabel("IMPORTANT",0);
    private CustomizedLabel unimportantLabel=new CustomizedLabel("unimportant",0);
    public LeftPanel(int width,int height)
    {
        this.setPreferredSize(new Dimension(width,height));
        add(importantLabel);
        importantLabel.setBounds(width/2-importantLabel.getWidth()/2,height/3,
                importantLabel.getWidth(),importantLabel.getHeight());
//        add(unimportantLabel);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,getWidth(),getHeight());
//        super.paintComponent(g);
    }
}
