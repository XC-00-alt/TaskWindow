package GUIdelegate.component.small;

import java.awt.*;

public class CustomizedLabel {
    private String text;
    private double rotationRadians;

    private float positionXRatio=0;
    private float positionYRatio=0;
    private double centrePositionXRatio=0.5;
    private double centrePositionYRatio=0.5;

    public CustomizedLabel(String text,float degree)
    {
        this.text=text;
        rotationRadians =Math.toRadians(degree);
    }

    public String getText() {
        return text;
    }

    public void setRatios(float xr,float yr,double rxr, double ryr)
    {
        positionXRatio=xr;
        positionYRatio=yr;
        centrePositionXRatio=rxr;
        centrePositionYRatio=ryr;
    }

    public double getRotationRadians() {
        return rotationRadians;
    }
    public void drawString(Graphics2D g2d,int panelWidth,int panelHeight)
    {
        float x=panelWidth*positionXRatio;
        float y=panelHeight*positionYRatio;
        double rotationX=panelWidth*centrePositionXRatio;
        double rotationY=panelHeight*centrePositionYRatio;
        g2d.rotate(rotationRadians,rotationX,rotationY);
        g2d.drawString(text,x,y);
        g2d.rotate(-rotationRadians,rotationX,rotationY);
    }
}
