package model;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Note {
//    private MyVector centre;
    private Point centre;
    private Color boundColor;
    private Color paperColor;
//    private int rotationDegree;
    private DefaultMutableTreeNode node;
    
    private String title="默认标题";
    private Color titleColor=Color.black;
    
    private String description;
    
    // half of the width of this note
    private int halfWidth=25;
    // half of the height of this note
    private int halfHeight=25;

    private double marginXRatio=0.125;
    private double marginYRatio=0.333;

    //the flag indicating whether this note is selected or not
    private boolean isSelected=false;
    
//    private Integer quadrant;
    private QuadrantEnum quadrantCode;
    // used in the isInRange method, allows the note to be selected with this acceptable tolerance
    private int errorAllowance=5;
    
    // the color of the bound of the selection box
    private final static Color SELECTED_COLOR =new Color(0xFF00FFF7, true);
    // the color to fill the selection box
    private final static Color SELECTED_FILL =new Color(0x3200FFF7, true);
    public static final String NOTE_QUADRANT_CHANGE ="noteQuadrantChange";
    public static final String NOTE_DELETE ="delete note";

    public static final String NOTE_TO_BE_EDIT ="summon edit note";
    private PropertyChangeSupport notifier;
    
    public Note(int x, int y,QuadrantEnum quadrantEnum)
    {
        centre=new Point(x,y);
        boundColor=Color.black;
        paperColor =new Color(0xFFE562);
        this.quadrantCode = quadrantEnum;
        notifier = new PropertyChangeSupport(this);
    }
    @Override
    public String toString()
    {
        return getTitle();
    }

    public QuadrantEnum getQuadrantCode() {
        return quadrantCode;
    }

    public void addObserver(PropertyChangeListener listener) {
        notifier.addPropertyChangeListener(listener);
    }

    public void setQuadrantCode(QuadrantEnum quadrantCode) {
        QuadrantEnum old=this.quadrantCode;
        this.quadrantCode = quadrantCode;
        setTitle(getQuadrantCode().getCode()+getQuadrantCode().getDescription());
        if(!old.equals(quadrantCode))
        {
            notifier.firePropertyChange(NOTE_QUADRANT_CHANGE,old.getCode(), quadrantCode.getCode());
        }
    }
    public void delete(){
        notifier.firePropertyChange(NOTE_DELETE,this,null);
    }
    public void summonEdit(){
        notifier.firePropertyChange(NOTE_TO_BE_EDIT,null,this);
    }

    public void setNode(DefaultMutableTreeNode node) {
        this.node = node;
    }

    public DefaultMutableTreeNode getNode() {
        return node;
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

    public int getMarginX() {
        return (int)(getWidth()*marginXRatio);
    }

    public void setMarginXRatio(double marginXRatio) {
        this.marginXRatio = validRatio(marginXRatio);
    }

    public double validRatio(double ratio)
    {
        if(ratio>0&&ratio<1) return ratio;
        else return 0.2;
    }
    public int getMarginY() {
        return (int)(getHeight()*marginYRatio);
    }

    public void setMarginYRatio(double marginYRatio) {
        this.marginYRatio = validRatio(marginYRatio);
    }

    public Color getBoundColor() {
        return boundColor;
    }

    public Color getPaperColor() {
        return paperColor;
    }

    public void setPaperColor(Color paperColor) {
        notifier.firePropertyChange(NoteUpdateEnum.PAPER_COLOR.message,this.paperColor,paperColor);
        this.paperColor = paperColor;
    }
//
//    public int getRotationDegree() {
//        return rotationDegree;
//    }
//
//    public void setRotationDegree(int rotationDegree) {
//        this.rotationDegree = rotationDegree;
//    }

    public Color getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(Color titleColor) {
        this.titleColor = titleColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Checks if the point is in the range of this note
     * @param point usually the point where the mouse is on the screen
     * @return true if it's in this note
     */
    public boolean isInRange(Point point)
    {
        // needs changes if rotation is added
        return point.getX()>= this.getLeft()-errorAllowance&&point.getX()<=centre.x+halfWidth+errorAllowance
        &&point.getY()>= this.getTop()-errorAllowance&&point.getY()<=centre.y+halfHeight+errorAllowance;
    }

    /**
     * @return the left bound of the note
     */
    public int getLeft()
    {
        return centre.x-halfWidth;
    }

    /**
     * @return the top bound of the note
     */
    public int getTop()
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
        int oldVal=this.halfWidth;
        this.halfWidth = halfWidth;
        notifier.firePropertyChange(NoteUpdateEnum.NOTE_WIDTH.message,oldVal,halfWidth);
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public Point getCentre() {
        return centre;
    }

}
