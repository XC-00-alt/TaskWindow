package GUIdelegate.component.big;

import GUIdelegate.delegate.GraphicDelegate;
import model.Note;
import model.QuadrantEnum;
import model.TaskQuadrant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WindowPanel extends JPanel {
    private TaskQuadrant taskQuadrant;
    private Color IMPORTANT_URGENT=new Color(0xFFE562);
    private Color IMPORTANT_NON_URGENT=new Color(0xA6EE9D);
    private Color UNIMPORTANT_URGENT=new Color(0xFFB662);
    private Color UNIMPORTANT_NON_URGENT=new Color(0xEE9D9D);

    private DragMouseAdapter mouseAdapter;
    public void setMouseAdapter()
    {
        mouseAdapter=new DragMouseAdapter();
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }
    public WindowPanel()
    {
        taskQuadrant=new TaskQuadrant();
        setMouseAdapter();
    }
    public boolean addNote(Note note) {
        return taskQuadrant.add(note);
    }
    public boolean removeNote(Note note)
    {
        return taskQuadrant.remove(note);
    }
//    public void paintAgain()
//    {
//        this.repaint();
//    }
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
        private Note selectedNote;

        @Override
        public void mousePressed(MouseEvent e) {
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
                Point vector = new Point((int) (endPoint.getX() - startPoint.getX() + selectedNote.getCentre().getX()),
                        (int) (endPoint.getY() - startPoint.getY() + selectedNote.getCentre().getY()));
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
                selectedNote.setSelected(false);
                selectedNote = null;
                startPoint = null;
                endPoint = null;
            }
        }
    }
}
