package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryLabelTest {
    CategoryLabel label1;
    CategoryLabel label2;
    List<CategoryLabel> labelList;
    @BeforeEach
    void setUp()
    {
        label1=new CategoryLabel(Color.BLUE,"test");
        label2=new CategoryLabel(Color.BLUE,"test");
        labelList=new ArrayList<>();
    }
    @Test
    void equalTest()
    {
        assertEquals(label1,label2);
    }
    @Test
    void countTest()
    {
        label2.addUsage();
        assertEquals(label2.getNoteCount(),label1.getNoteCount());
    }
    @Test
    void containTest(){
        labelList.add(label1);
        assertTrue(labelList.contains(label2));
        label1.addUsage();
        label2=labelList.get(labelList.indexOf(label2));
        assertEquals(1,label2.getNoteCount());
    }
}