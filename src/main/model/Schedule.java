package model;

import java.util.*;

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

    private boolean isPositionAtThatTime(Shift shift, int positionID, double shiftStart) {
        return shift.getEmployee().getPositionID() == positionID && shift.getStartTime() == shiftStart;
    }
}
