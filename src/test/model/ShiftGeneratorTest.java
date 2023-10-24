package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShiftGeneratorTest {

    private ShiftGenerator generator;

    @BeforeEach
    void runBefore(){
        generator = new ShiftGenerator();
    }


    @Test
    void testShiftGenFalse(){
        Schedule sche = new Schedule();
        Employee man = new Employee("Nana Fujita",new Position("Manager"),60.0,1);
        Employee sup1 = new Employee("Jepa",new Position("Supervisor"),60.0,2);
        Employee sup2 = new Employee("Hansel",new Position("Supervisor"),60.0,3);
        Employee sup3 = new Employee("Stanley",new Position("Supervisor"),60.0,4);

        Employee emp1 = new Employee("emp1",new Position("Service Assistant"),60.0,5);
        Employee emp2 = new Employee("emp2",new Position("Service Assistant"),60.0,6);
        Employee emp3 = new Employee("emp3",new Position("Service Assistant"),60.0,7);
        Employee emp4 = new Employee("emp4",new Position("Service Assistant"),60.0,8);
        Employee emp5 = new Employee("emp5",new Position("Service Assistant"),60.0,9);
        Employee emp6 = new Employee("emp6",new Position("Service Assistant"),60.0,10);
        Employee emp7 = new Employee("emp7",new Position("Service Assistant"),60.0,11);
        Employee emp8 = new Employee("emp8",new Position("Service Assistant"),60.0,12);

        EmployeeList empList= new EmployeeList();
        assertFalse(generator.shiftGen(empList,sche));
        empList.addEmployee(man);
        empList.addEmployee(sup1);
        empList.addEmployee(sup2);
        empList.addEmployee(sup3);
        AvailableDay ava1 = new AvailableDay("Sunday",8.0,22.0);
        AvailableDay ava2 = new AvailableDay("Monday",8.0,22.0);
        AvailableDay ava3 = new AvailableDay("Tuesday",8.0,22.0);
        AvailableDay ava4 = new AvailableDay("Wednesday",8.0,22.0);
        AvailableDay ava5 = new AvailableDay("Thursday",8.0,22.0);
        AvailableDay ava6 = new AvailableDay("Friday",8.0,22.0);
        AvailableDay ava7 = new AvailableDay("Saturday",8.0,22.0);
        for(Employee emp: empList.getListEmployee()) {
            emp.getAvailability().addDay(ava1);
            emp.getAvailability().addDay(ava2);
            emp.getAvailability().addDay(ava3);
            emp.getAvailability().addDay(ava4);
            emp.getAvailability().addDay(ava5);
            emp.getAvailability().addDay(ava6);
            emp.getAvailability().addDay(ava7);
        }
        assertFalse(generator.shiftGen(empList,sche));
    }

    @Test
    void testShiftGenTrue(){
        Schedule sche = new Schedule();
        Employee man = new Employee("Nana Fujita",new Position("Manager"),60.0,1);
        Employee sup1 = new Employee("Jepa",new Position("Supervisor"),60.0,2);
        Employee sup2 = new Employee("Hansel",new Position("Supervisor"),60.0,3);
        Employee sup3 = new Employee("Stanley",new Position("Supervisor"),60.0,4);

        Employee emp1 = new Employee("emp1",new Position("Service Assistant"),80.0,5);
        Employee emp2 = new Employee("emp2",new Position("Service Assistant"),80.0,6);
        Employee emp3 = new Employee("emp3",new Position("Service Assistant"),80.0,7);
        Employee emp4 = new Employee("emp4",new Position("Service Assistant"),80.0,8);
        Employee emp5 = new Employee("emp5",new Position("Service Assistant"),80.0,9);
        Employee emp6 = new Employee("emp6",new Position("Service Assistant"),80.0,10);
        Employee emp7 = new Employee("emp7",new Position("Service Assistant"),80.0,11);
        Employee emp8 = new Employee("emp8",new Position("Service Assistant"),80.0,12);
        Employee emp9 = new Employee("emp9",new Position("Service Assistant"),80.0,13);
        Employee emp10 = new Employee("emp10",new Position("Service Assistant"),80.0,14);
        Employee emp11 = new Employee("emp11",new Position("Service Assistant"),80.0,15);
        Employee emp12 = new Employee("emp12",new Position("Service Assistant"),80.0,16);


        EmployeeList empList = new EmployeeList();
        empList.addEmployee(man);
        empList.addEmployee(sup1);
        empList.addEmployee(sup2);
        empList.addEmployee(sup3);
        empList.addEmployee(emp1);
        empList.addEmployee(emp2);
        empList.addEmployee(emp3);
        empList.addEmployee(emp4);
        empList.addEmployee(emp5);
        empList.addEmployee(emp6);
        empList.addEmployee(emp7);
        empList.addEmployee(emp8);
        empList.addEmployee(emp9);
        empList.addEmployee(emp10);
        empList.addEmployee(emp11);
        empList.addEmployee(emp12);

        AvailableDay ava1 = new AvailableDay("Sunday",8.0,22.0);
        AvailableDay ava2 = new AvailableDay("Monday",8.0,22.0);
        AvailableDay ava3 = new AvailableDay("Tuesday",8.0,22.0);
        AvailableDay ava4 = new AvailableDay("Wednesday",8.0,22.0);
        AvailableDay ava5 = new AvailableDay("Thursday",8.0,22.0);
        AvailableDay ava6 = new AvailableDay("Friday",8.0,22.0);
        AvailableDay ava7 = new AvailableDay("Saturday",8.0,22.0);
        for(Employee emp: empList.getListEmployee()) {
            emp.getAvailability().addDay(ava1);
            emp.getAvailability().addDay(ava2);
            emp.getAvailability().addDay(ava3);
            emp.getAvailability().addDay(ava4);
            emp.getAvailability().addDay(ava5);
            emp.getAvailability().addDay(ava6);
            emp.getAvailability().addDay(ava7);
        }
        assertTrue(generator.shiftGen(empList,sche));
    }


    @Test
    void testCheckBeyondTime(){
        AvailableDay ava1;
        ava1 = new AvailableDay("Sunday",12.0,17.0);
        AvailableDay ava2;
        assertTrue(generator.checkNotBeyondTime(ava1,13.0,15.0));
        assertFalse(generator.checkNotBeyondTime(ava1,9.0,15.0));
        assertFalse(generator.checkNotBeyondTime(ava1,13.0,22.0));
        assertFalse(generator.checkNotBeyondTime(ava1,9.0,22.0));
    }

    @Test
    void testIsAbleToAssignShiftForEmpty() {
        int shiftNum1 = 1;
        int shiftNum2 = 2;
        int shiftNum3 = 3;
        int shiftNum4 = 4;
        Employee emp1;
        emp1 = new Employee("Nana Fujita",new Position("Service Assistant"),20.0,1);
        for(int i=0;i<8;i++) {
            assertFalse(generator.isAbleToAssignShift(emp1,i,shiftNum1));
            assertFalse(generator.isAbleToAssignShift(emp1,i,shiftNum2));
            assertFalse(generator.isAbleToAssignShift(emp1,i,shiftNum3));
            assertFalse(generator.isAbleToAssignShift(emp1,i,shiftNum4));
        }
    }

    @Test
    void testIsAbleToAssignShift() {
        int shiftNum = 1;
        //test for shiftNum1 == 1
        Employee emp1;
        emp1 = new Employee("Nana Fujita",new Position("Service Assistant"),20.0,1);
        AvailableDay ava1 = new AvailableDay("Sunday",8.0,22.0);
        Employee emp2 = new Employee("Sarah",new Position("Supervisor"),30.0,2);
        AvailableDay ava2 = new AvailableDay("Sunday",12.0,13.0);
        emp1.getAvailability().addDay(ava1);
        assertFalse(generator.isAbleToAssignShift(emp1,10,shiftNum));
        assertTrue(emp1.getAvailability().getListAvailability().get(0).getStartTime() <= 8.0);
        assertTrue(emp1.getAvailability().getListAvailability().get(0).getEndTime() >= 15.0);
        assertTrue(generator.isAbleToAssignShift(emp1,0,1));
        emp2.getAvailability().addDay(ava2);
        assertFalse(generator.isAbleToAssignShift(emp2,0,shiftNum));
        Employee emp3 = new Employee("Sarah",new Position("Supervisor"),0.0,3);
        AvailableDay ava3 = new AvailableDay("Sunday",8.0,22.0);
        emp3.getAvailability().addDay(ava3);
        assertFalse(generator.isAbleToAssignShift(emp3,0,shiftNum));
    }

    @Test
    void testAssignShift1(){
        EmployeeList empList = new EmployeeList();
        Schedule sche = new Schedule();
        Employee emp1 = new Employee("Nana Fujita",new Position("Manager"),35.0,1);
        Employee emp2= new Employee("Jepa",new Position("Supervisor"),35.0,2);
        Employee emp3 = new Employee("Hansel",new Position("Supervisor"),5.0,3);
        empList.addEmployee(emp1);
        AvailableDay temp = new AvailableDay("Sunday",8.0, 22.0);
        emp1.getAvailability().addDay(temp);
        assertTrue(generator.assignShift1(empList,sche,new IntegerWrapper(2),0));
        EmployeeList empList2 = new EmployeeList();
        empList2.addEmployee(emp2);
        AvailableDay temp2 = new AvailableDay("Sunday",8.0, 22.0);
        emp2.getAvailability().addDay(temp2);
        assertFalse(generator.assignShift1(empList2,sche,new IntegerWrapper(2),0));
        EmployeeList empList3 = new EmployeeList();
        empList3.addEmployee(emp3);
        AvailableDay temp3 = new AvailableDay("Sunday",9.0, 10.0);
        emp2.getAvailability().addDay(temp3);
        assertFalse(generator.assignShift1(empList3,sche,new IntegerWrapper(2),0));
    }

    @Test
    void testAssignShift3(){
        EmployeeList empList = new EmployeeList();
        Schedule sche = new Schedule();
        Employee emp1 = new Employee("Nana Fujita",new Position("Manager"),35.0,1);
        Employee emp2= new Employee("Jepa",new Position("Supervisor"),35.0,2);
        Employee emp3 = new Employee("Hansel",new Position("Supervisor"),5.0,3);
        empList.addEmployee(emp1);
        AvailableDay temp = new AvailableDay("Sunday",8.0, 22.0);
        emp1.getAvailability().addDay(temp);
        assertTrue(generator.assignShift3(empList,sche,new IntegerWrapper(2),0));
        EmployeeList empList2 = new EmployeeList();
        empList2.addEmployee(emp2);
        AvailableDay temp2 = new AvailableDay("Sunday",8.0, 22.0);
        emp2.getAvailability().addDay(temp2);
        assertFalse(generator.assignShift3(empList2,sche,new IntegerWrapper(2),0));
        EmployeeList empList3 = new EmployeeList();
        empList3.addEmployee(emp3);
        AvailableDay temp3 = new AvailableDay("Sunday",9.0, 10.0);
        emp2.getAvailability().addDay(temp3);
        assertFalse(generator.assignShift3(empList3,sche,new IntegerWrapper(2),0));

    }


    @Test
    void testWorkTime(){
        assertEquals(5.0,generator.workTime(4));
        assertEquals(7.0,generator.workTime(3));
    }

    @Test
    void testAssignMemeberToList(){
        EmployeeList managerAndSuper = new EmployeeList();
        EmployeeList sa = new EmployeeList();
        EmployeeList original = new EmployeeList();
        generator.assignMemberToList(original,sa,2);
        assertEquals(0,sa.getListEmployee().size());
        Employee emp1;
        Employee emp2;
        Employee emp3;
        emp1 = new Employee("Nana Fujita",new Position("Manager"),40.0,1);
        emp2 = new Employee("Sarah",new Position("Supervisor"),35.0,2);
        emp3 = new Employee("Mark",new Position("Service Assistant"),15.0,3);
        original.addEmployee(emp1);
        original.addEmployee(emp2);
        original.addEmployee(emp3);
        generator.assignMemberToList(original,sa,2);
        assertEquals(1,sa.getListEmployee().size());
        generator.assignMemberToList(original,managerAndSuper,0);
        assertEquals(1,managerAndSuper.getListEmployee().size());
        generator.assignMemberToList(original,managerAndSuper,1);
        assertEquals(2,managerAndSuper.getListEmployee().size());

    }

    @Test
    void testIsWorkOnThatDay(){
        Employee emp1;
        emp1 = new Employee("Nana Fujita",new Position("Manager"),40.0,1);
        Schedule sche;
        sche = new Schedule();
        boolean result1;
        result1 = generator.isWorkOnThatDay(emp1, 0, sche);
        assertFalse(result1);
        sche.addShift(new Shift(8.0,15.0,emp1,0));
        boolean result2;
        result2 = generator.isWorkOnThatDay(emp1, 0, sche);
        assertTrue(result2);
        assertFalse(generator.isWorkOnThatDay(emp1,1,sche));
    }

    @Test
    void testCreateShiftForShiftNum() {
        Employee emp1;
        emp1 = new Employee("Nana Fujita",new Position("Manager"),40.0,1);
        Shift temp;
        Shift temp2;
        temp = generator.createShiftForShiftNum(1,emp1,0);
        assertEquals(8.0,temp.getStartTime());
        assertEquals(0,temp.getDateNum());
        assertEquals(emp1,temp.getEmployee());
        temp2 = generator.createShiftForShiftNum(3,emp1,1);
        assertEquals(15.0,temp2.getStartTime());
        assertEquals(1,temp2.getDateNum());
        assertEquals(emp1,temp2.getEmployee());
    }

        @Test
        void testFillInShifts() {
            Schedule sche = new Schedule();
            Employee emp1 = new Employee("Nana Fujita",new Position("Manager"),16.0,1);
            AvailableDay ava1 = new AvailableDay("Sunday",8.0,22.0);
            AvailableDay ava3 = new AvailableDay("Monday",8.0,22.0);
            AvailableDay ava4 = new AvailableDay("Tuesday",9.0,10.0);
            AvailableDay ava5 = new AvailableDay("Wednesday",8.0,22.0);
            AvailableDay ava6 = new AvailableDay("Thursday",8.0,22.0);
            emp1.getAvailability().addDay(ava1);
            emp1.getAvailability().addDay(ava3);
            emp1.getAvailability().addDay(ava4);
            emp1.getAvailability().addDay(ava5);
            emp1.getAvailability().addDay(ava6);
            Employee emp2 = new Employee("Jepa",new Position("Manager"),20.0,2);
            sche.getShifts().add(new Shift(8.0,15.0,emp1,3));
            AvailableDay ava2 = new AvailableDay("Sunday",8.0,22.0);
            emp1.getAvailability().addDay(ava2);
            sche.getShifts().add(new Shift(8.0,15.0,emp2,1));
            sche.getShifts().add(new Shift(8.0,15.0,emp2,1));
            sche.getShifts().add(new Shift(8.0,15.0,emp2,1));
            generator.fillInShifts(emp1,sche);
            assertEquals(sche.getShifts().get(4).getEmployee().getName(),"Nana Fujita");
            assertEquals(sche.getShifts().get(5).getEmployee().getName(),"Nana Fujita");
        }




    @Test
    void testAssignShiftOnThatDay(){
        Employee emp1 = emp1 = new Employee("Nana Fujita",new Position("Manager"),40.0,1);
        Schedule sche = new Schedule();
        generator.assignShiftOnThatDay(1,emp1,sche,0);
        assertEquals(8.0,sche.getShifts().get(0).getStartTime());
        assertEquals(emp1,sche.getShifts().get(0).getEmployee());
        generator.assignShiftOnThatDay(2,emp1,sche,0);
        assertEquals(10.0,sche.getShifts().get(1).getStartTime());;
        assertEquals(emp1,sche.getShifts().get(1).getEmployee());
        generator.assignShiftOnThatDay(3,emp1,sche,0);
        assertEquals(15.0,sche.getShifts().get(2).getStartTime());;
        assertEquals(emp1,sche.getShifts().get(2).getEmployee());
        generator.assignShiftOnThatDay(4,emp1,sche,0);
        assertEquals(17.0,sche.getShifts().get(3).getStartTime());;
        assertEquals(emp1,sche.getShifts().get(3).getEmployee());
    }


    @Test
    void testIsAssignMore(){
        Schedule sche = new Schedule();
        Schedule sche2 = new Schedule();
        Employee emp1;
        Employee emp2;
        Employee emp3;
        emp1 = new Employee("Nana Fujita",new Position("Manager"),40.0,1);
        emp2 = new Employee("Sarah",new Position("Supervisor"),35.0,2);
        emp3 = new Employee("Mark",new Position("Service Assistant"),15.0,3);
        sche.addShift(new Shift (8.0,15.0,emp1, 0));
        assertTrue(generator.isAssignMore(sche,0,0,1));
        assertTrue(generator.isAssignMore(sche,0,1,1));
        sche.addShift(new Shift (8.0,15.0,emp1, 0));
        sche.addShift(new Shift (8.0,15.0,emp1, 0));
        assertFalse(generator.isAssignMore(sche,0,0,1));
        assertFalse(generator.isAssignMore(sche,0,0,2));
        assertTrue(generator.isAssignMore(sche,0,0,3));
        assertTrue(generator.isAssignMore(sche,0,0,4));
        sche.addShift(new Shift (17.0,22.0,emp1, 0));
        sche.addShift(new Shift (17.0,22.0,emp1, 0));
        sche.addShift(new Shift (17.0,22.0,emp1, 0));
        assertFalse(generator.isAssignMore(sche,0,0,4));
        assertFalse(generator.isAssignMore(sche,0,0,3));
        sche2.addShift(new Shift (10.0,17.0,emp1, 0));
        sche2.addShift(new Shift (10.0,17.0,emp1, 0));
        sche2.addShift(new Shift (10.0,17.0,emp1, 0));
        assertFalse(generator.isAssignMore(sche2,0,0,3));
        sche.addShift(new Shift (15.0,22.0,emp1, 0));
        sche.addShift(new Shift (15.0,22.0,emp1, 0));
        sche.addShift(new Shift (15.0,22.0,emp1, 0));
        assertFalse(generator.isAssignMore(sche,0,0,3));
        Schedule sche3 = new Schedule();
        sche3.addShift(new Shift (15.0,22.0,emp1, 0));
        sche3.addShift(new Shift (15.0,22.0,emp1, 0));
        sche3.addShift(new Shift (15.0,22.0,emp1, 0));
        assertFalse(generator.isAssignMore(sche3,0,0,2));
        sche.addShift(new Shift (15.0,22.0,emp1, 0));
        sche.addShift(new Shift (15.0,22.0,emp1, 0));
        sche.addShift(new Shift (15.0,22.0,emp1, 0));
        assertFalse(generator.isAssignMore(sche3,0,0,2));
        assertTrue(generator.isAssignMore(sche3,0,3,2));
    }

    @Test
    void testAssignShifts() {
        Employee emp1;
        Employee emp2;
        Employee emp3;
        Employee emp4;
        Employee emp5;
        Employee emp6;
        Employee emp7;
        Employee emp8;
        List <Employee> listEmp = new ArrayList<>();;


        emp1 = new Employee("Nana Fujita",new Position("Service Assistant"),60.0,1);
        emp2 = new Employee("Sarah",new Position("Service Assistant"),60.0,2);
        emp3 = new Employee("Mark",new Position("Service Assistant"),60.0,3);
        emp4 = new Employee("Han",new Position("Service Assistant"),60.0,4);
        emp5 = new Employee("Ann",new Position("Service Assistant"),60.0,5);
        emp6 = new Employee("Ray",new Position("Service Assistant"),60.0,6);
        emp7 = new Employee("Sana",new Position("Service Assistant"),60.0,7);
        emp8 = new Employee("Momo",new Position("Service Assistant"),60.0,8);
        listEmp.add(emp1);
        listEmp.add(emp2);
        listEmp.add(emp3);
        listEmp.add(emp4);
        listEmp.add(emp5);
        listEmp.add(emp6);
        listEmp.add(emp7);
        listEmp.add(emp8);

        Schedule sche = new Schedule();
        List<Employee> listForFail = new ArrayList<>();
        listForFail.add(emp1);
        listForFail.add(emp2);
        listForFail.add(emp3);
        boolean resultFail = generator.assignShifts(listForFail, sche, 1);
        assertFalse(resultFail);

        AvailableDay ava1 = new AvailableDay("Sunday",8.0,22.0);
        AvailableDay ava2 = new AvailableDay("Monday",8.0,22.0);
        AvailableDay ava3 = new AvailableDay("Tuesday",8.0,22.0);
        AvailableDay ava4 = new AvailableDay("Wednesday",8.0,22.0);
        AvailableDay ava5 = new AvailableDay("Thursday",8.0,22.0);
        AvailableDay ava6 = new AvailableDay("Friday",8.0,22.0);
        AvailableDay ava7 = new AvailableDay("Saturday",8.0,22.0);

        for(Employee emp: listEmp) {
            emp.getAvailability().addDay(ava1);
            emp.getAvailability().addDay(ava2);
            emp.getAvailability().addDay(ava3);
            emp.getAvailability().addDay(ava4);
            emp.getAvailability().addDay(ava5);
            emp.getAvailability().addDay(ava6);
            emp.getAvailability().addDay(ava7);
        }
        boolean resultTrue= generator.assignShifts(listEmp, sche, 1);
        assertTrue(resultTrue);
    }

    @Test
    void testCreateShiftForStaffsFalse(){
        Employee emp1;
        Employee emp2;
        Employee emp3;
        Employee emp4;
        Employee emp5;
        Employee emp6;
        Employee emp7;
        Employee emp8;
        EmployeeList listEmp = new EmployeeList();;


        emp1 = new Employee("Nana Fujita",new Position("Service Assistant"),60.0,1);
        emp2 = new Employee("Sarah",new Position("Service Assistant"),60.0,2);
        emp3 = new Employee("Mark",new Position("Service Assistant"),60.0,3);
        emp4 = new Employee("Han",new Position("Service Assistant"),60.0,4);
        emp5 = new Employee("Ann",new Position("Service Assistant"),60.0,5);
        emp6 = new Employee("Ray",new Position("Service Assistant"),60.0,6);
        emp7 = new Employee("Sana",new Position("Service Assistant"),60.0,7);
        emp8 = new Employee("Momo",new Position("Service Assistant"),60.0,8);
        listEmp.addEmployee(emp1);
        listEmp.addEmployee(emp2);
        listEmp.addEmployee(emp3);
        listEmp.addEmployee(emp4);
        listEmp.addEmployee(emp5);
        listEmp.addEmployee(emp6);
        listEmp.addEmployee(emp7);
        listEmp.addEmployee(emp8);
        Schedule sche = new Schedule();
        AvailableDay ava1 = new AvailableDay("Sunday",8.0,15.0);
        AvailableDay ava2 = new AvailableDay("Monday",8.0,15.0);
        AvailableDay ava3 = new AvailableDay("Tuesday",8.0,15.0);
        AvailableDay ava4 = new AvailableDay("Wednesday",8.0,15.0);
        AvailableDay ava5 = new AvailableDay("Thursday",8.0,15.0);
        AvailableDay ava6 = new AvailableDay("Friday",8.0,15.0);
        AvailableDay ava7 = new AvailableDay("Saturday",8.0,15.0);

        assertFalse(generator.createShiftForStaffs(listEmp,sche));

        for(Employee emp: listEmp.getListEmployee()) {
            emp.getAvailability().addDay(ava1);
            emp.getAvailability().addDay(ava2);
            emp.getAvailability().addDay(ava3);
            emp.getAvailability().addDay(ava4);
            emp.getAvailability().addDay(ava5);
            emp.getAvailability().addDay(ava6);
            emp.getAvailability().addDay(ava7);
        }
        assertFalse(generator.createShiftForStaffs(listEmp,sche));

    }
    @Test
    void testCreateShiftForStaffsSuccess(){
        Employee emp1;
        Employee emp2;
        Employee emp3;
        Employee emp4;
        Employee emp5;
        Employee emp6;
        Employee emp7;
        Employee emp8;
        EmployeeList listEmp = new EmployeeList();


        emp1 = new Employee("Nana Fujita",new Position("Service Assistant"),60.0,1);
        emp2 = new Employee("Sarah",new Position("Service Assistant"),60.0,2);
        emp3 = new Employee("Mark",new Position("Service Assistant"),60.0,3);
        emp4 = new Employee("Han",new Position("Service Assistant"),60.0,4);
        emp5 = new Employee("Ann",new Position("Service Assistant"),60.0,5);
        emp6 = new Employee("Ray",new Position("Service Assistant"),60.0,6);
        emp7 = new Employee("Sana",new Position("Service Assistant"),60.0,7);
        emp8 = new Employee("Momo",new Position("Service Assistant"),60.0,8);
        listEmp.addEmployee(emp1);
        listEmp.addEmployee(emp2);
        listEmp.addEmployee(emp3);
        listEmp.addEmployee(emp4);
        listEmp.addEmployee(emp5);
        listEmp.addEmployee(emp6);
        listEmp.addEmployee(emp7);
        listEmp.addEmployee(emp8);
        Schedule sche = new Schedule();
        AvailableDay ava1 = new AvailableDay("Sunday",8.0,22.0);
        AvailableDay ava2 = new AvailableDay("Monday",8.0,22.0);
        AvailableDay ava3 = new AvailableDay("Tuesday",8.0,22.0);
        AvailableDay ava4 = new AvailableDay("Wednesday",8.0,22.0);
        AvailableDay ava5 = new AvailableDay("Thursday",8.0,22.0);
        AvailableDay ava6 = new AvailableDay("Friday",8.0,22.0);
        AvailableDay ava7 = new AvailableDay("Saturday",8.0,22.0);


        for(Employee emp: listEmp.getListEmployee()) {
            emp.getAvailability().addDay(ava1);
            emp.getAvailability().addDay(ava2);
            emp.getAvailability().addDay(ava3);
            emp.getAvailability().addDay(ava4);
            emp.getAvailability().addDay(ava5);
            emp.getAvailability().addDay(ava6);
            emp.getAvailability().addDay(ava7);
        }
        assertTrue(generator.createShiftForStaffs(listEmp,sche));

    }

    @Test
    void testCreateShiftForManagersFalse(){
        Employee emp1;
        Employee emp2;
        Employee emp3;
        Employee emp4;
        Employee emp5;
        Employee emp6;
        EmployeeList listEmp = new EmployeeList();
        Schedule sche = new Schedule();
        emp1 = new Employee("Nana Fujita",new Position("Manager"),60.0,1);
        emp2 = new Employee("Sarah",new Position("Supervisor"),60.0,2);
        emp3 = new Employee("Mark",new Position("Supervisor"),60.0,3);
        emp4 = new Employee("Han",new Position("Supervisor"),60.0,4);
        emp5 = new Employee("Ann",new Position("Supervisor"),60.0,5);
        emp6 = new Employee("Ehe",new Position("Supervisor"),60.0,6);
        listEmp.addEmployee(emp1);
        boolean result1 = generator.createShiftForManagers(listEmp,sche);
        assertFalse(result1);
        listEmp.addEmployee(emp2);
        listEmp.addEmployee(emp3);
        listEmp.addEmployee(emp4);
        listEmp.addEmployee(emp5);
        listEmp.addEmployee(emp6);
        AvailableDay ava1 = new AvailableDay("Sunday",8.0,15.0);
        AvailableDay ava2 = new AvailableDay("Monday",8.0,15.0);
        AvailableDay ava3 = new AvailableDay("Tuesday",8.0,15.0);
        AvailableDay ava4 = new AvailableDay("Wednesday",8.0,15.0);
        AvailableDay ava5 = new AvailableDay("Thursday",8.0,15.0);
        AvailableDay ava6 = new AvailableDay("Friday",8.0,15.0);
        AvailableDay ava7 = new AvailableDay("Saturday",8.0,15.0);


        for(Employee emp: listEmp.getListEmployee()) {
            emp.getAvailability().addDay(ava1);
            emp.getAvailability().addDay(ava2);
            emp.getAvailability().addDay(ava3);
            emp.getAvailability().addDay(ava4);
            emp.getAvailability().addDay(ava5);
            emp.getAvailability().addDay(ava6);
            emp.getAvailability().addDay(ava7);
        }
        boolean result2 = generator.createShiftForManagers(listEmp,sche);
        assertFalse(result2);
    }

    @Test
    void testCreateShiftForManagersTrue(){
        Employee emp1;
        Employee emp2;
        Employee emp3;
        Employee emp4;
        Employee emp5;
        Employee emp6;
        EmployeeList listEmp = new EmployeeList();
        Schedule sche = new Schedule();
        emp1 = new Employee("Nana Fujita",new Position("Manager"),60.0,1);
        emp2 = new Employee("Sarah",new Position("Supervisor"),60.0,2);
        emp3 = new Employee("Mark",new Position("Supervisor"),60.0,3);
        emp4 = new Employee("Han",new Position("Supervisor"),60.0,4);
        emp5 = new Employee("Ann",new Position("Supervisor"),60.0,5);
        emp6 = new Employee("Ehe",new Position("Supervisor"),60.0,6);
        listEmp.addEmployee(emp1);;
        listEmp.addEmployee(emp2);
        listEmp.addEmployee(emp3);
        listEmp.addEmployee(emp4);
        listEmp.addEmployee(emp5);
        listEmp.addEmployee(emp6);
        AvailableDay ava1 = new AvailableDay("Sunday",8.0,22.0);
        AvailableDay ava2 = new AvailableDay("Monday",8.0,22.0);
        AvailableDay ava3 = new AvailableDay("Tuesday",8.0,22.0);
        AvailableDay ava4 = new AvailableDay("Wednesday",8.0,22.0);
        AvailableDay ava5 = new AvailableDay("Thursday",8.0,22.0);
        AvailableDay ava6 = new AvailableDay("Friday",8.0,22.0);
        AvailableDay ava7 = new AvailableDay("Saturday",8.0,22.0);


        for(Employee emp: listEmp.getListEmployee()) {
            emp.getAvailability().addDay(ava1);
            emp.getAvailability().addDay(ava2);
            emp.getAvailability().addDay(ava3);
            emp.getAvailability().addDay(ava4);
            emp.getAvailability().addDay(ava5);
            emp.getAvailability().addDay(ava6);
            emp.getAvailability().addDay(ava7);
        }
        boolean result = generator.createShiftForManagers(listEmp,sche);
        assertTrue(result);
    }


}
