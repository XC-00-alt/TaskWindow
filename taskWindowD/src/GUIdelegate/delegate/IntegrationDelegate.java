package GUIdelegate.delegate;

import GUIdelegate.component.big.*;
import GUIdelegate.component.small.NoteMenu;
import model.Note;
import model.NoteUpdateEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

public class IntegrationDelegate implements ActionListener, PropertyChangeListener {
    private int FRAME_WIDTH;
    private int FRAME_HEIGHT;
    // the Random instance that generates random numbers
    Random randomN =new Random();
    private JFrame mainframe=new JFrame("Task Window");
    private TopMenuBar topMenuBar;

    private TopPanel topPanel=new TopPanel();
    private LeftPanel leftPanel;
    private WindowPanel windowPanel;
    private BottomPanel bottomPanel;
    private ListPanel listPanel;

    private NoteMenu noteMenu=new NoteMenu();

    private NoteDialog noteDialog;

    public IntegrationDelegate()
    {
        topMenuBar=new TopMenuBar(this);
        //Gets the resolution of the scree
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        FRAME_WIDTH=(int) Math.round(screenSize.width*0.7);
        FRAME_HEIGHT=(int) Math.round(screenSize.height*0.8);
        setPanelSize();
        setupFrame();
    }

    public void setPanelSize()
    {
        int leftPanelWidth=FRAME_WIDTH/25;
//        int leftPanelWidth=100;
        int listPanelWidth=6*leftPanelWidth;
        int topPanelHeight=FRAME_HEIGHT/25;
//        int topPanelHeight=80;
        int windowPanelWidth=FRAME_WIDTH-leftPanelWidth-listPanelWidth;
        int windowPanelHeight=FRAME_HEIGHT-topPanelHeight*2;

        topPanel.setPreferredSize(new Dimension(FRAME_WIDTH,topPanelHeight));
        leftPanel=new LeftPanel(leftPanelWidth,FRAME_HEIGHT);
        windowPanel=new WindowPanel(this,windowPanelWidth,windowPanelHeight);
        bottomPanel=new BottomPanel(FRAME_WIDTH,topPanelHeight);
        listPanel=new ListPanel(this,new Dimension(listPanelWidth,FRAME_HEIGHT));

        noteDialog=new NoteDialog(this,windowPanelWidth/2,windowPanelHeight/2);
    }

    public void setupFrame()
    {
        mainframe.setJMenuBar(topMenuBar);
        mainframe.add(leftPanel, BorderLayout.WEST);
        mainframe.add(topPanel,BorderLayout.NORTH);
        mainframe.add(windowPanel,BorderLayout.CENTER);
        mainframe.add(bottomPanel,BorderLayout.SOUTH);
        mainframe.add(listPanel,BorderLayout.EAST);
        mainframe.pack();

        mainframe.setSize(FRAME_WIDTH,FRAME_HEIGHT);

//        ref:https://stackoverflow.com/questions/6777135/java-jframe-size-according-to-screen-resolution
        mainframe.setVisible(true);

        mainframe.setResizable(false);

        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // adding a new note
        if(e.getSource()==topMenuBar.getNoteAdder())
        {
            // close noteMenu and set its reference to selected note to be null
            noteMenu.reset();
            // clear selected note
            windowPanel.setSelectedNote(null);

            listPanel.clearSelectedRow();
            // close noteDialog
            noteDialog.reset();
            windowPanel.setDialogInAction(false);

            // randomly generates the position of the new note
            int x=randomN.nextInt(0, windowPanel.getWidth());
            int y=randomN.nextInt(0, windowPanel.getHeight());
            Note newNote=new Note(x, y,windowPanel.getQuadrantCode(x,y));
            newNote.addObserver(this);

            // add the note
            windowPanel.addNote(newNote);
            listPanel.addNote(newNote);
            // repaint to refresh the view
            windowPanel.repaint();
            listPanel.repaint();
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        Object eventSrc=event.getSource();
        String propName=event.getPropertyName();
        System.out.println(propName);
        if(propName.equals(Note.NOTE_QUADRANT_CHANGE))
        {
            // when the note fires the change in quadrant
            // which happens when the note is dragged and dropped in windowPanel
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // change the position of the note in the jTree of the listPanel
                    listPanel.changeNote((Note)eventSrc,(Integer) event.getOldValue(),(Integer) event.getNewValue());
                    // when the note is dragged (selected), the list should be updated
                    listPanel.showSelectedNote(windowPanel.getSelectedNote());
                    listPanel.repaint();

                    // windowPanel operations are not needed here
                    // since they have already happened before here
                }
            });
        }
        else if(propName.equals(WindowPanel.SELECT_NOTE))
        {
            // if the note is selected from the windowPanel
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Note newSelectedNote=(Note)event.getNewValue();

                    // update the selection in jTree
                    listPanel.showSelectedNote(newSelectedNote);

                    // again, windowPanel operations are not needed here
                }
            });
        }
        else if (propName.equals(ListPanel.SELECT_NOTE))
        {
            // if the note is selected from the listPanel
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    MouseEvent e = (MouseEvent) event.getNewValue();

                    // get the selected note from the jTree
                    Note selectedNote=listPanel.getSelectedNote(e);
                    // set the selectedNote in the windowPanel
                    windowPanel.setSelectedNote(selectedNote);
                    if(selectedNote!=null)
                    {
                        // prevents new object being set with attributes of the old object
                        noteDialog.reset();

                        // if it should trigger the noteMenu
                        if(e.isPopupTrigger()) {
                            // show the noteMenu at the place where it should be
                            noteMenu.show(e.getComponent(), e.getX(), e.getY());

                            // set the menu target on the selected note
                            noteMenu.setSelectedNote(selectedNote);

                            // tell the windowPanel that noteMenu is requested by the listPanel
                            windowPanel.setListRequested(true);
                        }

                        // update the selection in jTree
                        listPanel.showSelectedNote(selectedNote);
                    }
                    windowPanel.repaint();
                }
            });
        }
        else if(propName.equals(Note.NOTE_DELETE))
        {
            // when the note is set to be deleted
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // close the noteMenu and set its target to be null
                    noteMenu.reset();

                    Note noteDel=(Note)eventSrc;
                    // remove the note from the jTree and arrayList
                    listPanel.deleteNote(noteDel);
                    windowPanel.removeNote(noteDel);

                    windowPanel.setMenuPopUp(false);
                    windowPanel.setListRequested(false);

                    // refresh the view
                    listPanel.repaint();
                    windowPanel.repaint();
                }
            });
        }
        else if (propName.equals(Note.NOTE_TO_BE_EDIT))
        {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Note note2bEdit=(Note)eventSrc;
                    noteDialog.setSelectedNote(note2bEdit);
                    noteDialog.setVisible(true);
                    noteMenu.reset();

                    // indicates the noteMenu is closed
                    windowPanel.setMenuPopUp(false);
                    windowPanel.setListRequested(false);

                    windowPanel.setSelectedNote(note2bEdit);

                    // flags that there is an opened dialog
                    windowPanel.setDialogInAction(true);
                }
            });
        }
        else if(NoteUpdateEnum.isUpdateProp(propName))
        {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Note noteUpdate=(Note)eventSrc;
                    windowPanel.repaint();
                    if(propName.equals(NoteUpdateEnum.PAPER_COLOR.toString()))
                    {
                        Color newColor=(Color)event.getNewValue();
                    }
                    else if(propName.equals(NoteUpdateEnum.ROTATION.toString()))
                    {
                        int newRotation=(int) event.getNewValue();
                    }
                    else if(propName.equals(NoteUpdateEnum.NOTE_WIDTH.toString()))
                    {
                        int newHalfWidth=(int)event.getNewValue();
                    }
                    else if(propName.equals(NoteUpdateEnum.NOTE_HEIGHT.toString()))
                    {
                        int newHalfHeight=(int) event.getNewValue();
                    }
                    else if (propName.equals(NoteUpdateEnum.TITLE_FONT.toString()))
                    {
                        Font newFont=(Font) event.getNewValue();
                    }
                    else if (propName.equals(NoteUpdateEnum.TITLE_COLOR.toString()))
                    {
                        Color newColor=(Color)event.getNewValue();
                    }
                    else if(propName.equals(NoteUpdateEnum.TITLE_CONTENT.toString()))
                    {
                        String newTitle=(String) event.getNewValue();
                        listPanel.repaint();
                    }
                    else
                    {
                        windowPanel.repaintDescriptionDialog();
                    }
                }
            });
        }
        else if(propName.equals(WindowPanel.OPEN_POPUPMENU))
        {
            // when the noteMenu is opened from windowPanel
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // set the menu target on the selected note
                    MouseEvent e = (MouseEvent) event.getNewValue();
                    noteMenu.show(e.getComponent(), e.getX(), e.getY());
                    // set the menu target on the selected note
                    noteMenu.setSelectedNote(windowPanel.getSelectedNote());
                }
            });
        }
        else if (propName.equals(WindowPanel.CLOSE_POPUPMENU)) {
            // when the noteMenu is closed from windowPanel
            // don't invoke later but dealt right away is better
//            SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
                    // close the noteMenu and set its target to be null
                    noteMenu.reset();

                    // set the 2 flags to indicate the windowPanel that
                    // the noteMenu is closed
                    windowPanel.setListRequested(false);
                    windowPanel.setMenuPopUp(false);

                    // clear the selection
                    windowPanel.setSelectedNote(null);

                    // clear the selection in the jTree
                    listPanel.clearSelectedRow();

                    windowPanel.repaint();
                    listPanel.repaint();
//                }
//            });
        }
        else if(propName.equals(NoteDialog.CLOSE_DIALOG))
        {
//            SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
                    noteDialog.reset();

                    windowPanel.setSelectedNote(null);
//                    windowPanel.setListRequested(false);
//                    windowPanel.setMenuPopUp(false);
                    windowPanel.setDialogInAction(false);

                    listPanel.clearSelectedRow();

                    windowPanel.repaint();
                    listPanel.repaint();
//                }
//            });
        }
    }
}
