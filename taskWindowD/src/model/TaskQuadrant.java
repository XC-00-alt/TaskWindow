package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TaskQuadrant {
    private Note selectedNote;
    private List<Note> noteList;
    public TaskQuadrant()
    {
        noteList=new ArrayList<>();
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
