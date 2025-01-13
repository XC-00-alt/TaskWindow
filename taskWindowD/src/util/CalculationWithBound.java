package util;

import java.awt.*;

public class CalculationWithBound {
    public static Point minusOp(Point p1,Point p2)
    {
        Point vector = new Point((int) (p1.getX() - p2.getX()),
                (int) (p1.getY() - p2.getY()));
        return vector;
    }
    public static Point addOp(Point p1,Point p2)
    {
        Point vector = new Point((int) (p1.getX() + p2.getX()),
                (int) (p1.getY() + p2.getY()));
        return vector;
    }

    public static Point negateOp(Point p)
    {
        return new Point(-p.x,-p.y);
    }

    // since operations are fairly simple in 2D
    // therefore no need to create Matrix class or more for now
    public static Point translateOp(Point p,Point translateVector)
    {
        return addOp(p,translateVector);
    }

    public static Point rotateOp(Point p,double rotationRadians)
    {
        double cosR=Math.cos(rotationRadians);
        double sinR=Math.sin(rotationRadians);
        int newX=(int)(p.x*cosR-p.y*sinR);
        int newY=(int)(p.x*sinR+p.y*cosR);
        return new Point(newX,newY);
    }

    public static Point rotateAround(Point p, Point pivot, double rotationRadians)
    {
        p=minusOp(p,pivot);
        p=rotateOp(p,rotationRadians);
        p=addOp(p,pivot);
        return p;
    }

    public static Point refineOp(Point p,int winWidth,int winHeight)
    {
        int x=p.x;
        int y=p.y;
        if(x<0) x=0;
        else if(x>winWidth) x=winWidth;
        if(y<0) y=0;
        else if(y>winHeight) y=winHeight;
        return new Point(x,y);
    }
}
