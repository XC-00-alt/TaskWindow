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

        infoPanel=new EditInfoPanel(width,height);
        infoPanel.addActionListener(this);
        infoPanel.addChangeListener(this);
        tabbedPane.addTab("Info",infoPanel);

        add(tabbedPane);
    }

    public void setSelectedNote(Note selectedNote) {
        if(this.selectedNote!=null) this.selectedNote.setSelected(false);
        if(selectedNote!=null) {
            selectedNote.setSelected(true);

            infoPanel.setValue(selectedNote);
        }
        this.selectedNote = selectedNote;
    }
//
//    private ColorPane paperColorPane;
//    private SliderPane rotationPane;
//    private SliderPane widthPane;
//    private SliderPane heightPane;
//
//    private TextAttributePanel titlePane;
//
//    private TextAttributePanel descriptionPane;
//

//
//    public NoteDialog(PropertyChangeListener listener,int width, int height)
//    {
//        this.width=width;
//        this.height=height;
////        setPreferredSize(new Dimension(width,height));
//        setSize(width,this.height);
//        setResizable(false);
//        setLocation(width/2,height/2);
//        setTitle("Edit Note");
//        notifier=new PropertyChangeSupport(this);
//        notifier.addPropertyChangeListener(listener);
//
//        int buttonLen=height/12;
//        setComponents(buttonLen);
//    }
//    public void setComponents(int buttonLen)
//    {
//        setLayout(new FlowLayout());
//        paperColorPane=new ColorPane("paper color",buttonLen);
//        rotationPane=new SliderPane("rotation",this.width/4,buttonLen,-180,180);
//        widthPane=new SliderPane("width",this.width/6,buttonLen,25,this.width/4);
//        heightPane=new SliderPane("height",this.width/6,buttonLen,25,this.width/4);
//        titlePane=new TextAttributePanel("title",this.width,this.height/4,buttonLen);
//        descriptionPane=new TextAttributePanel("description",this.width,this.height*3/4,buttonLen);
//
//        paperColorPane.addActionListener(this);
//        titlePane.addActionListener(this);
//        descriptionPane.addActionListener(this);
//
//        rotationPane.addChangeListener(this);
//        widthPane.addChangeListener(this);
//        heightPane.addChangeListener(this);
//
//        add(paperColorPane);
//        add(rotationPane);
//        add(widthPane);
//        add(heightPane);
//        add(titlePane);
////        add(new JSeparator());
//        add(descriptionPane);
//    }
//

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
//    private String contentMsg;
//    private String fontMsg;
//    private String fontColorMsg;
//
//    public void textAttributePanelOp(TextAttributes textAttributes,
//                                     TextAttributePanel textAttributePanel, ActionEvent e)
//    {
//        boolean isDes=selectedNote.getDescriptionAttributes().equals(textAttributes);
//        if(selectedNote==null||
//                !(isDes||selectedNote.getTitleAttributes().equals(textAttributes)))
//        {
//            return;
//        }
//        else if(isDes)
//        {
//            contentMsg=NoteUpdateEnum.DESCRIPTION_CONTENT.toString();
//            fontMsg=NoteUpdateEnum.DESCRIPTION_FONT.toString();
//            fontColorMsg=NoteUpdateEnum.DESCRIPTION_COLOR.toString();
//        }
//        else
//        {
//            contentMsg=NoteUpdateEnum.TITLE_CONTENT.toString();
//            fontMsg=NoteUpdateEnum.TITLE_FONT.toString();
//            fontColorMsg=NoteUpdateEnum.TITLE_COLOR.toString();
//        }
//        if(textAttributePanel.isFontChooser(e.getSource()))
//        {
//            String fontStr=textAttributePanel.getItem();
//            if(!fontStr.equals(textAttributes.getFontName())) {
//                Font oldFont = textAttributes.getFont();
//                textAttributes.setFontName(fontStr);
//                selectedNote.callNotifier(fontMsg, oldFont, textAttributes.getFont());
//            }
//        }
//        else if(textAttributePanel.isColorButton(e.getSource()))
//        {
//            Color oldColor=textAttributes.getFontColor();
//            Color newColor=textAttributePanel.showColorDialog();
//            if(!newColor.equals(oldColor)) {
//                textAttributes.setFontColor(newColor);
//                selectedNote.callNotifier(fontColorMsg, oldColor, textAttributes.getFontColor());
//            }
//        }
//        else if(textAttributePanel.isBoldButton(e.getSource()))
//        {
//            textAttributePanel.reverseBold();
//            boolean newBold=textAttributePanel.isBold();
//            if(newBold!=textAttributes.isFontBold()) {
//                Font oldFont = textAttributes.getFont();
//                textAttributes.setFontBold(newBold);
//                selectedNote.callNotifier(fontMsg, oldFont, textAttributes.getFont());
//            }
//        }
//        else if(textAttributePanel.isConfirmButton(e.getSource())
//                ||textAttributePanel.isCancelButton(e.getSource()))
//        {
//            if(textAttributePanel.isConfirmButton(e.getSource()))
//            {
//                String oldContent=textAttributes.getTextContent();
//                String newContent=textAttributePanel.getText();
//                if(!oldContent.equals(newContent)) {
//                    textAttributes.setTextContent(textAttributePanel.getText());
//                    selectedNote.callNotifier(contentMsg, oldContent, textAttributes.getTextContent());
//                }
//            }
//            textAttributePanel.setText(textAttributes.getTextContent());
//            textAttributePanel.showEditButtons(false);
//        }
//    }
//
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
