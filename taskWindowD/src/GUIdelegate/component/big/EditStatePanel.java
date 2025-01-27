package GUIdelegate.component.big;

import GUIdelegate.component.small.TextFieldWithLabel;
import util.DateRelated;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;

public class EditStatePanel extends JPanel {
    private static final String INCOMPLETE="incomplete";
    private static final String COMPLETE="complete";
    private JCheckBox completeState=new JCheckBox(INCOMPLETE);
    private TextFieldWithLabel startDatePane=new TextFieldWithLabel("start date");
    private TextFieldWithLabel endDatePane=new TextFieldWithLabel("end date");
    public EditStatePanel(int width, int height)
    {
        setSize(width,height);
        setLayout(new FlowLayout());
        addComponents(width,height);
    }

    public void addComponents(int width, int height)
    {
        add(completeState);
        startDatePane.adjustSize(width, height/12);
        endDatePane.adjustSize(width,height/12);
        add(startDatePane);
        add(endDatePane);
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
    public void setValue(boolean complete, Date startDate, Date endDate)
    {
        setComplete(complete);
        startDatePane.setText(DateRelated.getDateString(startDate));
        setEndDate(endDate);
    }
    public void setEndDate(Date endDate)
    {
        endDatePane.setText(DateRelated.getDateString(endDate));
    }
    public void setComplete(boolean complete)
    {
        completeState.setSelected(complete);
        if(complete) completeState.setText(COMPLETE);
        else completeState.setText(INCOMPLETE);
    }
}
