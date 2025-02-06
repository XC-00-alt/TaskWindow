package util;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.awt.*;

public class JsonRelated {
    public static void addColor(JsonObjectBuilder objectBuilder, String name, Color color)
    {
        objectBuilder.add(name+"R",color.getRed())
                .add(name+"G",color.getGreen())
                .add(name+"B",color.getBlue())
//                .add(name+"RGB",color.getRGB())
                .add(name+"A",color.getAlpha());
    }
    public static Color getColor(JsonObject jsonObject,String name)
    {
        int r=jsonObject.getInt(name+"R");
        int g=jsonObject.getInt(name+"G");
        int b=jsonObject.getInt(name+"B");
        int a=jsonObject.getInt(name+"A");
        return new Color(r,g,b,a);
    }
}
