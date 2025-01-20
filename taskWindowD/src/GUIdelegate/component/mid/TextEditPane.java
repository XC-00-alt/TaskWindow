package GUIdelegate.component.mid;

import GUIdelegate.component.small.TextScrollArea;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * The panel that includes a scrollable textArea and two buttons for operation
 */
public class TextEditPane extends JPanel implements DocumentListener {
    // A JScrollPane that uses JTextArea as its view
    private TextScrollArea textScrollArea;
    private JButton confirmButton=new JButton("confirm");
    private JButton cancelButton=new JButton("cancel");
    private String oldText;
    public TextEditPane(int width, int height,int scrollAreaHeight)
    {
        setPreferredSize(new Dimension(width,height));
        textScrollArea =new TextScrollArea(width*4/5, scrollAreaHeight);
        confirmButton.setSize(new Dimension(width/4,height-scrollAreaHeight));
        cancelButton.setSize(new Dimension(width/4,height-scrollAreaHeight));
        add(textScrollArea);
        add(confirmButton);
        add(cancelButton);
        showButtons(false);
        textScrollArea.addDocumentListener(this);
    }
    public void addActionListener(ActionListener al)
    {
        confirmButton.addActionListener(al);
        cancelButton.addActionListener(al);
    }

    public void setText(String t)
    {
        textScrollArea.setText(t);
        oldText=t;
    }
    public String getText()
    {
        return textScrollArea.getText();
    }

    public void showButtons(boolean flag)
    {
        confirmButton.setVisible(flag);
        cancelButton.setVisible(flag);
    }
    public boolean isConfirmButton(Object o)
    {
        return confirmButton.equals(o);
    }

    public boolean isCancelButton(Object o)
    {
        return cancelButton.equals(o);
    }
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.BLACK);
//        g.drawRect(0,0,getWidth(),getHeight());
//    }

    // ref: https://blog.csdn.net/ouqianbei/article/details/105862416
    @Override
    public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        showButtons(!Objects.equals(oldText, textScrollArea.getText()));
    }
}
