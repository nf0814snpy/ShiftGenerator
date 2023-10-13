package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    Schedule testSchedule;
    private Employee emp1;
    private Employee emp2;
    private Employee emp3;
    private Employee emp4;
    private Employee emp5;

    @BeforeEach
    void runBefore(){
        testSchedule = new Schedule();
        emp1 = new Employee("Nana Fujita",new Position("Manager"),40.0,1);
        emp2 = new Employee("Sarah",new Position("Supervisor"),35.0,2);
        emp3 = new Employee("Mark",new Position("Service Assistant"),15.0,3);
        emp4 = new Employee("Takuya",new Position("TestTest"),20.0,4);
        emp5 = new Employee("Tom",new Position("Supervisor"),30.0,5);
    }

    @Test
    void testEmptySchedule(){
        assertEquals(0,testSchedule.getShifts().size());
    }

    @Test
    void testAddSingleShift(){
        testSchedule.addShift(new Shift (8.0,15.0,emp1, 0));
        assertEquals(1,testSchedule.getShifts().size());
    }

    @Test
    void testCountSingleShifts(){
        testSchedule.addShift(new Shift (8.0,15.0,emp1, 0));
        assertEquals(1,testSchedule.getShifts().size());
        assertEquals(1,testSchedule.countShifts(0,1,0));
        assertEquals(0,testSchedule.countShifts(0,1,1));
        assertEquals(0,testSchedule.countShifts(0,1,2));
        assertEquals(0,testSchedule.countShifts(0,1,3));
        assertEquals(0,testSchedule.countShifts(1,1,2));
        assertEquals(0,testSchedule.countShifts(0,2,0));
    }

    @Test
    void testCountMultipleShiftsForSinglePerson(){
        testSchedule.addShift(new Shift (8.0,15.0,emp1, 0));
        testSchedule.addShift(new Shift (15.0,22.0,emp1, 1));
        testSchedule.addShift(new Shift (8.0,15.0,emp1, 2));
        assertEquals(3,testSchedule.getShifts().size());
        assertEquals(1,testSchedule.countShifts(0,1,0));
        assertEquals(0,testSchedule.countShifts(0,1,1));
        assertEquals(0,testSchedule.countShifts(0,2,0));
        assertEquals(0,testSchedule.countShifts(1,1,0));
        assertEquals(1,testSchedule.countShifts(2,1,0));
        assertEquals(1,testSchedule.countShifts(1,3,0));
        assertEquals(0,testSchedule.countShifts(1,4,1));
    }

    @Test
    void testCountMultipleShiftsWithAnotherPerson(){
        testSchedule.addShift(new Shift (8.0,15.0,emp1, 0));
        testSchedule.addShift(new Shift (8.0,15.0,emp2, 0));
        testSchedule.addShift(new Shift (8.0,15.0,emp3, 0));
        testSchedule.addShift(new Shift (8.0,15.0,emp4, 0));
        testSchedule.addShift(new Shift (8.0,15.0,emp5, 0));
        assertEquals(5,testSchedule.getShifts().size());
        assertEquals(1,testSchedule.countShifts(0,1,0));
        assertEquals(2,testSchedule.countShifts(0,1,1));
        assertEquals(1,testSchedule.countShifts(0,1,2));
        assertEquals(1,testSchedule.countShifts(0,1,3));
        assertEquals(0,testSchedule.countShifts(1,4,1));

    }


}
