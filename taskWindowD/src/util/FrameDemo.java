package util;

import GUIdelegate.component.small.FontChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameDemo implements ActionListener {
    private JFrame frame;
    private FontChooser fontChooser;
    public FrameDemo()
    {
        frame=new JFrame("test frame");
        fontChooser=new FontChooser(100,20);
        fontChooser.addActionListener(this);
        frame.add(fontChooser);
        frame.setVisible(true);
        frame.setSize(new Dimension(700,700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==fontChooser.getComboBox())
        {
            System.out.println(fontChooser.getComboBox().getSelectedItem());
        }
    }
}
