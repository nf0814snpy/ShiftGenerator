package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShiftTest {

    private Shift shift1;
    private Employee emp1;
    @BeforeEach
    void runBefore(){
        emp1 = new Employee("Nana Fujita",new Position("Service Assistant"),20.0,1);
        shift1 = new Shift(8.0,12.0,emp1,0);
    }

    @Test
    void testConstructor(){
        assertEquals(8.0,shift1.getStartTime());
        assertEquals(12.0,shift1.getEndTime());
        assertEquals(0,shift1.getDateNum());
        assertEquals(emp1,shift1.getEmployee());
    }

    @Test
    void testCreateShiftForShiftNum(){
        Employee emp1;
        emp1 = new Employee("Nana Fujita",new Position("Service Assistant"),20.0,1);
         Shift temp;
         temp = createShiftForShiftNum(1,emp1,0);

    }

    private Shift createShiftForShiftNum(int shiftNum, Employee emp, int date) {
        double startHour = shiftNum == 1 ? 8.0 : 15.0;
        double endHour = shiftNum == 1 ? 15.0 : 22.0;
        Shift temp = new Shift(startHour, endHour, emp, date);
        emp.addWorkTime(7.0);
        return temp;
    }
}
