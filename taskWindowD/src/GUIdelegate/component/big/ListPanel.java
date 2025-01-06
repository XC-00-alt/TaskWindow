package GUIdelegate.component.big;

import model.Note;
import model.QuadrantEnum;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class ListPanel extends JScrollPane  {
    private JTree t;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tasks");
    private DefaultMutableTreeNode[] quadrantNodes=new DefaultMutableTreeNode[4];
    private JPanel container=new JPanel();
    public ListPanel(Dimension d)
    {
        this.setPreferredSize(d);
        quadrantNodes[0]=new DefaultMutableTreeNode(QuadrantEnum.IMPORTANT_NON_URGENT);
        quadrantNodes[1]=new DefaultMutableTreeNode(QuadrantEnum.IMPORTANT_URGENT);
        quadrantNodes[2]=new DefaultMutableTreeNode(QuadrantEnum.UNIMPORTANT_URGENT);
        quadrantNodes[3]=new DefaultMutableTreeNode(QuadrantEnum.UNIMPORTANT_NON_URGENT);

        for(DefaultMutableTreeNode node:quadrantNodes)
        {
            root.add(node);
        }
        t=new JTree(root);
        t.setBackground(Color.MAGENTA);
////        https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html#display
//        t.setEditable(true);
//        t.getSelectionModel().setSelectionMode
//                (TreeSelectionModel.SINGLE_TREE_SELECTION);
//        t.setShowsRootHandles(true);

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
    public void deleteNote(Note note,Integer oldVal)
    {
        quadrantNodes[oldVal-1].remove(note.getNode());
        System.out.println(quadrantNodes[oldVal-1].getChildCount());
//        note.getNode().removeFromParent();
    }
    public void changeNote(Note note,Integer oldVal,Integer newVal)
    {
        deleteNote(note,oldVal);
        quadrantNodes[newVal-1].add(note.getNode());
        t.repaint();
//        addNote(note);
    }
    public void addNote(Note note)
    {
        DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(note);
        Integer code=note.getQuadrantCode().getCode();

        int qId2=1;
        if(code>0&&code<5) qId2=code-1;
        quadrantNodes[qId2].add(newNode);
        note.setNode(newNode);
    }
    @Override
    public void paintComponent(Graphics g)
    {
//        System.out.println("rowCount"+t.getRowCount());
//        for(int i=0;i<t.getRowCount();i++)
//        {
//            if(t.isExpanded(i))
//            {
//                t.collapseRow(i);
//                t.expandRow(i);
//            }
//        }
//        g.setColor(Color.CYAN);
//        g.fillRect(0,0,getWidth(),getHeight());
    }
}
