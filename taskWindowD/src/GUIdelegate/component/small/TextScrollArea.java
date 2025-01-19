package GUIdelegate.component.small;

import javax.swing.*;
import java.awt.*;

public class TextScrollArea extends JScrollPane {
    private JTextArea textArea =new JTextArea();
    public TextScrollArea(int width,int height)
    {
        setPreferredSize(new Dimension(width, height));
//        textArea.setPreferredSize(new Dimension(width, height));
//      ref: https://blog.csdn.net/Harry_Jack/article/details/117257946
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        setViewportView(textArea);
    }
    public void setText(String t)
    {
        textArea.setText(t);
    }
}
