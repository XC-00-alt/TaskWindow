package GUIdelegate.component.big;

import model.Note;
import model.QuadrantEnum;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class ListPanel extends JScrollPane  {
    private JTree t;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tasks");
    private DefaultMutableTreeNode q1 = new DefaultMutableTreeNode(QuadrantEnum.IMPORTANT_NON_URGENT);
    private DefaultMutableTreeNode q2=new DefaultMutableTreeNode(QuadrantEnum.IMPORTANT_URGENT);
    private DefaultMutableTreeNode q3 = new DefaultMutableTreeNode(QuadrantEnum.UNIMPORTANT_URGENT);
    private DefaultMutableTreeNode q4=new DefaultMutableTreeNode(QuadrantEnum.UNIMPORTANT_NON_URGENT);
    private JPanel container=new JPanel();
    public ListPanel(Dimension d)
    {
        this.setPreferredSize(d);

        root.add(q1);
        root.add(q2);
        root.add(q3);
        root.add(q4);
        t=new JTree(root);
        t.setBackground(Color.MAGENTA);

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) t.getCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);

        container.setBackground(Color.MAGENTA);
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(t);
        setViewportView(container);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }
    public void addNote(Note note)
    {
        DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(note);
        Integer code=note.getQuadrantCode().getCode();
//        System.out.println(code);
//        if(code==1) q1.add(newNode);
//        else if(code==3) q3.add(newNode);
//        else if(code==4) q4.add(newNode);
//        else q2.add(newNode);
        switch (code)//switch has problem with int/Integer
        {
            case 1: {
                q1.add(newNode);
                break;
            }
            case 3: {
                q3.add(newNode);
                break;
            }
            case 4: {
                q4.add(newNode);
                break;
            }
            default:q2.add(newNode);
        }
    }
    @Override
    public void paintComponent(Graphics g)
    {
//        g.setColor(Color.CYAN);
//        g.fillRect(0,0,getWidth(),getHeight());
    }
}
