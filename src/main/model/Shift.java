package model;

import java.util.*;

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

    public int getDateNum() {
        return dateNum;
    }

    public Employee getEmployee() {
        return assignedEmployee;
    }

}
