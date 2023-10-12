package ui;

import model.AvailableDay;
import model.Employee;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShiftGeneratorTest {

    private ShiftGenerator generator;

    @BeforeEach
    void runBefore(){
        generator = new ShiftGenerator();
    }

    @Test
    void testCheckBeyondTime(){
        AvailableDay ava1;
        ava1 = new AvailableDay("Sunday",12.0,17.0);
        AvailableDay ava2;
        assertTrue(generator.checkBeyondTime(ava1,13.0,15.0));
        assertFalse(generator.checkBeyondTime(ava1,9.0,15.0));
        assertFalse(generator.checkBeyondTime(ava1,13.0,22.0));
        assertFalse(generator.checkBeyondTime(ava1,9.0,22.0));
    }

    @Test
    void testIsAbleToAssignShift() {
        int shiftNum1 = 1;
        int shiftNum2 = 2;
        int shiftNum3 = 3;
        //test for shiftNum1 == 1
        Employee emp1;
        emp1 = new Employee("Nana Fujita",new Position("Service Assistant"),20.0);
        AvailableDay ava1 = new AvailableDay("Sunday",8.0,22.0);
        AvailableDay ava2 = new AvailableDay("Monday",12.0,13.0);
        AvailableDay ava3 = new AvailableDay("Tuesday",15.0,22.0);
        emp1.getAvailability().addDay(ava1);
        emp1.getAvailability().addDay(ava2);
        emp1.getAvailability().addDay(ava3);
    }

}
