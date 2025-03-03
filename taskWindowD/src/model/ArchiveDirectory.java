package model;

import javax.json.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is meant to be a representation of all things needed for loading and saving information.
 * However, ArchiveView is yet implemented so currently (2025y/2M/8d) only TaskQuadrant is needed to be saved.
 */
public class ArchiveDirectory {
    private TaskQuadrant taskQuadrant;
    private static final String taskQuadrantKey="taskQuadrant";
//    private List<ArchiveView> archiveList=new ArrayList<>();

    private Color defaultLabelColor=new Color(0x8B7EE7);
    private String defaultLabelName="personal";
    private List<CategoryLabel> labelList=new ArrayList<>();
    public ArchiveDirectory(TaskQuadrant taskQuadrant)
    {
        this.taskQuadrant=taskQuadrant;
        labelList.add(new CategoryLabel(defaultLabelColor,defaultLabelName));
    }
    public ArchiveDirectory(JsonObject jsonObject, PropertyChangeListener listener)
    {
        taskQuadrant=new TaskQuadrant(jsonObject.getJsonObject(taskQuadrantKey),listener);

        JsonArray jsonArray=jsonObject.getJsonArray("labelList");
        for(int i=0;i< jsonArray.size();i++)
        {
            JsonObject labelJson=jsonArray.getJsonObject(i);
            CategoryLabel label=new CategoryLabel(labelJson);
            labelList.add(label);
        }
    }
    public JsonObject toJsonObject()
    {
        JsonObjectBuilder info= Json.createObjectBuilder();
        info.add(taskQuadrantKey,taskQuadrant.toJsonObject())
                .add("labelList",labelListToJsonArray());
        return info.build();
    }
    private JsonArray labelListToJsonArray()
    {
        JsonObject labelInfo;
        JsonArray labelArray;
        JsonArrayBuilder labelArrayBuilder= Json.createArrayBuilder();
        for(CategoryLabel label:labelList)
        {
            labelInfo=label.toJsonObject();
            labelArrayBuilder.add(labelInfo);
        }
        labelArray=labelArrayBuilder.build();
        return labelArray;
    }
    public boolean addLabel(CategoryLabel label)
    {
        return labelList.add(label);
    }

    public boolean removeLabel(CategoryLabel label)
    {
        return labelList.remove(label);
    }

    public TaskQuadrant getTaskQuadrant() {
        return taskQuadrant;
    }
}
