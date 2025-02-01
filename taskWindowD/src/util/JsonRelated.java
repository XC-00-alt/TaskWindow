package util;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.awt.*;

public class JsonRelated {
    public static void addColor(JsonObjectBuilder objectBuilder, String name, Color color)
    {
        objectBuilder.add(name+"RGB",color.getRGB())
                .add(name+"Alpha",color.getAlpha());
    }
}
