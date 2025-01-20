package GUIdelegate.component.mid;

import GUIdelegate.component.small.BoldButton;
import GUIdelegate.component.small.ColorButton;
import GUIdelegate.component.small.FontChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The panel that includes all attributes of a sequence of text
 */
public class TextAttributePanel extends JPanel {
    private JLabel label;
    private FontChooser fontChooser;
    private BoldButton boldButton;
    private ColorButton colorButton;
    // the panel that includes a scrollable textArea and two buttons for operation
    private TextEditPane textEditPane;
    public String DEFAULT_FONT="default";
    public TextAttributePanel(String title, int panelWidth, int panelHeight, int buttonLen)
    {
        this.setPreferredSize(new Dimension(panelWidth,panelHeight));

        setLayout(new FlowLayout());

        label=new JLabel(title);
        fontChooser=new FontChooser(panelWidth/2,buttonLen);
        boldButton=new BoldButton(buttonLen);
        colorButton=new ColorButton(buttonLen);
        textEditPane =new TextEditPane(panelWidth,panelHeight-buttonLen,
                (panelHeight-buttonLen)/3);

        add(label);
        add(fontChooser);
        add(boldButton);
        add(colorButton);
        add(textEditPane);
//        add(new JSeparator());
    }
    public void addActionListener(ActionListener al)
    {
        fontChooser.addActionListener(al);
        boldButton.addActionListener(al);
        colorButton.addActionListener(al);
        textEditPane.addActionListener(al);
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
    public void setValue(String contentText,String fontName,boolean boldBool,Color color)
    {
        textEditPane.setText(contentText);
        textEditPane.showButtons(false);
        setSelectedFont(fontName);
        boldButton.setBold(boldBool);
        colorButton.setColor(color);
    }
    public void showEditButtons(boolean flag)
    {
        textEditPane.showButtons(flag);
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
    public boolean isConfirmButton(Object o)
    {
        return textEditPane.isConfirmButton(o);
    }
    public boolean isCancelButton(Object o)
    {
        return textEditPane.isCancelButton(o);
    }
    public String getText()
    {
        return textEditPane.getText();
    }

    public void setText(String t)
    {
        textEditPane.setText(t);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth(),getHeight());
    }
}
