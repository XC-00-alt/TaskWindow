package GUIdelegate.component.mid;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * For changing the value of int attribute on AttributePane
 */
public class SliderPane extends JPanel{
    private JLabel attributeName=new JLabel();
    private JSlider valueSlider;
    private JTextField valueField=new JTextField();
    public SliderPane(String attributeStr,int slider_width, int small_component_dimension, int min, int max)
    {
        valueSlider=new JSlider(min,max,min);
        valueSlider.setPreferredSize(new Dimension(slider_width,small_component_dimension));
        valueField.setPreferredSize(new Dimension(slider_width/2,small_component_dimension));
        valueField.setEditable(false);
        valueField.setText(String.valueOf(valueSlider.getValue()));
        attributeName.setText(attributeStr);
        addComponent();
    }
    public void addKeyListener(KeyListener kl) {
        valueSlider.addKeyListener(kl);
        valueField.addKeyListener(kl);
    }
//    public void setLabel(String attributeStr)
//    {
//        attributeName.setText(attributeStr);
//    }
    public void addComponent()
    {
        add(attributeName);
        add(valueSlider);
        add(valueField);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth(),getHeight());
    }
    public void addChangeListener(ChangeListener cl)
    {valueSlider.addChangeListener(cl);}


    public JSlider getValueSlider() {
        return valueSlider;
    }
    public void updateValueField()
    {
        valueField.setText(String.valueOf(valueSlider.getValue()));
    }
    public void setValue(int value)
    {
        valueSlider.setValue(value);
        updateValueField();
    }

    public int getValue() {
        return valueSlider.getValue();
    }
    public int updateFieldAndGetValue()
    {
        updateValueField();
        return getValue();
    }
}
