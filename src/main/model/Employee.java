package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/*
    Employee class has employee's name and Position for the employee,
    and availability for each employee also idealWorkTime per week,
    totalWorkTime per week and contains empID.
 */
public class Employee implements Writable {
    private String name;
    private Position position;
    private Availability availability;
    private double idealWorkTime;
    private double totalWorkTime;
    private int empID;

    /*
     * REQUIRES: ideal >= 0, empID != 200
     * EFFECTS: assign name, position,idealWorkTime, totalWorkTime and empID
     */

    public Employee(String name,Position position,double ideal,int empID) {
        this.name = name;
        this.position = position;
        availability = new Availability();
        this.idealWorkTime = ideal;
        this.totalWorkTime = 0;
        this.empID = empID;
    }

    public int getID() {
        return empID;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public double getIdealWorkTime() {
        return idealWorkTime;
    }

    public int getPositionID() {
        return position.getPositionID();
    }

    /*
     * REQUIRES: time >= 0
     * MODIFIES: this
     * EFFECTS: add time to totalWorkTime
     */
    public void addWorkTime(double time) {
        totalWorkTime += time;
    }

    public double getTotalWorkTime() {
        return totalWorkTime;
    }


    /*
     * REQUIRES: workTime >= 0
     * EFFECTS: If the person's totalWorkTime is same as idealWorkTime or the difference between
     * these two number is less than workTime, return true. Otherwise false.
     */
    public boolean isWorkEnough(double workTime) {
        return idealWorkTime - totalWorkTime == 0 || idealWorkTime - totalWorkTime < workTime;
    }

    public Availability getAvailability() {
        return availability;
    }

    /*
     * EFFECTS: return objects as json object
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("positionName", position.getPositionName());
        json.put("availability", availabilityToJson());
        json.put("idealWorkTime", idealWorkTime);
        json.put("empID",empID);
        return json;
    }

    // EFFECTS: returns employee in this employee list as a JSON array
    private JSONArray availabilityToJson() {
        JSONArray jsonArray = new JSONArray();

        for (AvailableDay ava : availability.getListAvailability()) {
            jsonArray.put(ava.toJson());
        }

        return jsonArray;
    }

}
