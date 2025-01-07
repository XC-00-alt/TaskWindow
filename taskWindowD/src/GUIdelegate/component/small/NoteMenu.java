package GUIdelegate.component.small;

import model.Note;

import javax.swing.*;

public class NoteMenu extends JPopupMenu {
    private final JMenuItem itemEdit=new JMenuItem("Edit");
    private final JMenuItem itemDelete=new JMenuItem("Delete");
    private Note selectedNote=null;
    public NoteMenu()
    {
        this.add(itemEdit);
        this.add(itemDelete);
    }

    public void setSelectedNote(Note selectedNote) {
        this.selectedNote = selectedNote;
    }
}
