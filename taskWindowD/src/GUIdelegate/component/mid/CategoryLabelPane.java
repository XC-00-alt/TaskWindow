package GUIdelegate.component.mid;

import GUIdelegate.component.small.ColorButton;
import model.CategoryLabel;

import javax.swing.*;
import java.awt.*;

public class CategoryLabelPane {
    private ColorButton colorButton;
    private JTextField nameField;
    private JButton confirmButton=new JButton("√");//pending to be changed to icon
    private JButton cancelButton=new JButton("←");
    private JButton deleteButton=new JButton("×");
//    private OperationButton confirmButton;
//    private OperationButton cancelButton;
//    private OperationButton deleteButton;

    private CategoryLabel categoryLabel;
    public CategoryLabelPane()
    {


    }
    public void setButtons()
    {
        confirmButton.setForeground(Color.BLUE);
        cancelButton.setForeground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.RED);
    }
}
