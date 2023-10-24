package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AvailableDayTest {
    private AvailableDay ava1;
    private AvailableDay ava2;
    private AvailableDay ava3;
    private AvailableDay ava4;
    private AvailableDay ava5;
    private AvailableDay ava6;
    private AvailableDay ava7;

    @BeforeEach
    void runBefore(){
        ava1 = new AvailableDay("Sunday",8.0,22.0);
        ava2 = new AvailableDay("Monday",12.0,13.0);
        ava3 = new AvailableDay("Tuesday",10.0,22.0);
        ava4 = new AvailableDay("Wednesday",8.0,17.0);
        ava5 = new AvailableDay("Thursday",8.5,12.0);
        ava6 = new AvailableDay("Friday",17.0,22.0);
        ava7 = new AvailableDay("Saturday",15.0,22.0);
    }

    @Test
    void testConstructor(){
        assertEquals(0,ava1.getNumOfDate());
        assertEquals(1,ava2.getNumOfDate());
        assertEquals(2,ava3.getNumOfDate());
        assertEquals(3,ava4.getNumOfDate());
        assertEquals(4,ava5.getNumOfDate());
        assertEquals(5,ava6.getNumOfDate());
        assertEquals(6,ava7.getNumOfDate());
        assertEquals(8.0,ava1.getStartTime());
    }

    @Test
    void testGetStartTime() {
        assertEquals(12.0,ava2.getStartTime());
    }

    @Test
    void testGetEndTime() {
        assertEquals(13.0,ava2.getEndTime());
    }

    @Test
    void testGetDayOfWeek() {
        assertEquals("Monday",ava2.getDayOfWeek());
    }

}
