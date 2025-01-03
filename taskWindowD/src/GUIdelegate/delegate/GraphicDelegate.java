package GUIdelegate.delegate;

import model.Note;

import java.awt.*;
import java.util.List;

public class GraphicDelegate {
    //ref:https://www.cnblogs.com/LiuFqiang/p/16949220.html

    /**
     * Draws text that can be switched to next line
     * @param g Graphics instance
     * @param text the text to be drawn
     * @param font Font instance of the text
     * @param textBoxWidth the width of the text box
     * @param textBoxHeight the height of the text box
     * @param x the left bound of the text box on the screen
     * @param y the top bound of the text box on the screen
     * @param marginX the horizontal margin
     * @param marginY the vertical margin
     */
    public static void drawWrappedText(Graphics g,String text,Font font,int textBoxWidth,int textBoxHeight,
                                       int x,int y,int marginX,int marginY)
    {
        FontMetrics metrics = g.getFontMetrics(font);
        int strLength = metrics.stringWidth(text);
        if(strLength>textBoxWidth-marginX*2)
        {
            int rowLen=0;
            int nRow=0;
            int lastOutputEnd=0;
            for(int i=0;i<text.length();i++)
            {
                rowLen+=metrics.charWidth(text.charAt(i));
                // if it's the last line
                if(marginY+font.getSize()*nRow>=textBoxHeight-marginY)
                {
                    g.drawString("...",x+marginX,y+marginY+font.getSize()*nRow);
                    break;
                }
                if(rowLen>textBoxWidth-marginX*2)
                {
                    g.drawString(text.substring(lastOutputEnd,i),x+marginX,y+marginY+font.getSize()*nRow);
                    rowLen=metrics.charWidth(text.charAt(i));
                    lastOutputEnd=i;
                    nRow++;
                }
                else {
                    if(i==text.length()-1)
                    {
                        g.drawString(text.substring(lastOutputEnd),x+marginX,y+marginY+font.getSize()*nRow);
                        rowLen=0;
                        lastOutputEnd=text.length();
                        nRow++;
                    }
                }
            }
        }
        else g.drawString(text,x+marginX,y+marginY);
    }
    public static void drawNotes(Graphics g, List<Note> noteList)
    {
        for(Note note:noteList)
        {
            // Draw note paper
            g.setColor(note.getPaperColor());
            g.fillRect(note.getX(),note.getY(),note.getWidth(),note.getHeight());

            // Draws the title
            g.setColor(note.getTitleColor());
            drawWrappedText(g,note.getTitle(),g.getFont(),note.getWidth(), note.getHeight(),
                    note.getX(),note.getY(),note.getWidth()/8,note.getHeight()/3);
//            drawWrappedText(g,"测试中文test text text String\n test",g.getFont(),note.getWidth(), note.getHeight(),
//                    note.getX(),note.getY(),note.getWidth()/8,note.getHeight()/3);
            // Draws selection box
            if(note.isSelected())
            {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(note.getErrorAllowance()));
                g2d.setColor(Note.getSelectedColor());
                g2d.drawRect(note.getX()-note.getErrorAllowance(),
                        note.getY()-note.getErrorAllowance(),
                        note.getWidth()+note.getErrorAllowance()*2,
                        note.getHeight()+note.getErrorAllowance()*2);
//                g2d.drawRect(note.getX(),note.getY(),note.getWidth(),note.getHeight());
                g2d.setColor(Note.getSelectedFill());
                g2d.fillRect(note.getX()-note.getErrorAllowance(),
                        note.getY()-note.getErrorAllowance(),
                        note.getWidth()+note.getErrorAllowance()*2,
                        note.getHeight()+note.getErrorAllowance()*2);
            }
        }
    }
}
