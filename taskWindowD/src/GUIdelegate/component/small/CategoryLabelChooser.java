package GUIdelegate.component.small;

import model.CategoryLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class is used in EditStatePanel
 */
    // (1) make this class functional
    // (2) finish management dialog
    // (3) link this to note
public class CategoryLabelChooser extends JPanel {
    private JLabel attributeName;
    private JComboBox<CategoryLabel> comboBox;
    private CategoryLabel createNewLabel=new CategoryLabel(Color.WHITE,"+");
    public CategoryLabelChooser(String attributeStr,int boxWidth, int height)
    {
//        setPreferredSize(new Dimension(width,height));

        attributeName=new JLabel(attributeStr);
//        attributeName.setPreferredSize(new Dimension(width,height));
        add(attributeName);

        comboBox=new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(boxWidth,height));
        comboBox.addItem(createNewLabel);
        add(comboBox);
    }
    public void addActionListener(ActionListener al)
    {
        comboBox.addActionListener(al);
    }
    public void addItem(CategoryLabel label)
    {
        comboBox.addItem(label);
    }
    public void removeItem(CategoryLabel label)
    {
        comboBox.removeItem(label);
    }
    public void setComboBoxItems(List<CategoryLabel> labelList)
    {
        for(CategoryLabel categoryLabel:labelList)
        {
            comboBox.addItem(categoryLabel);
        }
    }
    public void setSelectedLabel(CategoryLabel label)
    {
        comboBox.setSelectedItem(label);
        comboBox.setBackground(label.getColor());
    }
}
