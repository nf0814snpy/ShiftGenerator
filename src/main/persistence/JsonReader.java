package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public EmployeeList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEmpList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        } catch (Exception ex) {
            System.out.println("Exception catched");
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private EmployeeList parseEmpList(JSONObject jsonObject) {
        EmployeeList empList = new EmployeeList();
        addEmployees(empList, jsonObject);
        return empList;
    }

    // MODIFIES: empList
    // EFFECTS: parses employees from a JSON object and adds them to the EmployeeList
    private void addEmployees(EmployeeList empList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("employees");
        for (Object json : jsonArray) {
            JSONObject nextEmployee = (JSONObject) json;
            addEmp(empList, nextEmployee);
        }
    }

    // MODIFIES: empList
    // EFFECTS: parses an employee from a JSON object and adds it to the EmployeeList
    private void addEmp(EmployeeList empList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Position pos = new Position(jsonObject.getString("positionName"));
        double idealWorkTime = jsonObject.getDouble("idealWorkTime");
        int empID = jsonObject.getInt("empID");
        Employee emp = new Employee(name,pos,idealWorkTime,empID);
        JSONArray jsonArray = jsonObject.getJSONArray("availability");
        for (Object json : jsonArray) {
            JSONObject nextAvailability = (JSONObject) json;
            addAvailability(emp, nextAvailability);
        }
        empList.addEmployee(emp);
    }

    // MODIFIES: emp
    // EFFECTS: parses an availability day from a JSON object and adds it to the employee's availability
    private void addAvailability(Employee emp, JSONObject jsonObject) {
        String dayOfWeek = jsonObject.getString("dayOfWeek");
        double startTime = jsonObject.getDouble("startTime");
        double endTime = jsonObject.getDouble("endTime");
        AvailableDay avaDay = new AvailableDay(dayOfWeek,startTime,endTime);
        emp.getAvailability().addDay(avaDay);
    }
}
