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

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
