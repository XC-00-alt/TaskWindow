package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DateRelated;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    Date date;
    Date nullDate;
    @BeforeEach
    void setDate()
    {
        date=new Date();
        nullDate=null;
    }

    @Test
    void dateTest()
    {
        Date copyDate=new Date(date.getTime());
        assertEquals(date,copyDate);
    }
//    @Test
//    void nullDateTest()
//    {
//        Date copyDate=new Date(nullDate.getTime());
//        assertEquals(nullDate,copyDate);
//    }
    @Test
    void getGreatTime()
    {
        Date greatDate=new Date(Long.MAX_VALUE);
        System.out.println(DateRelated.getDateString(greatDate));
    }
}