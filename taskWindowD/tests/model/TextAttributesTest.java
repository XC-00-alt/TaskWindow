package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextAttributesTest {

    TextAttributes t1;
    TextAttributes t2;
    @BeforeEach
    void setup()
    {
        t1=new TextAttributes("测试");
        t2=new TextAttributes("测试");
    }

    @Test
    void testEquals0() {
        System.out.println("test0: t1: "+t1.hashCode());
        assertEquals(t1, t1);
    }

    @Test
    void testEquals1() {
        System.out.println("test1: t1: "+t1.hashCode()+" t2: "+t2.hashCode());
        assertNotEquals(t1, t2);
    }

    @Test
    void testEquals2() {
        System.out.println("before test2: t1: "+t1.hashCode()+" t2: "+t2.hashCode());
        String s1="1";
        t1.setTextContent(s1);
        t2.setTextContent(s1);
        System.out.println("test2: t1: "+t1.hashCode()+" t2: "+t2.hashCode());
        assertNotEquals(t1, t2);
    }

    @Test
    void testHashCode() {
    }
}