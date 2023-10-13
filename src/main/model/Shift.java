package model;

import java.util.*;

// This class is an object for Schedule class.
//   This holds who are assigned to this shift. And start and end time of the shift on the day of week.
public class Shift {
    double startTime;
    double endTime;
    int dateNum;
    Employee assignedEmployee;


    public Shift(double startTime,double endTime,Employee assignedEmployee, int dateNum) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.assignedEmployee = assignedEmployee;
        this.dateNum = dateNum;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public int getDateNum() {
        return dateNum;
    }

    public Employee getEmployee() {
        return assignedEmployee;
    }
}
