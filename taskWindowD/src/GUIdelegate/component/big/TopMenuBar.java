package GUIdelegate.component.big;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TopMenuBar extends JMenuBar{
    private JButton noteAdder=new JButton("+note");
    public TopMenuBar(ActionListener al)
    {
        noteAdder.addActionListener(al);
        this.add(noteAdder);
    }

    public JButton getNoteAdder() {
        return noteAdder;
    }
}
