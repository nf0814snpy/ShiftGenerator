package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AvailabilityTest {
    private AvailableDay ava1;
    private AvailableDay ava2;
    private AvailableDay ava3;
    private AvailableDay ava4;
    private AvailableDay ava5;
    private AvailableDay ava6;
    private AvailableDay ava7;

    private Availability avail;

    @BeforeEach
    void runBefore(){
        ava1 = new AvailableDay("Sunday",8.0,22.0);
        ava2 = new AvailableDay("Monday",12.0,13.0);
        ava3 = new AvailableDay("Tuesday",10.0,22.0);
        ava4 = new AvailableDay("Wednesday",8.0,17.0);
        ava5 = new AvailableDay("Thursday",8.5,12.0);
        ava6 = new AvailableDay("Friday",17.0,22.0);
        ava7 = new AvailableDay("Saturday",15.0,22.0);
        avail = new Availability();
    }

    @Test
    void testEmptyList(){
        assertEquals(0,avail.getListAvailability().size());
    }

    @Test
    void testAddSingleAvailability(){
        avail.addDay(ava1);
        assertEquals(1,avail.getListAvailability().size());
        assertEquals(0,avail.getListAvailability().get(0).getNumOfDate());
        assertEquals(8.0,avail.getListAvailability().get(0).getStartTime());
        assertEquals(22.0,avail.getListAvailability().get(0).getEndTime());
    }

    @Test
    void testAddMultipleAvailability(){
        avail.addDay(ava1);
        avail.addDay(ava2);
        avail.addDay(ava3);
        assertEquals(3,avail.getListAvailability().size());
        assertEquals(0,avail.getListAvailability().get(0).getNumOfDate());
        assertEquals(8.0,avail.getListAvailability().get(0).getStartTime());
        assertEquals(22.0,avail.getListAvailability().get(0).getEndTime());
        assertEquals(1,avail.getListAvailability().get(1).getNumOfDate());
        assertEquals(12.0,avail.getListAvailability().get(1).getStartTime());
        assertEquals(13.0,avail.getListAvailability().get(1).getEndTime());
        assertEquals(2,avail.getListAvailability().get(2).getNumOfDate());
        assertEquals(10.0,avail.getListAvailability().get(2).getStartTime());
        assertEquals(22.0,avail.getListAvailability().get(2).getEndTime());
    }


}
