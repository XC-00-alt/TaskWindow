package model;

public enum LabelUpdateEnum {
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
