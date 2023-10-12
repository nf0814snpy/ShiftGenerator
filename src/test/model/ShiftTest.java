package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShiftTest {

    private Shift shift1;
    private Employee emp1;
    @BeforeEach
    void runBefore(){
        emp1 = new Employee("Nana Fujita",new Position("Service Assistant"),20.0);
        shift1 = new Shift(8.0,12.0,emp1,0);
    }

    @Test
    void testConstructor(){
        assertEquals(8.0,shift1.getStartTime());
        assertEquals(0,shift1.getDateNum());
        assertEquals(emp1,shift1.getEmployee());
    }
}
