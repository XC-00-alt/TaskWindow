package GUIdelegate.component.big;

import GUIdelegate.component.mid.ColorPane;
import GUIdelegate.component.mid.SliderPane;
import GUIdelegate.component.mid.TextPanel;
import model.Note;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NoteDialog extends JDialog implements ActionListener, ChangeListener {
    private Note selectedNote=null;
    private PropertyChangeSupport notifier;
    private int width;
    private int height;

    private ColorPane paperColorPane;
    private SliderPane rotationPane;
    private SliderPane widthPane;
    private SliderPane heightPane;

    private TextPanel titlePane;

    public static final String CLOSE_DIALOG="close edit dialog";
//    public static final String PAPER_COLOR="set paper color";
//    public static final String UPDATE_NOTE="update note";
    public NoteDialog(PropertyChangeListener listener,int width, int height)
    {
        this.width=width;
        this.height=height*5/4;
//        setPreferredSize(new Dimension(width,height));
        setSize(width,this.height);
        setResizable(false);
        setLocation(width/2,height/2);
        setTitle("Edit Note");
        notifier=new PropertyChangeSupport(this);
        notifier.addPropertyChangeListener(listener);

        int buttonLen=height/12;
        setComponents(buttonLen);
    }
    public void setComponents(int buttonLen)
    {
        setLayout(new FlowLayout());
        paperColorPane=new ColorPane("paper color",buttonLen);
        rotationPane=new SliderPane("rotation",this.width/4,buttonLen,0,360);
        widthPane=new SliderPane("width",this.width/6,buttonLen,25,this.width/4);
        heightPane=new SliderPane("height",this.width/6,buttonLen,25,this.width/4);
        titlePane=new TextPanel("title",this.width,this.height/4,buttonLen);

        paperColorPane.addActionListener(this);
        titlePane.addActionListener(this);

        rotationPane.addChangeListener(this);
        widthPane.addChangeListener(this);
        heightPane.addChangeListener(this);

        add(paperColorPane);
        add(rotationPane);
        add(widthPane);
        add(heightPane);
        add(titlePane);
    }

    public void setSelectedNote(Note selectedNote) {
        if(this.selectedNote!=null) this.selectedNote.setSelected(false);
        if(selectedNote!=null) {
            selectedNote.setSelected(true);
            paperColorPane.setColor(selectedNote.getPaperColor());
            rotationPane.setValue(selectedNote.getRotationDegree());
            widthPane.setValue(selectedNote.getWidth()/2);
            heightPane.setValue(selectedNote.getHeight()/2);
            titlePane.setValue(selectedNote.getTitleFontName(),
                    selectedNote.getTitleColor());
//            titlePane.setSelectedFont(selectedNote.getTitleFontName());
//            System.out.println(selectedNote.getTitleFontName());
        }
        this.selectedNote = selectedNote;
    }
    public void reset()
    {
        setSelectedNote(null);
        setVisible(false);
    }

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
                if(e.getSource()==paperColorPane.getColorButton())
                {
                    Color newColor=paperColorPane.showColorDialog();
                    selectedNote.setPaperColor(newColor);
                }
//            else if(e.getSource()==titlePane.getFontChooser())
                else if(titlePane.isFontChooser(e.getSource()))
                {
                    String fontStr=titlePane.getItem();
                    selectedNote.setTitleFontName(fontStr);
                }
                else if(titlePane.isColorButton(e.getSource()))
                {
                    Color newColor=titlePane.showColorDialog();
                    selectedNote.setTitleColor(newColor);
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
                if(e.getSource()==widthPane.getValueSlider())
                {
                    int newHalfWidth=widthPane.getValue();
                    selectedNote.setHalfWidth(newHalfWidth);
                    widthPane.setValue(newHalfWidth);
                }
                else if(e.getSource()==heightPane.getValueSlider())
                {
                    int newHalfHeight=heightPane.getValue();
                    selectedNote.setHalfHeight(newHalfHeight);
                    heightPane.setValue(newHalfHeight);
                }
                else if(e.getSource()==rotationPane.getValueSlider())
                {
                    int newRotation=rotationPane.getValue();
                    selectedNote.setRotationDegree(newRotation);
                    rotationPane.setValue(newRotation);
                }
            }
        }catch (Exception exception)
        {
            System.out.print(exception.getMessage());
        }
    }
}
