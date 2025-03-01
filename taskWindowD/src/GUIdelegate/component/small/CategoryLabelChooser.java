package GUIdelegate.component.small;

import model.CategoryLabel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class is used in EditStatePanel
 */
public class CategoryLabelChooser extends JPanel {
    private JComboBox<CategoryLabel> comboBox;
    private CategoryLabel createNewLabel=new CategoryLabel(Color.WHITE,"+");
    public CategoryLabelChooser(int width, int height, List<CategoryLabel> labelList)
    {
        setPreferredSize(new Dimension(width,height));
        comboBox.setPreferredSize(new Dimension(width,height*4/5));
        comboBox=new JComboBox<>();
        comboBox.addItem(createNewLabel);
        for(CategoryLabel categoryLabel:labelList)
        {
            comboBox.addItem(categoryLabel);
        }
        add(comboBox);
    }
    public void setSelectedLabel(CategoryLabel label)
    {
        comboBox.setSelectedItem(label);
        comboBox.setBackground(label.getColor());
    }
}
