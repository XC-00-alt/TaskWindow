package GUIdelegate.component.small;

import GUIdelegate.component.small.ColorButton;
import model.CategoryLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used in creating new CategoryLabel and managing existing CategoryLabel
 */
public class CategoryLabelPane extends JPanel{
    private JPanel pane1=new JPanel();
    private ColorButton colorButton;
    private JTextField nameField;
    private JPanel pane2;
    private JButton confirmButton=new JButton("√");//pending to be changed to icon

    private JButton cancelButton=new JButton("←");
    private JButton deleteButton=new JButton("×");

    private CategoryLabel categoryLabel;
    public CategoryLabelPane(int pane1Width,int buttonLen,boolean isManage,ActionListener al)
    {
        int width=pane1Width+4;
        if(isManage) width=pane1Width+buttonLen*5;
        int height=buttonLen*3/2;
        this.setPreferredSize(new Dimension(width, height));

        pane1.setPreferredSize(new Dimension(pane1Width,height));
        colorButton=new ColorButton(buttonLen);
        colorButton.addActionListener(al);
        pane1.add(colorButton);
        nameField=new JTextField();
        nameField.setPreferredSize(new Dimension(pane1Width-buttonLen*2,buttonLen));
        pane1.add(nameField);
        add(pane1);

        if(isManage) setButtons(height,buttonLen,al);
        else colorButton.setColor(Color.BLUE);
    }
    public void setButtons(int pane2Height,int buttonLen,ActionListener al)
    {
        pane2=new JPanel();
        pane2.setPreferredSize(new Dimension(buttonLen*4,pane2Height));

        confirmButton.setForeground(Color.BLUE);
        cancelButton.setForeground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.RED);

        confirmButton.addActionListener(al);
        cancelButton.addActionListener(al);
        deleteButton.addActionListener(al);

        Dimension buttonDimension=new Dimension(buttonLen*6/5,buttonLen);
        confirmButton.setPreferredSize(buttonDimension);
        cancelButton.setPreferredSize(buttonDimension);
        deleteButton.setPreferredSize(buttonDimension);

        pane2.add(confirmButton);
        pane2.add(cancelButton);
        pane2.add(deleteButton);
        add(pane2);
    }
    public String getText()
    {
        return nameField.getText();
    }

    public void setCategoryLabel(CategoryLabel categoryLabel) {
        this.categoryLabel=categoryLabel;

        if(categoryLabel!=null) {
            colorButton.setColor(categoryLabel.getColor());
            nameField.setText(categoryLabel.getName());
        }

    }
    public Color showColorDialog()
    {
        Color newColor=colorButton.showColorDialog();
        if(categoryLabel!=null)
        {
            categoryLabel.setColor(newColor);
        }
        return newColor;
    }
    public void setColor(Color color)
    {
        colorButton.setColor(color);
    }

    public boolean isColorButton(Object object)
    {
        return object.equals(colorButton);
    }
    public boolean isConfirmButton(Object object)
    {
        return object.equals(confirmButton);
    }
    public boolean isCancelButton(Object object)
    {
        return object.equals(cancelButton);
    }

    public CategoryLabel getCategoryLabel() {
        if(categoryLabel==null&&!getText().isBlank())
        {
            categoryLabel=new CategoryLabel(colorButton.getColor(),getText());
        }
        return categoryLabel;
    }
    public void clearText()
    {
        nameField.setText(null);
    }
    public void setLabelColor(Color color)
    {
        if(categoryLabel!=null) categoryLabel.setColor(color);
    }
    public void setLabelName(String name)
    {
        if(categoryLabel!=null) categoryLabel.setName(name);
    }
}
