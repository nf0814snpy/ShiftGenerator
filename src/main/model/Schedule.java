package model;

import java.util.*;

/*
  This class has a list of shift for entire week.
 */
public class Schedule {
    List<Shift> shift;

    public Schedule() {
        shift = new ArrayList<>();
    }

    public void addShift(Shift newShift) {
        shift.add(newShift);
    }

    public List<Shift> getShifts() {
        return shift;
    }

    //EFFECTS: count how many shift was assigned in the list on the day at the same time for the position ID
    public int countShifts(int date, int shiftNum, int positionID) {
        int count = 0;
        double shiftStart = getShiftStartNum(shiftNum);

        for (Shift shifts : shift) {
            if (shifts.getDateNum() == date && isPositionAtThatTime(shifts, positionID, shiftStart)) {
                count++;
            }
        }

        return count;
    }

    /*
     * EFFECTS: get start time according to shiftNumber
     */
    private double getShiftStartNum(int shiftNum) {
        switch (shiftNum) {
            case 1:
                return 8.0;
            case 2:
                return 10.0;
            case 3:
                return 15.0;
            default:
                return 17.0;
        }
    }

    //EFFECTS: return whether there is a person who are assigned and has same positionID to the shift
    // at specific shiftTime
    private boolean isPositionAtThatTime(Shift shift, int positionID, double shiftStart) {
        return shift.getEmployee().getPositionID() == positionID && shift.getStartTime() == shiftStart;
    }
}
