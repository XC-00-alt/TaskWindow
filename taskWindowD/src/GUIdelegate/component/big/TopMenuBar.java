package GUIdelegate.component.big;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TopMenuBar extends JMenuBar{
    private JMenu directoryMenu=new JMenu("Directory");
    private JMenuItem loadDirectory=new JMenuItem("Load");
    private JMenuItem saveDirectory=new JMenuItem("Save");
    private JButton noteAdder=new JButton("+Note");
    public TopMenuBar(ActionListener al)
    {
        directoryMenu.add(loadDirectory);
        directoryMenu.add(saveDirectory);

        loadDirectory.addActionListener(al);
        saveDirectory.addActionListener(al);
        noteAdder.addActionListener(al);

        this.add(directoryMenu);
        this.add(noteAdder);
    }

    public JButton getNoteAdder() {
        return noteAdder;
    }
    public boolean isSaveDirectory(Object o)
    {
        return saveDirectory.equals(o);
    }
}
