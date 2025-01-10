package GUIdelegate.component.big;

import GUIdelegate.component.mid.ColorPane;
import model.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NoteDialog extends JDialog {
    private Note selectedNote=null;
    private PropertyChangeSupport notifier;
    private int width;
    private int height;

    private ColorPane paperColorPane;

    public static final String CLOSE_DIALOG="close edit dialog";
    public NoteDialog(PropertyChangeListener listener,int width, int height)
    {
        this.width=width;
        this.height=height;
//        setPreferredSize(new Dimension(width,height));
        setSize(width,height);
        setResizable(false);
        setLocation(width/2,height/2);
        setTitle("Edit Note");
        notifier=new PropertyChangeSupport(this);
        notifier.addPropertyChangeListener(listener);

        int buttonLen=height/12;
        setComponents(buttonLen);
    }
    public void setComponents(int buttonLen)
    {
        setLayout(new FlowLayout());
        paperColorPane=new ColorPane("paper color",buttonLen);
        add(paperColorPane);
    }

    public void setSelectedNote(Note selectedNote) {
        if(this.selectedNote!=null) this.selectedNote.setSelected(false);
        if(selectedNote!=null) {
            selectedNote.setSelected(true);
            paperColorPane.setColor(selectedNote.getPaperColor());
        }
        this.selectedNote = selectedNote;
    }
    public void reset()
    {
        setSelectedNote(null);
        setVisible(false);
    }

    // ref:https://blog.csdn.net/l1o3v1e4ding/article/details/134852648
    @Override
    protected void processWindowEvent(WindowEvent e) {
        if(e.getID()==WindowEvent.WINDOW_CLOSING)
        {
            notifier.firePropertyChange(CLOSE_DIALOG,selectedNote,null);
        }
        super.processWindowEvent(e);
    }
}
