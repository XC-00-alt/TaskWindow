package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskQuadrantTest {
    TaskQuadrant t;
    @BeforeEach
    void setup()
    {
        t=new TaskQuadrant();
    }

    @Test
    void getCount() {
        assertEquals(0,t.getNewId());
        assertEquals(1,t.getNewId());
    }
}