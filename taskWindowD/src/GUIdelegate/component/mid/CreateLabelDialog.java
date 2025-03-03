package GUIdelegate.component.mid;

import GUIdelegate.component.small.CategoryLabelPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateLabelDialog extends JDialog implements ActionListener {
    private CategoryLabelPane createPane;
    private JButton confirmButton=new JButton("confirm");
    private JButton cancelButton=new JButton("cancel");
    public CreateLabelDialog(int width,int height)
    {
        setLayout(new FlowLayout());
        setSize(new Dimension(width, height));
        setResizable(false);
        setLocation(width/2,height*2/5);

        createPane=new CategoryLabelPane(width*3/4,width/10,false,this);
        add(createPane);

        Dimension buttonDimension=new Dimension(width/3,width/10);
        confirmButton.setSize(buttonDimension);
        cancelButton.setSize(buttonDimension);
        add(confirmButton);
        add(cancelButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
