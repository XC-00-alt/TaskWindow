package GUIdelegate.component.small;

import model.Note;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteMenu extends JPopupMenu implements ActionListener {
    private final JMenuItem itemEdit=new JMenuItem("Edit");
    private final JMenuItem itemDelete=new JMenuItem("Delete");

    private Note selectedNote=null;
    public NoteMenu()
    {
        itemEdit.addActionListener(this);
        this.add(itemEdit);
        itemDelete.addActionListener(this);
        this.add(itemDelete);
    }

    public JMenuItem getItemEdit() {
        return itemEdit;
    }

    public JMenuItem getItemDelete() {
        return itemDelete;
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==itemDelete)
        {
            selectedNote.delete();
        }
        else if(e.getSource()==itemEdit)
        {
            selectedNote.summonEdit();
            // potential bug here
//            selectedNote.setSelected(true);
        }
        selectedNote=null;
    }
}
