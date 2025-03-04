package model;

import util.JsonRelated;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class CategoryLabel {
    private Color color;
    private String name;
    private int noteCount=0;

    private PropertyChangeSupport notifier;

    public JsonObject toJsonObject()
    {
        JsonObjectBuilder info= Json.createObjectBuilder();
        JsonRelated.addColor(info,"labelColor",color);
        info.add("name",name);
        return info.build();
    }
    public CategoryLabel(JsonObject jsonObject)
    {
        color=JsonRelated.getColor(jsonObject,"labelColor");
//        name=jsonObject.getString("name");
        name=jsonObject.get("name").toString();
        notifier = new PropertyChangeSupport(this);
    }
    public CategoryLabel(Color color,String name)
    {
        this.color=color;
        this.name=name;
        notifier = new PropertyChangeSupport(this);
    }
    public void addUsage()
    {
        noteCount++;
    }
    public void removeUsage()
    {
        noteCount--;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public void addObserver(PropertyChangeListener listener) {
        notifier.addPropertyChangeListener(listener);
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
    public void callLabelDelete()
    {
        notifier.firePropertyChange(LabelUpdateEnum.DELETE.message, this,null);
    }

    @Override
    public String toString()
    {
        return name;
    }
    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        if(null == other) {
            return false;
        }
        if (!(other instanceof CategoryLabel otherLabel)) return false;
        else
        {
            return otherLabel.color.equals(this.color)&&otherLabel.name.equals(name);
        }
    }
//    @Override
//    public int hashCode() {
//        return Objects.hash(color,name);
//    }
}
