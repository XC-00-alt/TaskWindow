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
public class CategoryLabelPane extends JPanel implements ActionListener {
    private ColorButton colorButton;
    private JTextField nameField;
    private JButton confirmButton=new JButton("√");//pending to be changed to icon
    private JButton cancelButton=new JButton("←");
    private JButton deleteButton=new JButton("×");

    private CategoryLabel categoryLabel;
    public CategoryLabelPane(int width,int height, int buttonLen)
    {
        this.setPreferredSize(new Dimension(width, height));
        colorButton=new ColorButton(buttonLen);
        nameField.setPreferredSize(new Dimension(width-buttonLen*6,buttonLen));
        add(nameField);
        setButtons();
    }
    public void setButtons()
    {
        confirmButton.setForeground(Color.BLUE);
        cancelButton.setForeground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.RED);
        colorButton.addActionListener(this);
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
        deleteButton.addActionListener(this);
        add(colorButton);
        add(confirmButton);
        add(cancelButton);
        add(deleteButton);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object srcObject=e.getSource();
        if(srcObject.equals(colorButton))
        {
            // if undo and redo were to be implemented,
            // then CategoryLabel should have a notifier
//            Color oldColor=categoryLabel.getColor();
            Color newColor=showColorDialog();
            categoryLabel.setColor(newColor);
        }
    }
}
