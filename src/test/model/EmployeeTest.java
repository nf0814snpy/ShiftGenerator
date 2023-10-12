package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    private Employee emp1;

    @BeforeEach
    void runBefore(){
        emp1 = new Employee("Nana Fujita",new Position("Service Assistant"),20.0);

    }

    @Test
    void testConstructor() {
        assertEquals("Nana Fujita", emp1.getName());
        assertEquals(2, emp1.getPositionID());
        assertEquals(20.0,emp1.idealWorkTime());
        assertEquals(0.0,emp1.getTotalWorkTime());
        assertEquals(0,emp1.getAvailability().getListAvailability().size());
    }

    @Test
    void testAddSingleWorkTime() {
        emp1.addWorkTime(7.0);
        assertEquals(7.0,emp1.getTotalWorkTime());
        assertFalse(emp1.isWorkEnough(0));
    }

    @Test
    void testAddSingleWorkTimeBoundary() {
        assertFalse(emp1.isWorkEnough(0));
        emp1.addWorkTime(20.0);
        assertEquals(20.0,emp1.getTotalWorkTime());
        assertEquals(20.0,emp1.idealWorkTime());
        assertTrue(emp1.isWorkEnough(0));
    }

    @Test
    void testAddMultipleWorkTime() {
        assertFalse(emp1.isWorkEnough(5));
        emp1.addWorkTime(7.0);
        emp1.addWorkTime(5.0);
        assertEquals(12.0,emp1.getTotalWorkTime());
        assertEquals(20.0,emp1.idealWorkTime());
        assertFalse(emp1.isWorkEnough(2));
    }
}
