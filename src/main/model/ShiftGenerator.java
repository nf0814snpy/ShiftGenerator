package model;

import java.util.*;

//Generate shift corresponds to availability and Employee information
public class ShiftGenerator {

    //EFFECTS: generate shift. If it is possible, return true. Otherwise false.
    public boolean shiftGen(List<Employee> listOfEmployee,Schedule result) {
        List<Employee> managerAndSuper = new ArrayList<Employee>();
        List<Employee> sa = new ArrayList<Employee>();
        assignMemberToList(listOfEmployee,managerAndSuper,0);
        assignMemberToList(listOfEmployee,managerAndSuper,1);
        assignMemberToList(listOfEmployee,sa,2);
        if (!createShiftForManagers(managerAndSuper,result)) {
            return false;
        }
        if (!createShiftForStaffs(sa,result)) {
            return false;
        }
        return true;
    }

    /*
     * REQUIRES: 8<=startTime<=22 && 8<=endTime<=22
     * EFFECTS: check whether we can assign the shift time to the availability
     */
    public boolean checkNotBeyondTime(AvailableDay availableDay, double startTime, double endTime) {
        if ((availableDay.getStartTime() <= startTime) && (availableDay.getEndTime() >= endTime)) {
            return true;
        }
        return false;
    }

    //REQUIRES: shiftNum =1,2,3 or 4
    //EFFECTS: return the start time of shiftNum
    public double getShiftStartNum(int shiftNum) {
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

    //REQUIRES: shiftNum =1,2,3 or 4
    //EFFECTS: return the work time of shiftNum
    public double workTime(int shiftNum) {
        if (shiftNum == 4) {
            return 5.0;
        }
        return 7.0;
    }

    //REQUIRES: shiftNum =1,2,3 or 4
    //EFFECTS: return the end time of shiftNum
    public double getShiftEndNum(int shiftNum) {
        switch (shiftNum) {
            case 1:
                return 15.0;
            case 2:
                return 17.0;
            default:
                return 22.0;
        }
    }

    //EFFECTS: check whether we can assign specific shift on that day to the employee
    public boolean isAbleToAssignShift(Employee emp,int date, int shiftNum) {
        double start = getShiftStartNum(shiftNum);
        double end = getShiftEndNum(shiftNum);

        for (AvailableDay ava: emp.getAvailability().getListAvailability()) {
            if (date == ava.getNumOfDate()) {
                if (checkNotBeyondTime(ava, start,end) && !emp.isWorkEnough(end - start)) {
                    return true;
                }
            }
        }
        return false;
    }

    //MODIFIES: result, emp, empID
    //EFFECTS: assign shiftNum1 to the employee in the list if it is possible and add the shift
    //to the schedule
    public boolean assignShift1(List<Employee> list, Schedule result, IntegerWrapper empID, int date) {
        for (Employee emp : list) {
            if (emp.getID() == empID.getValue()) {
                continue;
            }
            if (isAbleToAssignShift(emp, date,1)) {
                Shift temp = new Shift(8.0, 15.0, emp, date);
                result.addShift(temp);
                emp.addWorkTime(7.0);
                empID.setValue(emp.getID());
                return true;
            }
        }
        return false;
    }

    //MODIFIES: result, list, empID
    //EFFECTS: assign shiftNum3 to the employee in the list if it is possible and add the shift
    //to the schedule
    public boolean assignShift3(List<Employee> list, Schedule result, IntegerWrapper empID, int i) {
        for (Employee emp : list) {
            if (empID.getValue() == emp.getID()) {
                continue;
            }
            if (isAbleToAssignShift(emp, i,3)) {
                Shift temp = new Shift(15.0, 22.0, emp, i);
                result.addShift(temp);
                emp.addWorkTime(7.0);
                empID.setValue(emp.getID());
                return true;
            }
        }
        return false;
    }

    //MODIFIES: list, schedule
    //EFFECTS: create shift for managers. It helps assign at least one
    //manager or supervisor for each time
    public boolean createShiftForManagers(List<Employee> list,Schedule result) {
        Employee previousWorker = new Employee("Comparison",new Position("Non"),0,200);
        int iterate = 0;
        int empID = 100;
        IntegerWrapper integer = new IntegerWrapper(empID);
        do {
            if (!assignShift1(list,result,integer,iterate)) {
                return false;
            }
            if (!assignShift3(list,result,integer,iterate)) {
                return false;
            }
            iterate++;
        } while (iterate < 7);
        for (Employee emp: list) {
            fillInShifts(emp, result);
        }
        return true;
    }

    //MODIFIES emp
    //EFFECTS: assign shift for the employee on the day at specific shift time.
    public Shift createShiftForShiftNum(int shiftNum, Employee emp, int date) {
        double startHour = 15.0;
        double endHour = 22.0;
        if (shiftNum == 1) {
            startHour = 8.0;
            endHour = 15.0;
        }
        Shift temp = new Shift(startHour, endHour, emp, date);
        emp.addWorkTime(7.0);
        return temp;
    }

    //REQUIRES: 1 or 3
    //MODIFIES: result
    //EFFECTS: assign shift for every employee in the list. We asssign at least 4 employee
    //for each shift. If we could satisfy minimum requirement (4 person)for the shift, return true.
    //Otherwise, false.
    public boolean assignShifts(List<Employee> list, Schedule result, int shiftNum) {
        for (int i = 0; i < 7; i++) {
            int empCount = 0;
            for (Employee emp : list) {
                if (isAbleToAssignShift(emp, i, shiftNum)) {
                    Shift temp = createShiftForShiftNum(shiftNum, emp, i);
                    result.addShift(temp);
                    empCount++;
                    if (empCount >= 4) {
                        break;
                    }
                }
            }
            if (empCount < 4) {
                return false;
            }
        }
        return true;
    }

    /*
     * MODIFIES: list and result
     * EFFECTS: create shift for all staffs in the list.
     */
    public boolean createShiftForStaffs(List<Employee> list,Schedule result) {

        if (!assignShifts(list, result, 1)) {
            return false;
        }
        if (!assignShifts(list, result, 3)) {
            return false;
        }

        for (Employee emp: list) {
            fillInShifts(emp, result);
        }

        return true;
    }

    /*
     * MODIFIES: result and emp
     * EFFECTS: after assigned minimum requirement for the shift, this class will assign shift to employee
     * as much as they can according to the availability.
     */
    public void fillInShifts(Employee emp, Schedule result) {
        int countDate = 6;
        while (countDate > -1) {
            if (!isWorkOnThatDay(emp,countDate, result)) {
                for (int i = 1; i < 5; i++) {
                    if (emp.isWorkEnough(workTime(i))) {
                        continue;
                    }
                    if (isAssignMore(result, countDate, emp.getPositionID(), i)
                            && isAbleToAssignShift(emp, countDate, i) && !isWorkOnThatDay(emp,countDate, result)) {
                        assignShiftOnThatDay(i, emp, result, countDate);
                    }
                }
            }
            countDate--;
        }
    }

    //REQUIRES: date 0-6 , shiftNumber = 1, 2, 3 or 4
    //EFFECTS assign employee to the specific shift on that day
    public void assignShiftOnThatDay(int shiftNumber,Employee emp,Schedule schedule, int date) {
        if (shiftNumber == 1) {
            Shift temp = new Shift(8.0,15.0,emp,date);
            schedule.addShift(temp);
            emp.addWorkTime(7.0);
        } else if (shiftNumber == 2) {
            Shift temp = new Shift(10.0,17.0,emp,date);
            schedule.addShift(temp);
            emp.addWorkTime(7.0);
        } else if (shiftNumber == 3) {
            Shift temp = new Shift(15.0,22.0,emp,date);
            schedule.addShift(temp);
            emp.addWorkTime(7.0);
        } else {
            Shift temp = new Shift(17.0,22.0,emp,date);
            schedule.addShift(temp);
            emp.addWorkTime(5.0);
        }
    }

    //REQUIRES: shiftNum = 1, 2, 3 or 4 and date = 0-6
    //EFFECTS: check whether we can assign more people to the specific shift
    //on that day or not
    public boolean isAssignMore(Schedule schedule,int date,int id, int shiftNum) {
        int limit = 4;
        if (id == 0 || id == 1) {
            limit = 1;
        }
        if (shiftNum == 1) {
            int ppl = schedule.countShifts(date, shiftNum, id) + schedule.countShifts(date, shiftNum + 1, id);
            return ppl <= limit;
        } else if (shiftNum == 2) {
            int ppl1 = schedule.countShifts(date, shiftNum, id) + schedule.countShifts(date, shiftNum - 1,id);
            int ppl2 = schedule.countShifts(date, shiftNum, id) + schedule.countShifts(date, shiftNum + 1,id);
            return ppl1 <= limit && ppl2 <= limit;
        } else if (shiftNum == 3) {
            int ppl1 = schedule.countShifts(date, shiftNum, id) + schedule.countShifts(date, shiftNum - 1,id);
            int ppl2 = schedule.countShifts(date, shiftNum, id) + schedule.countShifts(date, shiftNum + 1,id);
            return ppl1 <= limit && ppl2 <= limit;
        } else {
            int ppl = schedule.countShifts(date, shiftNum, id) + schedule.countShifts(date, shiftNum - 1, id);
            return ppl <= limit;
        }

    }

    /*
     * EFFECTS: check whether the person has the shift on that day
     */
    public boolean isWorkOnThatDay(Employee emp, int date, Schedule schedule) {
        List<Shift> listShift = schedule.getShifts();
        List<Employee> temp = new ArrayList<>();
        for (Shift shift : listShift) {
            if (shift.getDateNum() == date) {
                temp.add(shift.getEmployee());
            }
        }
        return temp.contains(emp);
    }


    //MODIFIES: listByPosition
    //EFFECTS: create new list by position
    public void assignMemberToList(List<Employee> originalList, List<Employee> listByPosition, int positionID) {
        for (Employee employee: originalList) {
            if (employee.getPositionID() == positionID) {
                listByPosition.add(employee);
            }
        }
    }

}
