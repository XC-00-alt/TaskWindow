package GUIdelegate.delegate;

import GUIdelegate.component.big.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class IntegrationDelegate implements ActionListener {
    private int FRAME_WIDTH;
    private int FRAME_HEIGHT;
    // the Random instance that generates random numbers
    Random randomN =new Random();
    private JFrame mainframe=new JFrame("Task Window");
    private TopMenuBar topMenuBar;

    private TopPanel topPanel=new TopPanel();
    private LeftPanel leftPanel;
    private WindowPanel windowPanel=new WindowPanel();
    private BottomPanel bottomPanel=new BottomPanel();
    private ListPanel listPanel=new ListPanel();

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
        windowPanel.setPreferredSize(new Dimension(windowPanelWidth,windowPanelHeight));
        bottomPanel.setPreferredSize(new Dimension(FRAME_WIDTH,topPanelHeight));
        listPanel.setPreferredSize(new Dimension(listPanelWidth,FRAME_HEIGHT));
//        topPanel.setSize(new Dimension(FRAME_WIDTH,topPanelHeight));
//        leftPanel.setSize(new Dimension(leftPanelWidth,FRAME_HEIGHT));
//        windowPanel.setSize(new Dimension(windowPanelWidth,windowPanelHeight));
//        bottomPanel.setSize(new Dimension(FRAME_WIDTH,topPanelHeight));
//        listPanel.setSize(new Dimension(listPanelWidth,FRAME_HEIGHT));
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
            System.out.println("test menu adder");
        }
    }
}
