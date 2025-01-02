package model;

import java.awt.*;

public class Note {
//    private MyVector centre;
    private Point centre;
    private Color color;
    
    private String title;
    
    private String description;
    
    // half of the width of this note
    private int halfWidth=25;
    // half of the height of this note
    private int halfHeight=25;

    //the flag indicating whether this note is selected or not
    private boolean isSelected=false;
    
    private int quadrant;

    // used in the isInRange method, allows the note to be selected with this acceptable tolerance
    private int errorAllowance=5;
    
    // the color of the bound of the selection box
    private final static Color SELECTED_COLOR =new Color(0xFF00FFF7, true);
    // the color to fill the selection box
    private final static Color SELECTED_FILL =new Color(0x3200FFF7, true);
    
    public Note(int x, int y)
    {
        centre=new Point(x,y);
        color=new Color(0xFFE562);
    }

    public static Color getSelectedColor() {
        return SELECTED_COLOR;
    }

    public static Color getSelectedFill() {
        return SELECTED_FILL;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getErrorAllowance() {
        return errorAllowance;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks if the point is in the range of this note
     * @param point usually the point where the mouse is on the screen
     * @return true if it's in this note
     */
    public boolean isInRange(Point point)
    {
        return point.getX()>= this.getX()-errorAllowance&&point.getX()<=centre.getX()+halfHeight+errorAllowance
        &&point.getY()>= this.getY()-errorAllowance&&point.getY()<=centre.getY()+halfHeight+errorAllowance;
    }

    /**
     * @return the left bound of the note
     */
    public int getX()
    {
        return centre.x-halfWidth;
    }

    /**
     * @return the top bound of the note
     */
    public int getY()
    {
        return centre.y-halfHeight;
    }

    public int getHeight() {
        return halfHeight*2;
    }

    public void setHalfHeight(int halfHeight) {
        this.halfHeight = halfHeight;
    }

    public int getWidth() {
        return halfWidth*2;
    }

    public void setHalfWidth(int halfWidth) {
        this.halfWidth = halfWidth;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public Point getCentre() {
        return centre;
    }
}
