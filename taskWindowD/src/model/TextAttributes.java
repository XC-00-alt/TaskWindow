package model;

import java.awt.*;
import java.util.Objects;

public class TextAttributes {
    private String textContent;
    private Font font=null;
    private String fontName;
    private boolean fontBold;
    private Color fontColor=Color.BLACK;
    public TextAttributes(String textContent)
    {
        this.textContent=textContent;
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
