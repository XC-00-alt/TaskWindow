package GUIdelegate.component.small;

import javax.swing.*;
import java.awt.*;

public class ColorButton extends JButton {
    private Color color;
//    private static final int small_component_dimension=25;
    public ColorButton(int length)
    {
//        setPreferredSize(new Dimension(small_component_dimension,small_component_dimension));
        setPreferredSize(new Dimension(length,length));
    }

    public void setColor(Color color) {//It will be useful if the diy ColorButton is implemented
        this.color = color;
        setBackground(color);
    }

    public Color getColor() {
        return color;
    }

}
