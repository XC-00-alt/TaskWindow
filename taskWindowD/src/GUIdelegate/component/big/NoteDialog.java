package GUIdelegate.component.big;

import GUIdelegate.component.mid.ColorPane;
import GUIdelegate.component.mid.SliderPane;
import GUIdelegate.component.mid.TextAttributePanel;
import model.Note;
import model.NoteUpdateEnum;
import model.TextAttributes;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NoteDialog extends JDialog implements ActionListener, ChangeListener{

    private JTabbedPane tabbedPane;
    private EditStatePanel statePanel;
    private EditInfoPanel infoPanel;
    private Note selectedNote=null;
    private PropertyChangeSupport notifier;
    private int width;
    private int height;
    public static final String CLOSE_DIALOG="close edit dialog";

    public NoteDialog(PropertyChangeListener listener,int width, int height)
    {
        this.width=width;
        this.height=height;
//        setPreferredSize(new Dimension(width,height));
        setSize(width,height);
        setResizable(false);
        setLocation(width/2,height*2/5);
        setTitle("Edit Note");
        notifier=new PropertyChangeSupport(this);
        notifier.addPropertyChangeListener(listener);

        // ref: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TabbedPaneDemoProject/src/components/TabbedPaneDemo.java
        tabbedPane=new JTabbedPane();

        int tabHeight=height*4/5;
        statePanel=new EditStatePanel(width,tabHeight);
        statePanel.addActionListener(this);
        tabbedPane.addTab("State",statePanel);

        infoPanel=new EditInfoPanel(width,tabHeight);
        infoPanel.addActionListener(this);
        infoPanel.addChangeListener(this);
        tabbedPane.addTab("Info",infoPanel);

        add(tabbedPane);
    }

    public void setSelectedNote(Note selectedNote) {
        if(this.selectedNote!=null) this.selectedNote.setSelected(false);
        if(selectedNote!=null) {
            selectedNote.setSelected(true);

            statePanel.setValue(selectedNote.isComplete());
            infoPanel.setValue(selectedNote);
        }
        this.selectedNote = selectedNote;
    }

    public void reset()
    {
        setSelectedNote(null);
        setVisible(false);
    }

    /**
     * KEEP THIS!
     * @param e the window event
     */
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
        try {
            if(selectedNote!=null)
            {
                if(infoPanel.isColorButton(e.getSource()))
                {
                    Color newColor=infoPanel.showColorDialog();
                    selectedNote.setPaperColor(newColor);
                }
                else if(statePanel.isCheckBox(e.getSource()))
                {
                    boolean newComplete=statePanel.isComplete();
                    selectedNote.setComplete(newComplete);
                    statePanel.setComplete(newComplete);
                }
                else
                {
                    infoPanel.checkAttributePanels(selectedNote,e);
                }
            }
        }catch (Exception exception)
        {
            System.out.print(exception.getMessage());
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        try {
            // when open a new edit after finishing last edit with the sliders
            // the dialog will pass the changed value to null object before the new selected note comes in
            if(selectedNote!=null)
            {
                if(infoPanel.isWidthPaneSlider(e.getSource()))
                {
                    int newHalfWidth=infoPanel.getNewWidth();
                    selectedNote.setHalfWidth(newHalfWidth);
                    infoPanel.setNewWidth(newHalfWidth);
                }
                else if(infoPanel.isHeightPaneSlider(e.getSource()))
                {
                    int newHalfHeight=infoPanel.getNewHeight();
                    selectedNote.setHalfHeight(newHalfHeight);
                    infoPanel.setNewHeight(newHalfHeight);
                }
                else if(infoPanel.isRotationPaneSlider(e.getSource()))
                {
                    int newRotation=infoPanel.getNewRotation();
                    selectedNote.setRotationDegree(newRotation);
                    infoPanel.setNewRotation(newRotation);
                }
            }
        }catch (Exception exception)
        {
            System.out.print(exception.getMessage());
        }
    }
}
