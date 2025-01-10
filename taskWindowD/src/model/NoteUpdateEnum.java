package model;

public enum NoteUpdateEnum {
    PAPER_COLOR("paper color"),
    NOTE_WIDTH("note width"),
    NOTE_HEIGHT("note height"),
    ROTATION("rotation"),
    TITLE_COLOR("title color"),
    TITLE("title"),
    DESCRIPTION("description"),
    ;
    final String message;
    NoteUpdateEnum(String str)
    {
        this.message=str;
    }
    @Override
    public String toString()
    {
        return message;
    }

    public static boolean isUpdateProp(String prop)
    {
        return prop.equals(PAPER_COLOR.message)||prop.equals(ROTATION.message)||
                prop.equals(NOTE_WIDTH.message)||prop.equals(NOTE_HEIGHT.message)||
                prop.equals(TITLE_COLOR.message)||prop.equals(TITLE.message)||
                prop.equals(DESCRIPTION.message);
    }
}
