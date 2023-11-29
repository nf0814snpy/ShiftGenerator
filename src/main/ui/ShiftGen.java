package ui;

import model.*;
import persistence.*;

import java.util.*;

// Represents the Shift Generation application
public class ShiftGen {
    private static final String JSON_STORE = "./data/EmployeeInfo.json";
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private EmployeeList empList;

    // MODIFIES: this
    // EFFECTS: constructs the ShiftGen application
    public ShiftGen() {
        input = new Scanner(System.in);
        empList = new EmployeeList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        //runEmpList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input and runs the application
    private void runEmpList() {
        boolean keepGoing = true;
        int command;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.nextInt();

            if (command == 9) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    //
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> generate shift");
        System.out.println("\t2 -> add employee");
        System.out.println("\t3 -> delete employee");
        System.out.println("\t4 -> add availability of employee");
        System.out.println("\t5 -> delete availability of employee");
        System.out.println("\t6 -> see employee info");
        System.out.println("\t7 -> save employee and availability to file");
        System.out.println("\t8 -> load employee and availability info from file");
        System.out.println("\t9 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(int command) {
        if (command == 1) {
            generateShift(empList);
        } else if (command == 2) {
            addEmp();
        } else if (command == 3) {
            deleteEmp();
        } else if (command == 4) {
            addAvaToEmp();
        } else if (command == 5) {
            deleteAvaToEmp();
        } else if (command == 6) {
            seeEmpInfo();
        } else if (command == 7) {
            saveEmpInfo(empList);
        } else if (command == 8) {
            loadEmpInfo();
        } else {
            System.out.println("Selection not valid...");
        }
    }
//
    // MODIFIES: this
    // EFFECTS: deletes an availability from an employee
    private void deleteAvaToEmp() {
        System.out.println("Enter the number of employee who you want to delete availability:");
        seeEmpName();
        int num;
        num = input.nextInt();
        System.out.println("Which day of week do you want to delete (Enter Sunday, Monday ... or Saturday):");
        showAvailability(empList.getListEmployee().get(num - 1));
        String dayOfWeek;
        Scanner myObj = new Scanner(System.in);
        dayOfWeek = myObj.nextLine();
        for (AvailableDay avaDay: empList.getListEmployee().get(num - 1).getAvailability().getListAvailability()) {
            if (avaDay.getDayOfWeek().equals(dayOfWeek)) {
                empList.getListEmployee().get(num - 1).getAvailability().getListAvailability().remove(avaDay);
                break;

            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an availability to an employee
    private void addAvaToEmp() {
        if (empList.getListEmployee().size() != 0) {
            System.out.println("Enter the number of employee who you want to add availability:");
            seeEmpName();
            int num;
            num = input.nextInt();
            empList.getListEmployee().get(num - 1);
            String dayOfWeek;
            System.out.println("Enter the day of week(Sunday, Monday ... Saturday):");
            Scanner myObj = new Scanner(System.in);
            dayOfWeek = myObj.nextLine();
            System.out.println("Enter start time(if 8:30am, enter 8.5):");
            double start = input.nextDouble();
            System.out.println("Enter end time(if 21:30am, enter 21.5):");
            double end = input.nextDouble();
            AvailableDay newAva = new AvailableDay(dayOfWeek, start, end);
            empList.getListEmployee().get(num - 1)
                    .getAvailability().addDay(newAva,empList.getListEmployee().get(num - 1));
        } else {
            System.out.println("List is empty");
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes an employee from the list
    private void deleteEmp() {
        System.out.println("Enter the number to delete employee:");
        seeEmpName();
        int num;
        num = input.nextInt();
        if (num - 1 < empList.getListEmployee().size()) {
            empList.getListEmployee().remove(num - 1);
        } else {
            System.out.println("Index out of bound.");
        }
    }

    // EFFECTS: displays the names of employees
    private void seeEmpName() {
        if (empList.getListEmployee().size() == 0) {
            System.out.println("Employee list is empty.");
        } else {
            for (int i = 1;i < empList.getListEmployee().size() + 1;i++) {
                String name = empList.getListEmployee().get(i - 1).getName();
                System.out.println(i + ": " + name);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays availability information of an employee
    private void seeEmpInfo() {
        if (empList.getListEmployee().size() == 0) {
            System.out.println("Employee list is empty.");
        } else {
            System.out.println("Enter the number to see info:");
            for (int i = 1;i < empList.getListEmployee().size() + 1;i++) {
                String name = empList.getListEmployee().get(i - 1).getName();
                System.out.println(i + ": " + name);
            }
            int num;
            num = input.nextInt();
            if (num - 1 < empList.getListEmployee().size()) {
                showAvailability(empList.getListEmployee().get(num - 1));
            } else {
                System.out.println("Index out of bound.");
            }
        }
    }

    // EFFECTS: displays availability information of an employee
    private void showAvailability(Employee emp) {
        System.out.println(emp.getName());
        for (AvailableDay ava: emp.getAvailability().getListAvailability()) {
            System.out.println(ava.getDayOfWeek() + ": " + ava.getStartTime() + " - " + ava.getEndTime());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new employee to the list
    private void addEmp() {
        System.out.println("Name :");
        Scanner myObj = new Scanner(System.in);
        String answer = myObj.nextLine();
        String name = answer;
        System.out.println("Position (Manager, Supervisor or Service Assistant ):");
        answer = myObj.nextLine();
        String positionName = answer;
        System.out.println("Ideal work hour per week :");
        answer = myObj.nextLine();
        double idealTime = Double. valueOf(answer);
        System.out.println("Employee ID :");
        answer = myObj.nextLine();
        int empID = Integer.valueOf(answer);
        Employee newEmp = new Employee(name,new Position(positionName),idealTime,empID);
        empList.addEmployee(newEmp);
    }

    // MODIFIES: this
    // EFFECTS: generates shifts based on employee availability
    public boolean generateShift(EmployeeList empList) {
        ShiftGenerator generator = new ShiftGenerator();
        Schedule shiftSchedule = new Schedule();
        if (generator.shiftGen(empList, shiftSchedule)) {
            printSchedule(shiftSchedule);
            saveEmpInfoNoLog(empList);
            loadEmpInfoNoLog();
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: prints the schedule of shifts
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

    // EFFECTS: prints information about a single shift
    public static void printShift(Shift shift) {
        String[] daysOfWeek = { "Sun", "Mon", "Tue", "Wen", "Thurs", "Fri", "Sat" };
        String date = daysOfWeek[shift.getDateNum()];

//        System.out.println(date + shift.getStartTime() + " - "
//                + shift.getEndTime() + " " + shift.getEmployee().getName());
    }


        // EFFECTS: saves the workroom to file
    public void saveEmpInfo(EmployeeList empList) {
        try {
            jsonWriter.open();
            jsonWriter.writeLog(empList);
            jsonWriter.close();

        } catch (Exception ex) {
            //
        }

    }

    // EFFECTS: saves the workroom to file
    public void saveEmpInfoNoLog(EmployeeList empList) {
        try {
            jsonWriter.open();
            jsonWriter.write(empList);
            jsonWriter.close();

        } catch (Exception ex) {
            //
        }

    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadEmpInfo() {
        try {
            empList = jsonReader.readLog();
            //System.out.println("Loaded employee information from " + JSON_STORE);
        } catch (Exception e) {
            //System.out.println("Unable to read from file: " + JSON_STORE);//
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadEmpInfoNoLog() {
        try {
            empList = jsonReader.read();

            //System.out.println("Loaded employee information from " + JSON_STORE);
        } catch (Exception e) {
            //System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public EmployeeList getEmpList() {
        return empList;
    }

}
