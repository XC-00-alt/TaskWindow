package GUIdelegate.component.mid;

import GUIdelegate.component.small.CategoryLabelPane;
import model.CategoryLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class ManageLabelDialog extends JDialog {
    private JScrollPane container=new JScrollPane();
    private JPanel containerInScrollableContainer=new JPanel(new FlowLayout());
    private List<CategoryLabelPane> labelPanes;
    private int LABEL_PANE1_WIDTH;
    private int BUTTON_LEN;
    public ManageLabelDialog(int width,int height)
    {
        setTitle("manage labels");
        setSize(new Dimension(width, height));
//        setResizable(false);
        setLocation(width/2,height*2/5);
        containerInScrollableContainer.setPreferredSize(new Dimension(width, height));
        container.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        container.setViewportView(containerInScrollableContainer);
        add(container);
        LABEL_PANE1_WIDTH=width/2;
        BUTTON_LEN=width/10;
        labelPanes=new ArrayList<>();
    }
    public void addLabels(List<CategoryLabel> labelList)
    {
        for(CategoryLabel label:labelList)
        {
            addLabel(label);
        }
    }
    public void addLabel(CategoryLabel label)
    {
        CategoryLabelPane categoryLabelPane=new CategoryLabelPane(LABEL_PANE1_WIDTH,BUTTON_LEN,true,null);
        categoryLabelPane.setCategoryLabel(label);
        labelPanes.add(categoryLabelPane);
        containerInScrollableContainer.add(categoryLabelPane);
    }
    public void clearLabels()
    {
        containerInScrollableContainer.removeAll();
    }
    public CategoryLabelPane searchPane(CategoryLabel label)
    {
        for(CategoryLabelPane pane:labelPanes)
        {
            if(label.equals(pane.getCategoryLabel())) return pane;
        }
        return null;
    }
    public void removeLabel(CategoryLabel label)
    {
        if(labelPanes.size()<2)
        {
            JOptionPane.showMessageDialog(this,"you can't have no label at all!");
        }
        else{
            CategoryLabelPane pane=searchPane(label);
            if(pane!=null)
            {
                containerInScrollableContainer.remove(pane);
                labelPanes.remove(pane);
            }
        }
    }
}
