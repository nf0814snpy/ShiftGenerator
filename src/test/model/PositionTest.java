package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PositionTest {
    private Position posTest1;
    private Position posTest2;
    private Position posTest3;
    private Position posTest4;

    @BeforeEach
    void runBefore(){
        posTest1 = new Position("Manager");
        posTest2 = new Position("Supervisor");
        posTest3 = new Position("Service Assistant");
        posTest4 = new Position("TestTest");
    }

    @Test
    void testConstructor(){
        assertEquals("Manager",posTest1.getPositionName());
        assertEquals("Supervisor",posTest2.getPositionName());
        assertEquals("Service Assistant",posTest3.getPositionName());
        assertEquals("TestTest",posTest4.getPositionName());
        assertEquals(0,posTest1.getPositionID());
        assertEquals(1,posTest2.getPositionID());
        assertEquals(2,posTest3.getPositionID());
        assertEquals(3,posTest4.getPositionID());
    }
}
