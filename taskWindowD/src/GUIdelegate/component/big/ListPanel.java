package GUIdelegate.component.big;

import model.Note;
import model.QuadrantEnum;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ListPanel extends JScrollPane  {
    private JTree t;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tasks");
    private DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("1");
    private DefaultMutableTreeNode node=new DefaultMutableTreeNode(new Note(0,0, QuadrantEnum.IMPORTANT_NON_URGENT));
    private DefaultMutableTreeNode root2=new DefaultMutableTreeNode("2");
    private JPanel container=new JPanel();
    public ListPanel(Dimension d)
    {
        this.setPreferredSize(d);
        root1.add(node);
        root.add(root1);
        root.add(root2);
        t=new JTree(root);
        container.add(t,BorderLayout.EAST);
        setViewportView(container);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }
    @Override
    public void paintComponent(Graphics g)
    {
//        g.setColor(Color.CYAN);
//        g.fillRect(0,0,getWidth(),getHeight());
    }
}
