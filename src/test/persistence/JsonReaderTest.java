package persistence;

import model.EmployeeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            EmployeeList empList = reader.read();
            fail("Exception expected");
        } catch (Exception e) {
            // pass
        }
    }

    @Test
    public void testReadFileSuccess() {
        JsonReader jsonReader = new JsonReader("./data/testWriterGeneralEmpList.json");
        try {
            EmployeeList empList= jsonReader.read();
            assertNotNull(empList);
            assertEquals(1,empList.getListEmployee().size());
        } catch (Exception e) {
            fail("not expected to thrown");
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testEmptyEmpList.json");
        try {
            EmployeeList empList = reader.read();
            assertEquals(0, empList.getListEmployee().size());
        } catch (Exception e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralEmpList.json");
        try {
            EmployeeList empList = reader.read();
            assertEquals(1, empList.getListEmployee().size());
        } catch (Exception e) {
            fail("Couldn't read from file");
        }
    }

}
