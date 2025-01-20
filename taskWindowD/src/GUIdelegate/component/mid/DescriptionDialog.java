package GUIdelegate.component.mid;

import GUIdelegate.component.small.TextScrollArea;

import javax.swing.*;
import java.awt.*;

public class DescriptionDialog extends JDialog {
    private TextScrollArea scrollArea;
    public DescriptionDialog(int width,int height)
    {
        setSize(new Dimension(width,height));
        scrollArea=new TextScrollArea(width,height);
        scrollArea.setEditable(false);
        add(scrollArea);
    }
    public void setValue(String text,Color fg,Color bg,Font font)
    {
        scrollArea.setText(text);
        scrollArea.setTextAttributes(fg,bg,font);
    }

}
