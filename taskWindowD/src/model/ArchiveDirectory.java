package model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is meant to be a representation of all things needed for loading and saving information.
 * However, ArchiveView is yet implemented so currently (2025y/2M/8d) only TaskQuadrant is needed to be saved.
 */
public class ArchiveDirectory {
    private TaskQuadrant taskQuadrant;
//    private List<ArchiveView> archiveList=new ArrayList<>();
    public ArchiveDirectory(TaskQuadrant taskQuadrant)
    {
        this.taskQuadrant=taskQuadrant;
    }
    public JsonObject toJsonObject()
    {
        JsonObjectBuilder info= Json.createObjectBuilder();
        info.add("taskQuadrant",taskQuadrant.toJsonObject());
        return info.build();
    }
}
