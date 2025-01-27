package GUIdelegate.component.small;

import javax.swing.*;
import java.awt.*;

public class TextFieldWithLabel extends JPanel {
    private JLabel label;
    private JTextField textField;
    public TextFieldWithLabel(String panelName)
    {
        label=new JLabel(panelName);
        textField=new JTextField();
        textField.setEditable(false);

        add(label);
        add(textField);
    }
    public void setText(String text)
    {
        textField.setText(text);
    }
    public boolean isTextEmpty()
    {
        return textField.getText()==null||textField.getText().length()==0;
    }
    public void adjustSize(int width,int height)
    {
        setPreferredSize(new Dimension(width, height));
        textField.setPreferredSize(new Dimension(width*2/3,height*5/6));
    }
}
