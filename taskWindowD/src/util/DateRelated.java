package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRelated {
    //ref: https://blog.csdn.net/qq_36789243/article/details/119362598
    private static final SimpleDateFormat simpleDateFormat=
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
    public static String getDateString(Date date)
    {
        if(date==null) return null;
        else return simpleDateFormat.format(date);
    }
}
