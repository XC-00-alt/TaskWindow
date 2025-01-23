package util;

import GUIdelegate.component.big.NoteDialog;
import GUIdelegate.component.mid.TextAttributePanel;
import GUIdelegate.component.small.FontChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FrameDemo implements ActionListener, PropertyChangeListener {
    private JFrame frame;
    private FontChooser fontChooser;
    private TextAttributePanel textPanel;
    private NoteDialog noteDialog;
    public FrameDemo()
    {
        frame=new JFrame("test frame");

        frame.setSize(new Dimension(700,700));

        frame.setLayout(new FlowLayout());
//        setUpFontChooser();
        setUpTextPanel();
        setUpNoteDialog();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setUpNoteDialog()
    {
        noteDialog=new NoteDialog(this,400,500);
        noteDialog.setVisible(true);
    }
    public void setUpTextPanel()
    {
        textPanel=new TextAttributePanel("title",300,150,15);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
