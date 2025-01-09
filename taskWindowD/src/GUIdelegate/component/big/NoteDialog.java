package GUIdelegate.component.big;

import model.Note;

import javax.swing.*;
import java.awt.*;

public class NoteDialog extends JDialog {
    private Note selectedNote=null;
    private int width;
    private int height;
    public NoteDialog(int width,int height)
    {
        this.width=width;
        this.height=height;
//        setPreferredSize(new Dimension(width,height));
        setSize(width,height);
        setResizable(false);
        setLocation(width/2,height/2);
    }

    public void setSelectedNote(Note selectedNote) {
        if(this.selectedNote!=null) this.selectedNote.setSelected(false);
        if(selectedNote!=null) selectedNote.setSelected(true);
        this.selectedNote = selectedNote;
    }
    public void reset()
    {
        setSelectedNote(null);
        setVisible(false);
    }
}
