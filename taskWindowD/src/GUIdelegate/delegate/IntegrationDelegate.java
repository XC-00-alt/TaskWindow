package GUIdelegate.delegate;

import GUIdelegate.component.big.*;
import GUIdelegate.component.small.NoteMenu;
import model.Note;

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
        listPanel=new ListPanel(new Dimension(listPanelWidth,FRAME_HEIGHT));
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
        if(e.getSource()==topMenuBar.getNoteAdder())
        {
            // randomly generates the position of the new note
            int x=randomN.nextInt(0, windowPanel.getWidth());
            int y=randomN.nextInt(0, windowPanel.getHeight());
            Note newNote=new Note(x, y,windowPanel.getQuadrantCode(x,y));
            newNote.setTitle(newNote.getQuadrantCode().toString()+newNote.getQuadrantCode().getDescription());
            newNote.addObserver(this);
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
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    listPanel.changeNote((Note)eventSrc,(Integer) event.getOldValue(),(Integer) event.getNewValue());
                    listPanel.repaint();
                }
            });
        }
        else if(propName.equals(Note.NOTE_DELETE))
        {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Note noteDel=(Note)eventSrc;
                    listPanel.deleteNote(noteDel);
                    windowPanel.removeNote(noteDel);
                    listPanel.repaint();
                    windowPanel.repaint();
                }
            });
        }
        else if(propName.equals(WindowPanel.OPEN_POPUPMENU))
        {
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
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    noteMenu.reset();
                }
            });
        }
    }
}
