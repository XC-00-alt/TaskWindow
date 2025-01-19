package GUIdelegate.component.mid;

import GUIdelegate.component.small.TextScrollArea;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class TextEditPane extends JPanel implements DocumentListener {
//    private JTextArea textArea =new JTextArea();
    private TextScrollArea textScrollArea;
    private JButton confirmButton=new JButton("confirm");
    private JButton cancelButton=new JButton("cancel");
    public TextEditPane(int width, int height)
    {
        setPreferredSize(new Dimension(width,height));
        textScrollArea =new TextScrollArea(width*4/5, height*2/3);
        add(textScrollArea);
        add(confirmButton);
        add(cancelButton);
        showButtons(false);
    }
    public void addActionListener(ActionListener al)
    {
        confirmButton.addActionListener(al);
        cancelButton.addActionListener(al);
    }

    public void showButtons(boolean flag)
    {
        confirmButton.setVisible(flag);
        cancelButton.setVisible(flag);
    }
//    public boolean isTextArea(Object o)
//    {
//        return textArea.equals(o);
//    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth(),getHeight());
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
