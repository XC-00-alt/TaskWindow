package GUIdelegate.component.small;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * A JScrollPane that uses JTextArea as its view
 */
public class TextScrollArea extends JScrollPane {
    private JTextArea textArea =new JTextArea();
    public TextScrollArea(int width,int height)
    {
        setPreferredSize(new Dimension(width, height));
//      ref: https://blog.csdn.net/Harry_Jack/article/details/117257946
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        setViewportView(textArea);
    }

    public void addDocumentListener(DocumentListener dl)
    {
        textArea.getDocument().addDocumentListener(dl);
    }
    public void setText(String t)
    {
        textArea.setText(t);
    }

    public String getText()
    {
        return textArea.getText();
    }
}
