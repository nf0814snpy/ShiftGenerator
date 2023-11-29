package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            EmployeeList empList = new EmployeeList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Exception was expected");
        } catch (Exception e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            EmployeeList empList = new EmployeeList();
            JsonWriter writer = new JsonWriter("./data/testEmptyEmpList.json");
            writer.open();
            writer.write(empList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyEmpList.json");
            empList = reader.read();
            assertEquals(0, empList.getListEmployee().size());
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            EmployeeList empList = new EmployeeList();
            Employee emp1;
            emp1 = new Employee("Nana Fujita",new Position("Manager"),40.0,1);
            AvailableDay ava1 = new AvailableDay("Sunday",8.0,22.0);
            AvailableDay ava3 = new AvailableDay("Monday",8.0,22.0);
            AvailableDay ava4 = new AvailableDay("Tuesday",9.0,10.0);
            AvailableDay ava5 = new AvailableDay("Wednesday",8.0,22.0);
            AvailableDay ava6 = new AvailableDay("Thursday",8.0,22.0);
            emp1.getAvailability().addDay(ava1,emp1);
            emp1.getAvailability().addDay(ava3,emp1);
            emp1.getAvailability().addDay(ava4,emp1);
            emp1.getAvailability().addDay(ava5,emp1);
            emp1.getAvailability().addDay(ava6,emp1);
            empList.addEmployee(emp1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralEmpList.json");
            writer.open();
            writer.write(empList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralEmpList.json");
            empList = reader.read();
            assertEquals(1, empList.getListEmployee().size());

        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}
