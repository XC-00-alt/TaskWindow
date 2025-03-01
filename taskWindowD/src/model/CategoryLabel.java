package model;

import java.awt.*;

public class CategoryLabel {
    private Color color;
    private String name;
    public CategoryLabel(Color color,String name)
    {
        this.color=color;
        this.name=name;
    }
    @Override
    public String toString()
    {
        return name;
    }
}
