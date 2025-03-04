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

    private static Color defaultLabelColor=new Color(0x8B7EE7);
    private static String defaultLabelName="personal";
    private List<CategoryLabel> labelList=new ArrayList<>();
    public static CategoryLabel defaultLabel =new CategoryLabel(defaultLabelColor,defaultLabelName);
    public ArchiveDirectory(TaskQuadrant taskQuadrant,PropertyChangeListener listener)
    {
        this.taskQuadrant=taskQuadrant;
        defaultLabel.addObserver(listener);
        labelList.add(defaultLabel);
    }
    public ArchiveDirectory(JsonObject jsonObject, PropertyChangeListener listener)
    {
        JsonArray jsonArray=jsonObject.getJsonArray("labelList");
        for(int i=0;i< jsonArray.size();i++)
        {
            JsonObject labelJson=jsonArray.getJsonObject(i);
            CategoryLabel label=new CategoryLabel(labelJson);
            label.addObserver(listener);
            labelList.add(label);
        }
        taskQuadrant=new TaskQuadrant(jsonObject.getJsonObject(taskQuadrantKey),listener,labelList);
    }

    public CategoryLabel getDefaultLabel() {
        return labelList.get(0);
    }

    public List<CategoryLabel> getLabelList() {
        return labelList;
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
        if(!labelList.contains(label))  return labelList.add(label);
        else return false;
    }

    public boolean removeLabel(CategoryLabel label)
    {
        return labelList.remove(label);
    }

    public TaskQuadrant getTaskQuadrant() {
        return taskQuadrant;
    }
}
