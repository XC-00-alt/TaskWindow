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
        if(isManage) width=pane1Width+buttonLen*6;
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

        if(isManage) setButtons(buttonLen,al);
    }
    public void setButtons(int buttonLen,ActionListener al)
    {
        pane2=new JPanel();
        pane2.setPreferredSize(new Dimension(buttonLen*5,buttonLen));

        confirmButton.setForeground(Color.BLUE);
        cancelButton.setForeground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.RED);

        confirmButton.addActionListener(al);
        cancelButton.addActionListener(al);
        deleteButton.addActionListener(al);

        Dimension buttonDimension=new Dimension(buttonLen,buttonLen);
        confirmButton.setPreferredSize(buttonDimension);
        cancelButton.setPreferredSize(buttonDimension);
        deleteButton.setPreferredSize(buttonDimension);

        pane2.add(confirmButton);
        pane2.add(cancelButton);
        pane2.add(deleteButton);
        add(pane2);
    }

    public void setCategoryLabel(CategoryLabel categoryLabel) {
        try {
            this.categoryLabel=categoryLabel;
            colorButton.setColor(categoryLabel.getColor());
            nameField.setText(categoryLabel.getName());
        }catch (NullPointerException nullEx)
        {
            System.out.println(nullEx.getMessage());
        }
    }
    public Color showColorDialog()
    {
        return colorButton.showColorDialog();
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
}
