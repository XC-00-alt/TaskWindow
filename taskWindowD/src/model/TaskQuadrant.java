package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TaskQuadrant {
    private List<Note> noteList=new ArrayList<>();
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
}
