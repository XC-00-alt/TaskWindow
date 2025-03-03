package GUIdelegate.component.mid;

import GUIdelegate.component.small.CategoryLabelPane;
import model.CategoryLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateLabelDialog extends JDialog{
    private CategoryLabelPane createPane;
    private JButton confirmButton=new JButton("confirm");
    private JButton cancelButton=new JButton("cancel");
    public CreateLabelDialog(int width,int height,ActionListener al)
    {
        setTitle("create a new label");
        setLayout(new FlowLayout());
        setSize(new Dimension(width, height));
        setResizable(false);
        setLocation(width/2,height*2/5);

        createPane=new CategoryLabelPane(width*3/4,width/10,false,al);
        add(createPane);

        Dimension buttonDimension=new Dimension(width/3,width/10);
        confirmButton.setSize(buttonDimension);
        cancelButton.setSize(buttonDimension);
        confirmButton.addActionListener(al);
        cancelButton.addActionListener(al);
        add(confirmButton);
        add(cancelButton);
    }
    public String getText()
    {
        return createPane.getText();
    }
    public boolean isColorButton(Object object)
    {
        return createPane.isColorButton(object);
    }
    public Color showColorDialog()
    {
        return createPane.showColorDialog();
    }
    public boolean isConfirmButton(Object object)
    {
        return object.equals(confirmButton);
    }
    public boolean isCancelButton(Object object)
    {
        return object.equals(cancelButton);
    }
    public CategoryLabel getCategoryLabel()
    {
        return createPane.getCategoryLabel();
    }
    public void reset()
    {
        setVisible(false);
        createPane.setColor(Color.BLUE);
        createPane.clearText();
        createPane.setCategoryLabel(null);
    }
}
