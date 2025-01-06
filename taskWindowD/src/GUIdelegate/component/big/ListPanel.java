package GUIdelegate.component.big;

import model.Note;
import model.QuadrantEnum;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ListPanel extends JScrollPane  {
    private JTree t1;
    private JTree t2;
    private DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("1");
    private DefaultMutableTreeNode node=new DefaultMutableTreeNode(new Note(0,0, QuadrantEnum.IMPORTANT_NON_URGENT));
    private DefaultMutableTreeNode root2=new DefaultMutableTreeNode("2");
    private JPanel container=new JPanel();
    public ListPanel()
    {
        root1.add(node);
        t1=new JTree(root1);
        t2=new JTree(root2);
        BoxLayout boxLayout=new BoxLayout(container,BoxLayout.Y_AXIS);
        container.setLayout(boxLayout);
        container.add(t1);
        container.add(new JSeparator());
        container.add(t2);
        setViewportView(container);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.CYAN);
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
