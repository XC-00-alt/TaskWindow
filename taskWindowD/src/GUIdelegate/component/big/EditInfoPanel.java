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

public class EditInfoPanel extends JPanel //implements ActionListener, ChangeListener
{
//    private Note selectedNote=null;
    private int width;
    private int height;

    private ColorPane paperColorPane;
    private SliderPane rotationPane;
    private SliderPane widthPane;
    private SliderPane heightPane;

    private TextAttributePanel titlePane;

    private TextAttributePanel descriptionPane;


    public EditInfoPanel(int width, int height)
    {
        this.width=width;
        this.height=height;
        setSize(width,height);

        int buttonLen=height/12;
        setComponents(buttonLen);
    }
    public void addActionListener(ActionListener al)
    {
        paperColorPane.addActionListener(al);
        titlePane.addActionListener(al);
        descriptionPane.addActionListener(al);
    }

    public void addChangeListener(ChangeListener cl)
    {
        rotationPane.addChangeListener(cl);
        widthPane.addChangeListener(cl);
        heightPane.addChangeListener(cl);
    }
    public void setComponents(int buttonLen)
    {
        setLayout(new FlowLayout());
        paperColorPane=new ColorPane("paper color",buttonLen);
        rotationPane=new SliderPane("rotation",this.width/4,buttonLen,-180,180);
        widthPane=new SliderPane("width",this.width/6,buttonLen,25,this.width/4);
        heightPane=new SliderPane("height",this.width/6,buttonLen,25,this.width/4);
        titlePane=new TextAttributePanel("title",this.width,this.height/4,buttonLen);
        descriptionPane=new TextAttributePanel("description",this.width,this.height*3/4,buttonLen);

        add(paperColorPane);
        add(rotationPane);
        add(widthPane);
        add(heightPane);
        add(titlePane);
//        add(new JSeparator());
        add(descriptionPane);
    }
    public void setValue(Note selectedNote)
    {
        paperColorPane.setColor(selectedNote.getPaperColor());
        rotationPane.setValue(selectedNote.getRotationDegree());
        widthPane.setValue(selectedNote.getWidth()/2);
        heightPane.setValue(selectedNote.getHeight()/2);
        titlePane.setValue(selectedNote.getTitleAttributes());
        descriptionPane.setValue(selectedNote.getDescriptionAttributes());
    }

    public boolean isColorButton(Object o)
    {
        return paperColorPane.getColorButton().equals(o);
    }

    public Color showColorDialog()
    {
        return paperColorPane.showColorDialog();
    }

//    public void reset()
//    {
//        setSelectedNote(null);
//        setVisible(false);
//    }
//
//
    public boolean isWidthPaneSlider(Object o)
    {
        return widthPane.getValueSlider().equals(o);
    }
    public int getNewWidth()
    {
        return widthPane.getValue();
    }
    public void setNewWidth(int val)
    {
        widthPane.setValue(val);
    }
    public boolean isHeightPaneSlider(Object o)
    {
        return heightPane.getValueSlider().equals(o);
    }
    public int getNewHeight()
    {
        return heightPane.getValue();
    }
    public void setNewHeight(int val)
    {
        heightPane.setValue(val);
    }
    public boolean isRotationPaneSlider(Object o)
    {
        return rotationPane.getValueSlider().equals(o);
    }
    public int getNewRotation()
    {
        return rotationPane.getValue();
    }
    public void setNewRotation(int val)
    {
        rotationPane.setValue(val);
    }
    private String contentMsg;
    private String fontMsg;
    private String fontColorMsg;

    public void checkAttributePanels(Note selectedNote,ActionEvent e)
    {
        textAttributePanelOp(selectedNote,selectedNote.getTitleAttributes(),titlePane,e);
        textAttributePanelOp(selectedNote,selectedNote.getDescriptionAttributes(),descriptionPane,e);
    }
    public void textAttributePanelOp(Note selectedNote,TextAttributes textAttributes,
                                     TextAttributePanel textAttributePanel, ActionEvent e)
    {
        boolean isDes=selectedNote.getDescriptionAttributes().equals(textAttributes);
        if(selectedNote==null||
                !(isDes||selectedNote.getTitleAttributes().equals(textAttributes)))
        {
            return;
        }
        else if(isDes)
        {
            contentMsg= NoteUpdateEnum.DESCRIPTION_CONTENT.toString();
            fontMsg=NoteUpdateEnum.DESCRIPTION_FONT.toString();
            fontColorMsg=NoteUpdateEnum.DESCRIPTION_COLOR.toString();
        }
        else
        {
            contentMsg=NoteUpdateEnum.TITLE_CONTENT.toString();
            fontMsg=NoteUpdateEnum.TITLE_FONT.toString();
            fontColorMsg=NoteUpdateEnum.TITLE_COLOR.toString();
        }
        if(textAttributePanel.isFontChooser(e.getSource()))
        {
            String fontStr=textAttributePanel.getItem();
            if(!fontStr.equals(textAttributes.getFontName())) {
                Font oldFont = textAttributes.getFont();
                textAttributes.setFontName(fontStr);
                selectedNote.callNotifier(fontMsg, oldFont, textAttributes.getFont());
            }
        }
        else if(textAttributePanel.isColorButton(e.getSource()))
        {
            Color oldColor=textAttributes.getFontColor();
            Color newColor=textAttributePanel.showColorDialog();
            if(!newColor.equals(oldColor)) {
                textAttributes.setFontColor(newColor);
                selectedNote.callNotifier(fontColorMsg, oldColor, textAttributes.getFontColor());
            }
        }
        else if(textAttributePanel.isBoldButton(e.getSource()))
        {
            textAttributePanel.reverseBold();
            boolean newBold=textAttributePanel.isBold();
            if(newBold!=textAttributes.isFontBold()) {
                Font oldFont = textAttributes.getFont();
                textAttributes.setFontBold(newBold);
                selectedNote.callNotifier(fontMsg, oldFont, textAttributes.getFont());
            }
        }
        else if(textAttributePanel.isConfirmButton(e.getSource())
                ||textAttributePanel.isCancelButton(e.getSource()))
        {
            if(textAttributePanel.isConfirmButton(e.getSource()))
            {
                String oldContent=textAttributes.getTextContent();
                String newContent=textAttributePanel.getText();
                if(!oldContent.equals(newContent)) {
                    textAttributes.setTextContent(textAttributePanel.getText());
                    selectedNote.callNotifier(contentMsg, oldContent, textAttributes.getTextContent());
                }
            }
            textAttributePanel.setText(textAttributes.getTextContent());
            textAttributePanel.showEditButtons(false);
        }
    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        try {
//            if(selectedNote!=null)
//            {
//                if(e.getSource()==paperColorPane.getColorButton())
//                {
//                    Color newColor=paperColorPane.showColorDialog();
//                    selectedNote.setPaperColor(newColor);
//                }
//                else
//                {
//                    /**
//                     * ========= titlePane ActionEvent =========
//                     */
//                    textAttributePanelOp(selectedNote.getTitleAttributes(),
//                            titlePane, e);
//                    /**
//                     * ========= descriptionPane ActionEvent =========
//                     */
//                    textAttributePanelOp(selectedNote.getDescriptionAttributes(),
//                            descriptionPane, e);
//                }
//            }
//        }catch (Exception exception)
//        {
//            System.out.print(exception.getMessage());
//        }
//
//    }
//
//    @Override
//    public void stateChanged(ChangeEvent e) {
//        try {
//            // when open a new edit after finishing last edit with the sliders
//            // the dialog will pass the changed value to null object before the new selected note comes in
//            if(selectedNote!=null)
//            {
//                if(e.getSource()==widthPane.getValueSlider())
//                {
//                    int newHalfWidth=widthPane.getValue();
//                    selectedNote.setHalfWidth(newHalfWidth);
//                    widthPane.setValue(newHalfWidth);
//                }
//                else if(e.getSource()==heightPane.getValueSlider())
//                {
//                    int newHalfHeight=heightPane.getValue();
//                    selectedNote.setHalfHeight(newHalfHeight);
//                    heightPane.setValue(newHalfHeight);
//                }
//                else if(e.getSource()==rotationPane.getValueSlider())
//                {
//                    int newRotation=rotationPane.getValue();
//                    selectedNote.setRotationDegree(newRotation);
//                    rotationPane.setValue(newRotation);
//                }
//            }
//        }catch (Exception exception)
//        {
//            System.out.print(exception.getMessage());
//        }
//    }
}
