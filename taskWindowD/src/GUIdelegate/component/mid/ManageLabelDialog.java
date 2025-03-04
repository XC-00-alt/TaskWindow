package GUIdelegate.component.mid;

import GUIdelegate.component.small.CategoryLabelPane;
import model.CategoryLabel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class ManageLabelDialog extends JDialog {
    private JScrollPane container=new JScrollPane();
    private JPanel containerInScrollableContainer=new JPanel();
    private List<CategoryLabelPane> labelPanes;
    private int LABEL_PANE1_WIDTH;
    private int BUTTON_LEN;
    public ManageLabelDialog(int width,int height)
    {
        setTitle("manage labels");
        setSize(new Dimension(width, height));
        setResizable(false);
        setLocation(width/2,height*2/5);

        container.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        container.setViewportView(containerInScrollableContainer);
        add(container);
        LABEL_PANE1_WIDTH=width/3;
        BUTTON_LEN=width/10;
    }
    public void addLabels(List<CategoryLabel> labelList)
    {
        for(CategoryLabel label:labelList)
        {
            CategoryLabelPane categoryLabelPane=new CategoryLabelPane(LABEL_PANE1_WIDTH,BUTTON_LEN,true,null);
            categoryLabelPane.setCategoryLabel(label);
            containerInScrollableContainer.add(categoryLabelPane);
        }
    }
}
