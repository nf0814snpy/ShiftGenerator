package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// This class represents a list of employees.
public class EmployeeList implements Writable {

    private List<Employee> empList;

    // EFFECTS: constructs an empty list of employees
    public EmployeeList() {
        empList = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: add new availableDay object to a list
     */
    public void addEmployee(Employee emp) {
        empList.add(emp);

        EventLog eventLog = EventLog.getInstance();
        String logMessage = "Employee added: " + emp.getName() + " (ID: " + emp.getID() + ", Position: "
                + emp.getPosition().getPositionName() + ", Ideal Work Time: " + emp.getIdealWorkTime() + ")";
        Event logEvent = new Event(logMessage);
        eventLog.logEvent(logEvent);
    }

    public void addEmployeeForLoad(Employee emp) {
        empList.add(emp);
    }

    public void removeLog(Employee emp) {
        EventLog eventLog = EventLog.getInstance();
        String logMessage = "Employee removed: " + emp.getName()  + " (ID: " + emp.getID() + ", Position: "
                + emp.getPosition().getPositionName() + ", Ideal Work Time: " + emp.getIdealWorkTime() + ")";;
        Event logEvent = new Event(logMessage);
        eventLog.logEvent(logEvent);
    }

    public List<Employee> getListEmployee() {
        return empList;
    }

    /*
     * EFFECTS: return objects(employee) as json object
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("employees", employeesToJson());
        return json;
    }

    // EFFECTS: returns employee in this list as a JSON array
    private JSONArray employeesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Employee e : empList) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
