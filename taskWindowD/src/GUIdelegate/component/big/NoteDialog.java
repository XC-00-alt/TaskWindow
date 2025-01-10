package GUIdelegate.component.big;

import GUIdelegate.component.mid.ColorPane;
import GUIdelegate.component.mid.SliderPane;
import model.Note;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NoteDialog extends JDialog implements ActionListener, ChangeListener {
    private Note selectedNote=null;
    private PropertyChangeSupport notifier;
    private int width;
    private int height;

    private ColorPane paperColorPane;
    private SliderPane rotationPane;
    private SliderPane widthPane;
    private SliderPane heightPane;

    public static final String CLOSE_DIALOG="close edit dialog";
//    public static final String PAPER_COLOR="set paper color";
    public static final String UPDATE_NOTE="update note";
    public NoteDialog(PropertyChangeListener listener,int width, int height)
    {
        this.width=width;
        this.height=height*5/4;
//        setPreferredSize(new Dimension(width,height));
        setSize(width,this.height);
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
        rotationPane=new SliderPane("rotation",this.width/4,buttonLen,0,360);
        widthPane=new SliderPane("width",this.width/5,buttonLen,25,this.width);
        heightPane=new SliderPane("height",this.width/5,buttonLen,25,this.width);

        paperColorPane.addActionListener(this);

        rotationPane.addChangeListener(this);
        widthPane.addChangeListener(this);
        heightPane.addChangeListener(this);

        add(paperColorPane);
        add(rotationPane);
        add(widthPane);
        add(heightPane);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==paperColorPane.getColorButton())
        {
            Color newColor=paperColorPane.showColorDialog();

        }
        // if requires undo and redo then not a good setting here
        notifier.firePropertyChange(UPDATE_NOTE,null,selectedNote);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
