package model;

import util.JsonRelated;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.awt.*;

public class CategoryLabel {
    private Color color;
    private String name;

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
    }
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
}
