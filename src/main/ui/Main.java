package ui;

import model.*;
import java.util.*;



public class Main {
    public static void main(String[] args) {
        System.out.println("Generate and Print shift? Press Y or N");

        Scanner myObj = new Scanner(System.in);
        String answer = myObj.nextLine();
        if (answer.equals("Y")) {
            List<Employee> empList = empGenerate();
            Schedule shiftSchedule = new Schedule();
            ShiftGenerator generator = new ShiftGenerator();
            if (generator.shiftGen(empList, shiftSchedule)) {
                System.out.println("Assign shift correctly!");
                printSchedule(shiftSchedule);
            } else {
                System.out.println("Shift Assign Fail! Hire more people.");
            }
        }
    }

    public static List<Employee> empGenerate() {
        List<Employee> empList = new ArrayList<>();

        Employee man = new Employee("Nana Fujita",new Position("Manager"),30.0,1);
        Employee sup1 = new Employee("Jepa",new Position("Supervisor"),30.0,2);
        Employee sup2 = new Employee("Hansel",new Position("Supervisor"),30.0,3);
        Employee sup3 = new Employee("Stanley",new Position("Supervisor"),30.0,4);
        empList.add(man);
        empList.add(sup1);
        empList.add(sup2);
        empList.add(sup3);
        Employee[] emp = new Employee[12];

        for (int i = 0; i < 12; i++) {
            emp[i] = new Employee("emp" + (i + 1), new Position("Service Assistant"), 40.0,4 + i);
            empList.add(emp[i]);
        }
        assignEmpList(empList);
        return empList;
    }

    public static void assignEmpList(List<Employee> empList) {
        AvailableDay[] availableDays = new AvailableDay[] {
                new AvailableDay("Sunday", 8.0, 22.0),
                new AvailableDay("Monday", 8.0, 22.0),
                new AvailableDay("Tuesday", 8.0, 22.0),
                new AvailableDay("Wednesday", 8.0, 22.0),
                new AvailableDay("Thursday", 8.0, 22.0),
                new AvailableDay("Friday", 8.0, 22.0),
                new AvailableDay("Saturday", 8.0, 22.0)
        };

        for (Employee emp : empList) {
            for (AvailableDay ava : availableDays) {
                emp.getAvailability().addDay(ava);
            }
        }
    }

    public static void printSchedule(Schedule sche) {
        List<Shift> list = sche.getShifts();
        Collections.sort(list, new Comparator<Shift>() {
            @Override
            public int compare(Shift shift1, Shift shift2) {
                if (shift1.getDateNum() != shift2.getDateNum()) {
                    return Integer.compare(shift1.getDateNum(), shift2.getDateNum());
                }
                return Double.compare(shift1.getStartTime(), shift2.getStartTime());
            }
        });

        for (Shift shift : list) {
            printShift(shift);
        }
    }

    public static void printShift(Shift shift) {
        String[] daysOfWeek = { "Sun", "Mon", "Tue", "Wen", "Thurs", "Fri", "Sat" };
        String date = daysOfWeek[shift.getDateNum()];

        System.out.println(date + shift.getStartTime() + " - "
                + shift.getEndTime() + " " + shift.getEmployee().getName());
    }

}
