package GUIdelegate.component.big;

import model.Note;
import model.QuadrantEnum;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ListPanel extends JScrollPane{
    // again, only for the sake of referencing
    private Note selectedNote;
    private JTree jTree;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tasks");
    private DefaultMutableTreeNode[] quadrantNodes=new DefaultMutableTreeNode[4];
    private JPanel container=new JPanel();
    private TreeMouseAdapter treeMouseAdapter=new TreeMouseAdapter();
    private PropertyChangeSupport notifier;
    public static final String SELECT_NOTE="select note on the list";
    public ListPanel(PropertyChangeListener propertyChangeListener,Dimension d)
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
        jTree =new JTree(root);
//        jTree.addTreeSelectionListener(this);
        jTree.setBackground(Color.MAGENTA);
//        https://docs.oracle.com/javase/tutorial/uiswing/components/tree.html#display
//        jTree.setEditable(true);
//        jTree.getSelectionModel().setSelectionMode
//                (TreeSelectionModel.SINGLE_TREE_SELECTION);
//        jTree.setShowsRootHandles(true);

        jTree.addMouseListener(treeMouseAdapter);

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jTree.getCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);

        notifier=new PropertyChangeSupport(this);
        notifier.addPropertyChangeListener(propertyChangeListener);

        container.setBackground(Color.MAGENTA);
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(jTree);
        setViewportView(container);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }
    public void deleteNote(Note note)
    {
//        quadrantNodes[oldVal-1].remove(note.getNode());
        note.getNode().removeFromParent();
    }
    public void changeNote(Note note,Integer oldVal,Integer newVal)
    {
        deleteNote(note);
        quadrantNodes[newVal-1].add(note.getNode());
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
        // UI needs to be updated
        jTree.updateUI();
        // and these have to be called again, otherwise UI will be default again
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jTree.getCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);
    }

//    /**  implements TreeSelectionListener */
//    @Override
//    public void valueChanged(TreeSelectionEvent e) {
//        //ref:https://blog.csdn.net/dd_Mr/article/details/122262024
//        DefaultMutableTreeNode node=(DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
//        System.out.println(node.getUserObject().getClass());
//        System.out.println(node.getUserObject().toString());
//    }
    public void showSelectedNote(Note selectedNote)
    {
        clearSelectedRow();
        if(selectedNote!=null&&selectedNote.isSelected())
        {
            System.out.println("listPane call");
            DefaultMutableTreeNode node =selectedNote.getNode();
            TreePath path=new TreePath(node.getPath());
            jTree.setSelectionPath(path);
        }
    }
    public Note getSelectedNote(MouseEvent event)
    {
//        if(event.isPopupTrigger()) {
//
//        }
        TreePath selPath = jTree.getPathForLocation(event.getX(), event.getY());
        if (selPath != null) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
            int selRow = jTree.getRowForLocation(event.getX(), event.getY());
            jTree.setSelectionRow(selRow);
            if (node.getUserObject().getClass() == Note.class) {
                return (Note) node.getUserObject();
            }
        }
        return null;
    }
    public void clearSelectedRow()
    {
        jTree.setSelectionRow(-1);
    }
    /**
     * A private MouseAdapter class
     */
    private class TreeMouseAdapter extends MouseAdapter
    {
        //ref: https://www.tutorialspoint.com/how-to-implement-the-mouse-right-click-on-each-node-of-jtree-in-java
        //https://www.experts-exchange.com/questions/21259761/Right-Click-on-JTree.html
        @Override
        public void mouseReleased(MouseEvent event)
        {
            notifier.firePropertyChange(SELECT_NOTE, null, event);
        }
    }
}
