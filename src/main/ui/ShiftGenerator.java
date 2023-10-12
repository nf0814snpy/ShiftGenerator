package ui;

import model.AvailableDay;
import model.Employee;
import model.Schedule;
import model.Shift;

import java.util.*;

public class ShiftGenerator {

    public Schedule shiftGen(Date startDate, List<Employee> listOfEmployee) {
        Schedule result = new Schedule();
        List<Employee> managerAndSuper = new ArrayList<Employee>();
        List<Employee> sa = new ArrayList<Employee>();
        assignMemberToList(listOfEmployee,managerAndSuper,0);
        assignMemberToList(listOfEmployee,managerAndSuper,1);
        assignMemberToList(listOfEmployee,sa,2);
        createShiftForManagers(managerAndSuper,result);
        createShiftForStaffs(sa,result);
        return result;
    }

    public boolean checkBeyondTime(AvailableDay availableDay, double startTime, double endTime) {
        if (availableDay.getStartTime() < startTime && availableDay.getEndTime() > endTime) {
            return true;
        }
        return false;
    }

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

    private double workTime(int shiftNum) {
        if (shiftNum == 4) {
            return 5.0;
        }
        return 7.0;
    }

    private double getShiftEndNum(int shiftNum) {
        switch (shiftNum) {
            case 1:
                return 15.0;
            case 2:
                return 17.0;
            default:
                return 22.0;
        }
    }

    public boolean isAbleToAssignShift(Employee emp,int date, int shiftNum) {
        double start = getShiftStartNum(shiftNum);
        double end = getShiftEndNum(shiftNum);

        for (AvailableDay ava: emp.getAvailability().getListAvailability()) {
            if (date == ava.getNumOfDate()) {
                if (checkBeyondTime(ava, start,end) && !emp.isWorkEnough(end - start)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int assignShift1(List<Employee> list, Schedule result, Employee previousWorker, int date, int count) {
        for (Employee emp : list) {
            if (emp.equals(previousWorker)) {
                continue;
            }
            if (isAbleToAssignShift(emp, date,1)) {
                Shift temp = new Shift(8.0, 15.0, emp, date);
                result.addShift(temp);
                emp.addWorkTime(7.0);
                previousWorker = emp;
                count++;
                break;
            }
        }
        return count;
    }

    private int assignShift3(List<Employee> list, Schedule result, Employee previousWorker, int i, int count) {
        for (Employee emp : list) {
            if (emp.equals(previousWorker)) {
                continue;
            }
            if (isAbleToAssignShift(emp, i,3)) {
                Shift temp = new Shift(15.0, 22.0, emp, i);
                result.addShift(temp);
                emp.addWorkTime(7.0);
                previousWorker = emp;
                count++;
                break;
            }
        }
        return count;
    }

    public boolean createShiftForManagers(List<Employee> list,Schedule result) {
        Employee previousWorker = null;
        int count = 0;
        for (int i = 0;i < 7;i++) {
            if (i != 0 && count != 2) {
                return false;
            }
            count = assignShift1(list,result,previousWorker,i, count);
            if (count != 1) {
                return false;
            }
            count = assignShift3(list,result,previousWorker,i,count);
        }
        for (Employee emp: list) {
            fillInShifts(emp, result);
        }
        return true;
    }

    private Shift createShiftForShiftNum(int shiftNum, Employee emp, int date) {
        double startHour = shiftNum == 1 ? 8.0 : 15.0;
        double endHour = shiftNum == 1 ? 15.0 : 22.0;
        Shift temp = new Shift(startHour, endHour, emp, date);
        emp.addWorkTime(7.0);
        return temp;
    }

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


    private void fillInShifts(Employee emp, Schedule result) {
        int countDate = 6;
        while (countDate > -1) {
            for (int i = 1;i < 5;i++) {
                if (isWorkOnThatDay(emp,countDate, result)) {
                    continue;
                }
                if (emp.isWorkEnough(workTime(i))) {
                    continue;
                }
                if (isAssignMore(result, countDate, emp.getPositionID(), i) && isAbleToAssignShift(emp,countDate,i)) {
                    assignShiftOnThatDay(i,emp,result,countDate);
                }
            }
            countDate--;
        }
    }

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

    public boolean isAssignMore(Schedule schedule,int date,int id, int shiftNum) {
        int limit = 6;
        if (id == 0 || id == 1) {
            limit = 2;
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


    public boolean isWorkOnThatDay(Employee emp, int date, Schedule schedule) {
        boolean result = false;
        List<Shift> temp = new ArrayList<>();
        for (Shift shift:schedule.getShifts()) {
            if (shift.getDateNum() == date && emp.equals(shift.getEmployee())) {
                result = true;
                break;
            }
        }
        return result;
    }



    public void assignMemberToList(List<Employee> originalList, List<Employee> listByPosition, int employeeID) {
        for (Employee employee: originalList) {
            if (employee.getPositionID() == employeeID) {
                listByPosition.add(employee);
            }
        }
    }

}
