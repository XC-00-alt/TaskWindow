package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuadrantEnumTest {
    @Test
    void testEquals()
    {
        assertEquals(QuadrantEnum.IMPORTANT_NON_URGENT,QuadrantEnum.IMPORTANT_NON_URGENT);
        assertFalse(QuadrantEnum.IMPORTANT_NON_URGENT.equals(QuadrantEnum.IMPORTANT_URGENT));
    }
    @Test
    void testName()
    {
        System.out.println(QuadrantEnum.IMPORTANT_NON_URGENT.name());
    }
    @Test
    void testSwitch()
    {
        QuadrantEnum quadrantEnum=QuadrantEnum.IMPORTANT_NON_URGENT;
        switch (quadrantEnum)
        {
            case IMPORTANT_NON_URGENT : {
                System.out.println("1");
                break;
            }
            default: System.out.println("not");
        }
    }
    @Test
    void getEnum()
    {
        QuadrantEnum quadrantEnum=QuadrantEnum.valueOf(QuadrantEnum.IMPORTANT_NON_URGENT.name());
        assertEquals(QuadrantEnum.IMPORTANT_NON_URGENT,quadrantEnum);
    }

}