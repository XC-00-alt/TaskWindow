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
        this.selectedNote = selectedNote;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==itemDelete) System.out.println("DEL");
        else if(e.getSource()==itemEdit) System.out.println("EDI");
    }
}
