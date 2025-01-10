package GUIdelegate.component.mid;

import GUIdelegate.component.small.ColorButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ColorPane extends JPanel {
    private JLabel attributeName;
    private ColorButton colorButton;
    public ColorPane(String attributeStr,int buttonLen)
    {
        colorButton=new ColorButton(buttonLen);
        attributeName=new JLabel(attributeStr);
        add(attributeName);
        add(colorButton);
    }

//    public void setLabel(String attributeStr)
//    {
//        attributeName.setText(attributeStr);
//    }

    public void addActionListener(ActionListener al)
    {
        colorButton.addActionListener(al);
    }
    public void setColor(Color color)
    {
        colorButton.setBackground(color);
    }

    public Color getColorFromButton()
    {
        return colorButton.getColor();
    }
    public ColorButton getColorButton() {
        return colorButton;
    }
    public Color showColorDialog()
    {
        Color newColor=JColorChooser.showDialog(this,"Choose color",
                colorButton.getBackground(),true);
        if(newColor!=null) {
            colorButton.setColor(newColor);
        }
        return colorButton.getColor();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth(),getHeight());
    }
}
