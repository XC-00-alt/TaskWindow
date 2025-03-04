package model;

public enum LabelUpdateEnum {
    CHOOSER_REQUEST_CREATE("chooser create"),
    DELETE("delete label"),
    ;
    final String message;
    LabelUpdateEnum(String str)
    {
        this.message=str;
    }
    @Override
    public String toString()
    {
        return message;
    }
}
