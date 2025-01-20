package GUIdelegate.component.mid;

import javax.swing.*;
import java.awt.*;

public class DescriptionDialog extends JDialog {
    private TextEditPane textEditPane;
    public DescriptionDialog(int width,int height)
    {
        setSize(new Dimension(width,height));
        textEditPane=new TextEditPane(width,height,height*4/5);
        add(textEditPane);
    }
}
