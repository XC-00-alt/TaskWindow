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
    public TextPanel(String title,int panelWidth,int panelHeight,int buttonLen)
    {
        this.setPreferredSize(new Dimension(panelWidth,panelHeight));

        setLayout(new FlowLayout());

        label=new JLabel(title);
        System.out.println(label.getWidth());
        fontChooser=new FontChooser(panelWidth/2,buttonLen*2);
        boldButton=new BoldButton(buttonLen);
        colorButton=new ColorButton(buttonLen);

        add(label);
        add(fontChooser);
        add(boldButton);
        add(colorButton);
    }
    public void addActionListener(ActionListener al)
    {
        fontChooser.addActionListener(al);
        boldButton.addActionListener(al);
        colorButton.addActionListener(al);
    }

    public FontChooser getFontChooser() {
        return fontChooser;
    }

    public BoldButton getBoldButton() {
        return boldButton;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth(),getHeight());
    }
}
