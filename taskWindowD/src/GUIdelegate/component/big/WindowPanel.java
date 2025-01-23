package GUIdelegate.component.big;

import GUIdelegate.component.mid.DescriptionDialog;
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
    // this is only used for the convenience of the process when dragging and dropping
    // not a strong requirement for the selected note among the list
    // since painting selection doesn't require it
//    private Note selectedNote;
    private TaskQuadrant taskQuadrant;
//    private Color IMPORTANT_URGENT=new Color(0xD8FFE0);
//    private Color IMPORTANT_NON_URGENT=new Color(0xFFF3C3);
//    private Color UNIMPORTANT_URGENT=new Color(0xD1E7FF);
//    private Color UNIMPORTANT_NON_URGENT=new Color(0xFFD5D5);
    private Color IMPORTANT_URGENT=new Color(0xFFF3C3);
    private Color IMPORTANT_NON_URGENT=new Color(0xD8FFE0);
    private Color UNIMPORTANT_URGENT=new Color(0xFFE4C5);
    private Color UNIMPORTANT_NON_URGENT=new Color(0xFFD5D5);

    private DescriptionDialog descriptionDialog;
    private PropertyChangeSupport notifier;
    public static final String OPEN_POPUPMENU="open win-noteMenu";
    public static final String CLOSE_POPUPMENU="close win-noteMenu";

    public static final String SELECT_NOTE="select note in the window";

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
        setMouseAdapter();
        notifier=new PropertyChangeSupport(this);
        notifier.addPropertyChangeListener(propertyChangeListener);
        setupDescriptionDialog(width,height);
    }
    public void setupDescriptionDialog(int width,int height)
    {
        descriptionDialog=new DescriptionDialog(height/2,width/2);
        descriptionDialog.setVisible(false);
        descriptionDialog.setLocation(width*16/15,height/2);
    }
    public boolean addNote(Note note) {
        return taskQuadrant.add(note);
    }
    public boolean removeNote(Note note)
    {
        return taskQuadrant.remove(note);
    }

    public Note getSelectedNote() {
        return taskQuadrant.getSelectedNote();
    }
    private void setSelectedNotePrivate(Note note)
    {
        Note oldNote=setSelectedNote(note);
        notifier.firePropertyChange(SELECT_NOTE,oldNote,taskQuadrant.getSelectedNote());
    }
    public Note setSelectedNote(Note note)
    {
        Note oldNote=taskQuadrant.getSelectedNote();
        taskQuadrant.setSelectedNote(note);
        descriptionDialog.setVisible(note != null);
        return oldNote;
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

//        if(descriptionDialog.isVisible()) descriptionDialog.repaint();
    }
    public void repaintDescriptionDialog()
    {
        descriptionDialog.setValue(
        getSelectedNote().getDescriptionAttributes().getTextContent(),
                getSelectedNote().getDescriptionAttributes().getFontColor(),
                getSelectedNote().getPaperColor(),
                getSelectedNote().getDescriptionAttributes().getFont());
        descriptionDialog.repaint();
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

    public int getNewNoteId()
    {
        return taskQuadrant.getNewId();
    }

    // flags whether noteMenu is requested by the listPanel
    private boolean listRequested =false;

    // flags whether noteMenu is requested by this windowPanel
    private boolean menuPopUp =false;

    private boolean dialogInAction=false;

    public void setListRequested(boolean listRequested) {
        this.listRequested = listRequested;
    }

    public void setMenuPopUp(boolean menuPopUp) {
        this.menuPopUp = menuPopUp;
    }

    public void setDialogInAction(boolean dialogInAction) {
        this.dialogInAction = dialogInAction;
    }

    /**
     * A private MouseAdapter class
     */
    private class DragMouseAdapter extends MouseAdapter {
        private Point startPoint;
        private Point endPoint;


        @Override
        public void mouseMoved(MouseEvent e) {
            if(!menuPopUp&&!listRequested
                    &&!dialogInAction
            )
            {
                int i=taskQuadrant.getNoteList().size() - 1;
                for (; i > -1; i--) {
                    Note currNote = taskQuadrant.getNoteList().get(i);
                    if (currNote.isInRange(e.getPoint())) {
                        setSelectedNote(currNote);
//                        descriptionDialog.setVisible(true);
                        descriptionDialog.setValue(currNote.getDescriptionAttributes().getTextContent(),
                                currNote.getDescriptionAttributes().getFontColor(),
                                currNote.getPaperColor(),
                                currNote.getDescriptionAttributes().getFont());
                        break;
                    }
                }
                if(i<=-1) {
                    if(getSelectedNote()!=null) setSelectedNote(null);
//                    descriptionDialog.setVisible(false);
                }
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            descriptionDialog.setVisible(false);
            // if it's handled at the release that it's a right click
            // then the noteMenu that is shown on the screen should be reset when a new press is coming
            if(menuPopUp || listRequested)
            {
                // these 2 boolean flags are related to the state of noteMenu
                notifier.firePropertyChange(CLOSE_POPUPMENU,true,false);
            }
            else if(dialogInAction)
            {
                notifier.firePropertyChange(NoteDialog.CLOSE_DIALOG,getSelectedNote(),null);
            }

            for (int i = taskQuadrant.getNoteList().size() - 1; i > -1; i--) {
                Note currNote = taskQuadrant.getNoteList().get(i);
                if (currNote.isInRange(e.getPoint())) {
                    startPoint = e.getPoint();

                    setSelectedNotePrivate(currNote);

                    break;
                } else System.out.println("nah");
            }
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Note selectedNote=taskQuadrant.getSelectedNote();
            if (selectedNote!= null && startPoint != null) {
                endPoint = e.getPoint();

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
            Note selectedNote=taskQuadrant.getSelectedNote();
            if (selectedNote != null && startPoint != null) {

                endPoint = e.getPoint();
                Point vector = new Point((int) (endPoint.getX() - startPoint.getX() + selectedNote.getCentre().getX()),
                        (int) (endPoint.getY() - startPoint.getY() + selectedNote.getCentre().getY()));
                selectedNote.setCentre(vector);
                // if it's a right click
                if(e.isPopupTrigger())
                {
                    // set popUp flag true
                    menuPopUp =true;

                    // show the popUp menu
                    notifier.firePropertyChange(OPEN_POPUPMENU,null,e);

                    // don't reset the selection just yet since we wish to see the note being selected
                    // when we edit or delete it
                }
                // if not a right click then it's the Drop in Drag and Drop
                else
                {
                    // reset the selection
                    setSelectedNotePrivate(null);
                    repaint();
                }
                startPoint = null;
                endPoint = null;
            }
        }
    }
}
