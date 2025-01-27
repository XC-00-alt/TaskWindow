package GUIdelegate.delegate;

import model.Note;
import model.TextAttributes;

import java.awt.*;
import java.util.List;

public class GraphicDelegate {
    private static Font defaultFont=null;
    private static final BasicStroke noteBoundWidth=new BasicStroke(1);
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
            for(int i=0;i<=text.length();i++)
            {
                // if it's the last line
                if(marginY+font.getSize()*nRow>=textBoxHeight-marginY)
                {
                    g.drawString("...",x+marginX,y+marginY+font.getSize()*nRow);
                    break;
                }
                if(i==text.length())
                {
                    g.drawString(text.substring(lastOutputEnd),x+marginX,y+marginY+font.getSize()*nRow);
                    break;
                }
                rowLen+=metrics.charWidth(text.charAt(i));
                if(rowLen>textBoxWidth-marginX*2)
                {
                    g.drawString(text.substring(lastOutputEnd,i),x+marginX,y+marginY+font.getSize()*nRow);
                    rowLen=metrics.charWidth(text.charAt(i));
                    lastOutputEnd=i;
                    nRow++;
                }
            }
        }
        else g.drawString(text,x+marginX,y+marginY);
    }
    private static void setDefaultFont(TextAttributes textAttributes,Font font)
    {
        if(textAttributes.getFont()==null)
        {
            textAttributes.setDefaultFont(font);
        }
    }
    public static void drawBox(Note note,Graphics2D g2d,Color boundColor,Color fillColor)
    {
        g2d.setStroke(new BasicStroke(note.getErrorAllowance()));
        g2d.setColor(boundColor);
        g2d.drawRect(note.getLeft()-note.getErrorAllowance(),
                note.getTop()-note.getErrorAllowance(),
                note.getWidth()+note.getErrorAllowance()*2,
                note.getHeight()+note.getErrorAllowance()*2);
//                g2d.drawRect(note.getLeft(),note.getTop(),note.getWidth(),note.getHeight());
        g2d.setColor(fillColor);
        g2d.fillRect(note.getLeft()-note.getErrorAllowance(),
                note.getTop()-note.getErrorAllowance(),
                note.getWidth()+note.getErrorAllowance()*2,
                note.getHeight()+note.getErrorAllowance()*2);
    }
    public static void drawNotes(Graphics g, List<Note> noteList)
    {
        Graphics2D g2d = (Graphics2D) g;
        if(defaultFont==null) defaultFont=g.getFont();
        for(Note note:noteList)
        {
            TextAttributes titleAttributes=note.getTitleAttributes();
            setDefaultFont(titleAttributes,defaultFont);
            setDefaultFont(note.getDescriptionAttributes(),defaultFont);

            // Draws the bound
            g2d.setStroke(noteBoundWidth);
            g2d.rotate(Math.toRadians(note.getRotationDegree()),
                    note.getCentre().x,note.getCentre().y);
            g.setColor(note.getBoundColor());
            g.drawRect(note.getLeft(),note.getTop(),note.getWidth(),note.getHeight());

            // Draw note paper
            g.setColor(note.getPaperColor());
            g.fillRect(note.getLeft(),note.getTop(),note.getWidth(),note.getHeight());

            // Draws the title
            g.setColor(titleAttributes.getFontColor());
            g.setFont(titleAttributes.getFont());
            drawWrappedText(g,titleAttributes.getTextContent(),g.getFont(),note.getWidth(), note.getHeight(),
                    note.getLeft(),note.getTop(),note.getWidth()/8,note.getHeight()/3);

//            // Draws the nail
//            g.setColor(note.getBoundColor());
//            g.fillOval(note.getCentre().x-note.getErrorAllowance(),note.getTop(),
//                    note.getErrorAllowance(),note.getErrorAllowance());

            // Draws selection box
            if(note.isSelected())
            {
                drawBox(note,g2d,Note.getSelectedColor(),Note.getSelectedFill());
            }
            else if(note.isComplete())
            {
                drawBox(note,g2d,Note.COMPLETE_COLOR,Note.COMPLETE_FILL);
            }
            // rotate back
            g2d.rotate(-Math.toRadians(note.getRotationDegree()),
                    note.getCentre().x,note.getCentre().y);
        }
    }
}
