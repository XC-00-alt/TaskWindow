package GUIdelegate.component.big;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TopMenuBar extends JMenuBar{
    private JMenu directoryMenu=new JMenu("Directory");
    private JMenuItem loadDirectory=new JMenuItem("Load");
    private JMenuItem saveDirectory=new JMenuItem("Save");

    private JMenu categoryLabelMenu=new JMenu("Label");
    private JMenuItem createLabel=new JMenuItem("Create");
    private JMenuItem manageLabel=new JMenuItem("Manage");
    private JButton noteAdder=new JButton("+Note");
    public TopMenuBar(ActionListener al)
    {
        directoryMenu.add(loadDirectory);
        directoryMenu.add(saveDirectory);
        categoryLabelMenu.add(createLabel);
        categoryLabelMenu.add(manageLabel);

        loadDirectory.addActionListener(al);
        saveDirectory.addActionListener(al);
        noteAdder.addActionListener(al);

        this.add(directoryMenu);
        this.add(categoryLabelMenu);
        this.add(noteAdder);
    }

    public JButton getNoteAdder() {
        return noteAdder;
    }
    public boolean isSaveDirectory(Object o)
    {
        return saveDirectory.equals(o);
    }
    public boolean isqLoadDirectory(Object o)
    {
        return loadDirectory.equals(o);
    }
}
