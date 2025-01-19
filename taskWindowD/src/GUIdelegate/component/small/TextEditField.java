package GUIdelegate.component.small;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TextEditField extends JPanel{
    private JTextField textField=new JTextField();
    private JButton confirmButton=new JButton("confirm");
    private JButton cancelButton=new JButton("cancel");
    public TextEditField(int width, int height)
    {
        setPreferredSize(new Dimension(width,height));
        textField.setPreferredSize(new Dimension(width*4/5,height*2/3));
        add(textField);
        add(confirmButton);
        add(cancelButton);
        showButtons(false);
    }
    public void addActionListener(ActionListener al)
    {
        textField.addActionListener(al);
        confirmButton.addActionListener(al);
        cancelButton.addActionListener(al);
    }

    public void showButtons(boolean flag)
    {
        confirmButton.setVisible(flag);
        cancelButton.setVisible(flag);
    }
    public boolean isTextField(Object o)
    {
        return textField.equals(o);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth(),getHeight());
    }
}
