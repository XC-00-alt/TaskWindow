package GUIdelegate.component.big;

import GUIdelegate.component.small.NoteMenu;
import GUIdelegate.delegate.GraphicDelegate;
import model.Note;
import model.QuadrantEnum;
import model.TaskQuadrant;
import util.CalculationWithBound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WindowPanel extends JPanel {
    private Note selectedNote;
    private TaskQuadrant taskQuadrant;
    private Color IMPORTANT_URGENT=new Color(0xFFE562);
    private Color IMPORTANT_NON_URGENT=new Color(0xA6EE9D);
    private Color UNIMPORTANT_URGENT=new Color(0xFFB662);
    private Color UNIMPORTANT_NON_URGENT=new Color(0xEE9D9D);

//    private NoteMenu noteMenu=new NoteMenu();
    private PropertyChangeSupport notifier;
    public static final String OPEN_POPUPMENU="open win-noteMenu";
    public static final String CLOSE_POPUPMENU="close win-noteMenu";

    private DragMouseAdapter mouseAdapter;
    public void setMouseAdapter()
    {
        mouseAdapter=new DragMouseAdapter();
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }
    public WindowPanel(PropertyChangeListener propertyChangeListener,int width,int height)
    {
        this.setPreferredSize(new Dimension(width,height));
        taskQuadrant=new TaskQuadrant();
//        add(noteMenu);
        setMouseAdapter();
        notifier=new PropertyChangeSupport(this);
        notifier.addPropertyChangeListener(propertyChangeListener);
    }
    public boolean addNote(Note note) {
        return taskQuadrant.add(note);
    }
    public boolean removeNote(Note note)
    {
        return taskQuadrant.remove(note);
    }

    public Note getSelectedNote() {
        return selectedNote;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        int halfWidth=getWidth()/2;
        int halfHeight=getHeight()/2;
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        // Q2
        g.setColor(IMPORTANT_URGENT);
        g.fillRect(0,0,halfWidth,halfHeight);
        // Q1
        g.setColor(IMPORTANT_NON_URGENT);
        g.fillRect(getWidth()-halfWidth,0,halfWidth,halfHeight);
        // Q3
        g.setColor(UNIMPORTANT_URGENT);
        g.fillRect(0,getHeight()-halfHeight,halfWidth,halfHeight);
        // Q4
        g.setColor(UNIMPORTANT_NON_URGENT);
        g.fillRect(getWidth()-halfWidth,getHeight()-halfHeight,halfWidth,halfHeight);

        // draws notes
        GraphicDelegate.drawNotes(g,taskQuadrant.getNoteList());
    }
    public QuadrantEnum getQuadrantCode(int x, int y)
    {
        int halfWidth=getWidth()/2;
        int halfHeight=getHeight()/2;
        if(x<=halfWidth)
        {
            if(y<=halfHeight) return QuadrantEnum.IMPORTANT_URGENT;
            else return QuadrantEnum.UNIMPORTANT_URGENT;
        }
        else
        {
            if(y<=halfHeight) return QuadrantEnum.IMPORTANT_NON_URGENT;
            else return QuadrantEnum.UNIMPORTANT_NON_URGENT;
        }
    }



    /**
     * A private MouseAdapter class
     */
    private class DragMouseAdapter extends MouseAdapter {
        private Point startPoint;
        private Point endPoint;

        private boolean popUp=false;

//        private JComponent invokerComp;
//        public DragMouseAdapter(JComponent jComponent)
//        {
//            this.invokerComp =jComponent;
//        }
        @Override
        public void mousePressed(MouseEvent e) {
            // if it's handled at the release that it's a right click
            // then the popUp menu should be reset when a new press is coming
            if(popUp)
            {
//                noteMenu.reset();
                notifier.firePropertyChange(CLOSE_POPUPMENU,true,false);
                selectedNote.setSelected(false);
                selectedNote=null;
                popUp=false;
            }

            for (int i = taskQuadrant.getNoteList().size() - 1; i > -1; i--) {
                Note currNote = taskQuadrant.getNoteList().get(i);
                if (currNote.isInRange(e.getPoint())) {
                    currNote.setSelected(true);
                    startPoint = e.getPoint();
                    selectedNote = currNote;
                    break;
                } else System.out.println("nah");
            }
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (selectedNote != null && startPoint != null) {
                endPoint = e.getPoint();
//                Point vector = new Point((int) (endPoint.getX() - startPoint.getX() + selectedNote.getCentre().getX()),
//                        (int) (endPoint.getY() - startPoint.getY() + selectedNote.getCentre().getY()));
                Point vector= CalculationWithBound.minusOp(endPoint,startPoint);
                vector=CalculationWithBound.addOp(vector,selectedNote.getCentre());
                vector=CalculationWithBound.refineOp(vector,getWidth(),getHeight());
                selectedNote.setCentre(vector);
                selectedNote.setQuadrantCode(getQuadrantCode(vector.x, vector.y));
                startPoint = endPoint;
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (selectedNote != null && startPoint != null) {

                endPoint = e.getPoint();
                Point vector = new Point((int) (endPoint.getX() - startPoint.getX() + selectedNote.getCentre().getX()),
                        (int) (endPoint.getY() - startPoint.getY() + selectedNote.getCentre().getY()));
                selectedNote.setCentre(vector);
                // if it's a right click
                if(e.isPopupTrigger())
                {
                    // set popUp flag true
                    popUp=true;

                    // show the popUp menu
                    notifier.firePropertyChange(OPEN_POPUPMENU,null,e);
                    System.out.println("!");
//                    noteMenu.show(e.getComponent(),e.getX(),e.getY());

                    // set the menu target on the selected note
//                    noteMenu.setSelectedNote(selectedNote);

                    // don't reset the selection just yet since we wish to see the note being selected
                    // when we edit or delete it
                }
                // if not a right click then it's the Drop in Drag and Drop
                else
                {
                    // reset the selection
                    selectedNote.setSelected(false);
                    selectedNote = null;
                    repaint();
                }
                startPoint = null;
                endPoint = null;
            }
        }
    }
}
