package GUIdelegate.component.small;

import javax.swing.*;
import java.awt.*;

public class BoldButton extends JButton {
    private boolean bold=false;
    public BoldButton(int length)
    {
//        setPreferredSize(new Dimension(length,length));
        setSize(new Dimension(length,length));
        setText("B");
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isBold() {
        return bold;
    }
        @Override
    public void paintComponent(Graphics g) {
        if(bold) setForeground(Color.BLACK);
        else setForeground(Color.GRAY);
        super.paintComponent(g);
    }
}
