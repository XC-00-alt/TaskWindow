package util;

import GUIdelegate.component.mid.TextPanel;
import GUIdelegate.component.small.FontChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameDemo implements ActionListener {
    private JFrame frame;
    private FontChooser fontChooser;
    private TextPanel textPanel;
    public FrameDemo()
    {
        frame=new JFrame("test frame");

        frame.setSize(new Dimension(700,700));

        frame.setLayout(new FlowLayout());
//        setUpFontChooser();
        setUpTextPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setUpTextPanel()
    {
        textPanel=new TextPanel("title",300,150,15);
        textPanel.addActionListener(this);
        frame.add(textPanel);
    }

    public void setUpFontChooser()
    {
        fontChooser=new FontChooser(100,20);
        fontChooser.addActionListener(this);
        frame.add(fontChooser);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource()==fontChooser.getComboBox())
//        {
//            System.out.println(fontChooser.getComboBox().getSelectedItem());
//        }
        if(textPanel.isFontChooser(e.getSource()))
        {
            System.out.println(textPanel.getItem());
        }
    }
}
