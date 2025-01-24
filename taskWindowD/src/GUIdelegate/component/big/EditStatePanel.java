package GUIdelegate.component.big;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EditStatePanel extends JPanel {
    private static final String INCOMPLETE="incomplete";
    private static final String COMPLETE="complete";
    private JCheckBox completeState=new JCheckBox(INCOMPLETE);
    public EditStatePanel(int width, int height)
    {
        setSize(width,height);
        addComponents();
    }

    public void addComponents()
    {
        add(completeState);
    }
    public void addActionListener(ActionListener al)
    {
        completeState.addActionListener(al);
    }
    public boolean isCheckBox(Object o)
    {
        return completeState.equals(o);
    }
    public boolean isComplete()
    {
        return completeState.isSelected();
    }
    public void setValue(boolean complete)
    {
        setComplete(complete);
    }
    public void setComplete(boolean complete)
    {
        completeState.setSelected(complete);
        if(complete) completeState.setText(COMPLETE);
        else completeState.setText(INCOMPLETE);
    }
}
