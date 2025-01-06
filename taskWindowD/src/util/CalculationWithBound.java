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
