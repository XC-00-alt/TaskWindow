package GUIdelegate.component.mid;

import GUIdelegate.component.small.BoldButton;
import GUIdelegate.component.small.ColorButton;
import GUIdelegate.component.small.FontChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TextPanel extends JPanel {
    private JLabel label;
    private FontChooser fontChooser;
    private BoldButton boldButton;
    private ColorButton colorButton;
    private JTextField textField;
    public TextPanel(String title)
    {
        label=new JLabel(title);
    }
    public void addActionListener(ActionListener al)
    {
        fontChooser.addActionListener(al);
        boldButton.addActionListener(al);
    }
}
