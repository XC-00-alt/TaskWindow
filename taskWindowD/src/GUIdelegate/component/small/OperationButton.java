package GUIdelegate.component.small;

import javax.swing.*;
import java.awt.*;

public class OperationButton extends JButton {
    public static String CONFIRM="confirm";
    public static String CANCEL="cancel";
    public static String DELETE="delete";
    private String op;
    public OperationButton(String op)
    {
        this.op=op;
    }
    @Override
    public void paint(Graphics g)
    {

    }
}
