package model;

import util.DateRelated;
import util.JsonRelated;

import javax.json.*;
import java.util.ArrayList;
import java.util.List;

public class TaskQuadrant {
    private int count=0;
    private Note selectedNote=null;
    private List<Note> noteList;

    public JsonObject toJsonObject()
    {
        JsonObjectBuilder info= Json.createObjectBuilder();
        info.add("count",count)
                .add("noteList",noteListToJsonArray());
        return info.build();
    }
    private JsonArray noteListToJsonArray()
    {
        JsonObject noteInfo;
        JsonArray notesArray;
        JsonArrayBuilder notesArrayBuilder= Json.createArrayBuilder();
        for(Note note:noteList)
        {
            noteInfo=note.toJsonObject();
            notesArrayBuilder.add(noteInfo);
        }
        notesArray=notesArrayBuilder.build();
        return notesArray;
    }

    public TaskQuadrant()
    {
        noteList=new ArrayList<>();
    }

    public TaskQuadrant(JsonObject jsonObject)
    {
        count=Integer.parseInt(jsonObject.get("count").toString());
        System.out.println(count);
        // noteList parsing
        JsonArray jsonArray=jsonObject.getJsonArray("noteList");
//        for(JsonValue jv:jsonArray)
//        {
//            jv.getValueType();
//        }
        noteList=new ArrayList<>();
    }

    public int getNewId() {
        return count++;
    }

    public boolean add(Note note)
    {
        return noteList.add(note);
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public boolean remove(Note note)
    {
        return noteList.remove(note);
    }

    public Note getSelectedNote() {
        return selectedNote;
    }

    public void setSelectedNote(Note selectedNote) {
        if(this.selectedNote!=null) this.selectedNote.setSelected(false);
        if(selectedNote!=null) selectedNote.setSelected(true);
        this.selectedNote = selectedNote;
    }
}
