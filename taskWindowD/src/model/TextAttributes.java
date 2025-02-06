package model;
import util.JsonRelated;

import javax.json.*;
import java.awt.*;
import java.util.Objects;

public class TextAttributes {
    private String textContent;
    private Font font=null;
    private String fontName;
    private boolean fontBold;
    private Color fontColor=Color.BLACK;

//    public String toJsonString()
//    {
//        String jsonStr="";
//
//        return jsonStr;
//    }

    public JsonObject toJsonObject()
    {
        JsonObjectBuilder info=Json.createObjectBuilder();
        info.add("textContent",textContent)
//                .add("font",font)
                .add("fontName",fontName)
                .add("fontBold",fontBold)
                .add("fontSize",font.getSize());
        JsonRelated.addColor(info,"fontColor",fontColor);
        return info.build();
    }
    public TextAttributes(JsonObject jsonObject)
    {
        textContent=jsonObject.get("textContent").toString();
        fontName=jsonObject.get("fontName").toString();
        fontBold=jsonObject.getBoolean("fontBold");
        int fontSize=jsonObject.getInt("fontSize");
        font=new Font(fontName,getBoldCode(),fontSize);
        fontColor=JsonRelated.getColor(jsonObject,"fontColor");
    }
    public TextAttributes(String textContent)
    {
        this.textContent=textContent;
    }
    public void setDefaultFont(Font font)
    {
        setFont(font);
        setFontName(font.getName());
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setFontName(String fontName) {
//        if(!fontName.equals(this.fontName))
//        {
            this.fontName = fontName;
            try {
                setFont(new Font(fontName,font.getStyle(),font.getSize()));
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
//        }
    }

    public String getFontName() {
        return fontName;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }
    public int getBoldCode()
    {
        if(fontBold) return Font.BOLD;
        else return Font.PLAIN;
    }
    public void setFontBold(boolean fontBold) {
//        if(this.fontBold!=fontBold)
//        {
            this.fontBold = fontBold;
            try {
                setFont(new Font(fontName, getBoldCode(),font.getSize()));
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
//        }
    }

    public boolean isFontBold() {
        return fontBold;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public Color getFontColor() {
        return fontColor;
    }
    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        if(null == other) {
            return false;
        }
        if (!(other instanceof TextAttributes)) return false;
        else{}
        return false;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(textContent);
//    }

}
