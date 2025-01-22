package model;

public enum NoteUpdateEnum {
    PAPER_COLOR("paper color"),
    NOTE_WIDTH("note width"),
    NOTE_HEIGHT("note height"),
    ROTATION("rotation"),

    TITLE_COLOR("title color"),
    TITLE_FONT("title font"),
    TITLE_CONTENT("title content"),

    DESCRIPTION_COLOR("description color"),
    DESCRIPTION_FONT("description font"),
    DESCRIPTION_CONTENT("description content"),
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
                prop.equals(TITLE_COLOR.message)||prop.equals(TITLE_FONT.message)||prop.equals(TITLE_CONTENT.message)||
                prop.equals(DESCRIPTION_COLOR.message)||prop.equals(DESCRIPTION_FONT.message)
                ||prop.equals(DESCRIPTION_CONTENT.message);
    }
}
