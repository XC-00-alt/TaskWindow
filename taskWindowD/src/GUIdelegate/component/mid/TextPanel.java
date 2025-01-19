package GUIdelegate.component.mid;

import GUIdelegate.component.small.BoldButton;
import GUIdelegate.component.small.ColorButton;
import GUIdelegate.component.small.FontChooser;
import GUIdelegate.component.small.TextEditField;
import model.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TextPanel extends JPanel {
    private JLabel label;
    private FontChooser fontChooser;
    private BoldButton boldButton;
    private ColorButton colorButton;
    private TextEditField textEditField;
    public String DEFAULT_FONT="default";
    public TextPanel(String title,int panelWidth,int panelHeight,int buttonLen)
    {
        this.setPreferredSize(new Dimension(panelWidth,panelHeight));

        setLayout(new FlowLayout());

        label=new JLabel(title);
        fontChooser=new FontChooser(panelWidth/2,buttonLen);
        boldButton=new BoldButton(buttonLen);
        colorButton=new ColorButton(buttonLen);
        textEditField=new TextEditField(panelWidth,panelHeight-buttonLen);

        add(label);
        add(fontChooser);
        add(boldButton);
        add(colorButton);
        add(textEditField);
    }
    public void addActionListener(ActionListener al)
    {
        fontChooser.addActionListener(al);
        boldButton.addActionListener(al);
        colorButton.addActionListener(al);
        textEditField.addActionListener(al);
    }

    public boolean isFontChooser(Object object)
    {
        return object.equals(fontChooser.getComboBox());
    }
//    public FontChooser getFontChooser() {
//        return fontChooser;
//    }

    public String getItem()
    {
        Object item=fontChooser.getComboBox().getSelectedItem();
        if(item!=null) return item.toString();
        else return DEFAULT_FONT;
    }
    public void setValue(String fontName,boolean boldBool,Color color)
    {
        setSelectedFont(fontName);
        boldButton.setBold(boldBool);
        colorButton.setColor(color);
    }
    public Color showColorDialog()
    {
        return colorButton.showColorDialog();
    }

    public void setSelectedFont(String fontFamilyName)
    {
        fontChooser.getComboBox().setSelectedItem(fontFamilyName);
    }
    public boolean isBoldButton(Object object)
    {
        return object.equals(boldButton);
    }

    public boolean isBold() {
        return boldButton.isBold();
    }
    public void setBold(boolean bold) {
        boldButton.setBold(bold);
    }
    public void reverseBold()
    {
        boldButton.setBold(!boldButton.isBold());
    }
    public boolean isColorButton(Object object)
    {
        return object.equals(colorButton);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth(),getHeight());
    }
}