package ui;

import model.Employee;
import model.EmployeeList;
import model.Schedule;
import model.ShiftGenerator;
import model.Shift;
import model.Position;
import model.Availability;
import model.AvailableDay;

import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.table.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * The MainMenu class displays the main menu of the application and handles user interactions.
 * Various functionalities are available in the main menu, and each function triggers corresponding
 * actions or processes.
 * This class uses Swing to construct the GUI and processes user input through event listeners.
 * Key features of the application, as well as the display and editing of data, are managed in this class.
 * Responses to user actions such as menu item selection or button clicks are handled by calling appropriate methods
 * through event handlers or action listeners.
 */
@SuppressWarnings("methodlength")
public class MainMenu extends JFrame {

    private JPanel empInfo;
    private JPanel generate;

    private ShiftGen shiftGen = new ShiftGen();
    private JTable table;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private String[] columnNames = new String[8];
    private String[][] dataValues;
    private JRadioButton[] employees;
    private ButtonGroup buttons;
    private ButtonGroup operateButtons;
    private JFrame frame;
    private JButton addEmp;
    private JButton deleteEmp;
    private JButton seeInfo;
    private JButton addAvailability;
    private JButton deleteAvailability;
    private JPanel radioButtonPanel;
    private JPanel radioButtonPanelAva;
    private JPanel avaButtons;
    private JDialog infoDialog;
    private JDialog errorDialog;
    private JPanel errorPanel;
    private Employee employee;
    private JPanel info;
    private String empName;
    private String idealTime;
    private String position;
    private String tempID;
    private JRadioButton[] availabilities;

    public MainMenu() {
        // Create frame instance
        frame = new JFrame();
        JButton button = new JButton("Generate");
        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add ActionListener to the "Generate" button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to generate new data and update the table
                generateNewData();
            }
        });

        // Create panels
        empInfo = new JPanel();
        generate = new JPanel();
        JMenuBar menubar = new JMenuBar();
        JMenu menu1 = new JMenu("File");

        JMenuItem menuitem1 = new JMenuItem("Load Information");
        JMenuItem menuitem2 = new JMenuItem("Save Information");

        radioButtonPanel = new JPanel();


        // MODIFIES: The state of shiftGen and any internal variables related to employee information.
        // EFFECTS: Loads employee information using shiftGen, triggering a reload of the UI.
        menuitem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shiftGen.loadEmpInfo();
                reload();
            }
        });


        // MODIFIES: The state of shiftGen by saving employee information based on shiftGen's employee list.
        // EFFECTS: Initiates the process of saving employee information using shiftGen.
        menuitem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shiftGen.saveEmpInfo(shiftGen.getEmpList());
            }
        });


        menubar.add(menu1);
        menu1.add(menuitem1);
        menu1.add(menuitem2);

        // Add panels to the tabbed pane
        tabbedPane.addTab("Employee Information", empInfo);
        tabbedPane.addTab("Shift Generator", generate);

        //table and button setting
        columnNames = new String[] {"Name","Sun","Mon","Tue","Wed","Thurs","Fri","Sat"};
        table = new JTable();
        dataValues = new String[0][8];
        TableModel  model = new MyTableModel();
        table.setModel(model);
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(200);
        for (int i = 1;i < 8;i++) {
            TableColumn column1 = table.getColumnModel().getColumn(i);
            column1.setPreferredWidth(200);
        }
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 400));

        scrollPane1 = new JScrollPane(button);

        scrollPane1.setSize(90,30);

        scrollPane1.setBounds(75,200,150,30);

        generate.add(scrollPane);

        generate.add(scrollPane1);

        empInfo.setLayout(new BoxLayout(empInfo, BoxLayout.Y_AXIS));
        scrollPane2 = new JScrollPane(radioButtonPanel);
        scrollPane2.setPreferredSize(new Dimension(200, 300)); // Adjust as needed

        addEmp = new JButton("Add Employee");
        deleteEmp = new JButton("Delete Employee");


        // REQUIRES: The employee list must not be empty.
        // MODIFIES: The employee list and, potentially, the state of the frame.
        // EFFECTS: If the employee list is empty, displays an error message using a JOptionPane.
        //          Otherwise, initiates the process to delete the selected employee.
        deleteEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shiftGen.getEmpList().getListEmployee().size() == 0) {
                    JPanel panel = new JPanel();
                    JLabel label = new JLabel(new ImageIcon("./data/error.gif"));
                    panel.add(label);

                    int result = JOptionPane.showOptionDialog(frame, panel, "Delete Error", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
                } else {
                    deleteSelectedEmployee();
                }
            }
        });

        //REQUIRES: The shiftGen object must be initialized and have a valid employee list.
        //MODIFIES: The employee list in the shiftGen object and, potentially, the state of the frame.
        //EFFECTS: Initiates the process of adding a new employee to the system.
        //Displays a dialog with input fields for the employee's name, position, ideal work hours, and employee ID.
        // Upon clicking the OK button, validates the input, creates a new employee, adds it to the employee list,
        //disposes of the dialog, and triggers a reload of the frame to reflect the updated employee list.
        //Displays an error message if the input is invalid (e.g., duplicate employee ID or empty name).
        addEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog inputDialog = new JDialog(frame, "Add Employee", true);
                inputDialog.setLayout(new GridLayout(5, 2)); // Rows, Columns


                JLabel nameLabel = new JLabel("Name:");
                JTextField nameField = new JTextField();
                JLabel positionLabel = new JLabel("Position (Manager, Supervisor, or Service Assistant):");
                String[] positions = {"Manager", "Supervisor", "Service Assistant"};
                JComboBox<String> positionComboBox = new JComboBox<>(positions);
                JLabel idealTimeLabel = new JLabel("Ideal work hour per week:");
                JTextField idealTimeField = new JTextField();
                JLabel empIDLabel = new JLabel("Employee ID:");
                JTextField empIDField = new JTextField();


                inputDialog.add(nameLabel);
                inputDialog.add(nameField);
                inputDialog.add(positionLabel);
                inputDialog.add(positionComboBox);
                inputDialog.add(idealTimeLabel);
                inputDialog.add(idealTimeField);
                inputDialog.add(empIDLabel);
                inputDialog.add(empIDField);

                JButton okButton = new JButton("OK");

                //REQUIRES: The shiftGen object must be initialized and have a valid employee list.
                //MODIFIES: The employee list in the shiftGen object and, potentially, the state of the frame.
                //EFFECTS: Retrieves input values for the new employee from the dialog's fields.
                //           Validates the name and employee ID to ensure they are unique and not empty.
                //           If validation passes, creates a new Employee object with the input values,
                //           adds it to the employee list, disposes of the dialog, and triggers a reload
                //           of the frame
                //           to reflect the updated employee list.
                //           Displays an error message if the input is invalid (e.g., duplicate employee ID or
                //           empty name).
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String positionName = (String) positionComboBox.getSelectedItem();
                        double idealTime = Double.parseDouble(idealTimeField.getText());
                        int empID = Integer.valueOf(empIDField.getText());
                        if (checkName(name,shiftGen.getEmpList())) {
                            if (checkID(empID,shiftGen.getEmpList())) {
                                Employee newEmp = new Employee(name, new Position(positionName), idealTime, empID);

                                shiftGen.getEmpList().addEmployee(newEmp);

                                inputDialog.dispose();
                                reload();
                            } else {
                                JPanel panel = new JPanel();
                                JLabel label = new JLabel(new ImageIcon("./data/error2.gif"));
                                panel.add(label);

                                int result = JOptionPane.showOptionDialog(frame, panel, "Delete Error",
                                        JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
                            }

                        }  else {
                            JPanel panel = new JPanel();
                            JLabel label = new JLabel(new ImageIcon("./data/error3.gif"));
                            panel.add(label);

                            int result = JOptionPane.showOptionDialog(frame, panel, "Delete Error",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
                        }
                    }


                });

                inputDialog.add(okButton);

                inputDialog.setSize(700, 200);
                inputDialog.setLocationRelativeTo(frame);
                inputDialog.setVisible(true);
            }

        });


        seeInfo = new JButton("See Employee Info");

        seeInfo.addActionListener(new ActionListener() {

            //REQUIRES: The shiftGen object must be initialized, and there should be at least one employee
            // in the employee list.
            //            The buttons variable should be a valid ButtonGroup.
            // MODIFIES: The state of the infoDialog, info, empName, idealTime, position, tempID, and
            //             availabilities variables.
            //EFFECTS: Displays detailed information about the selected employee, including their name, ideal work time,
            //           position, employee ID, and availabilities.
            //           Allows the user to add or delete availabilities for the selected employee through a dialog.
            //           Handles error cases where there are no employees or no employee is selected.
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shiftGen.getEmpList().getListEmployee().size() == 0 || buttons.getSelection() == null) {
                    JPanel panel = new JPanel();
                    JLabel label = new JLabel(new ImageIcon("./data/error.gif"));
                    panel.add(label);

                    int result = JOptionPane.showOptionDialog(frame, panel, "Delete Error", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
                } else {
                    infoDialog = new JDialog(frame, "Employee Information", true);
                    info = new JPanel();
                    empName = "";
                    idealTime = "";
                    position = "";
                    AtomicInteger id = new AtomicInteger(-1);

                    Availability ava = new Availability();
                    ava = positionAssign(id, ava);
                    info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

                    tempID = String.valueOf(id);
                    JLabel name = new JLabel("Name: " + empName);
                    info.add(Box.createRigidArea(new Dimension(0, 10)));
                    JLabel idealWork = new JLabel("Ideal Work Time: " + idealTime);
                    info.add(Box.createRigidArea(new Dimension(0, 10)));
                    JLabel pos = new JLabel("Position: " + position);
                    info.add(Box.createRigidArea(new Dimension(0, 10)));
                    JLabel empID = new JLabel("ID: " + tempID);
                    info.add(Box.createRigidArea(new Dimension(0, 10)));
                    JLabel availability = new JLabel("Availabilities:");
                    info.add(name);
                    info.add(pos);
                    info.add(idealWork);
                    info.add(empID);
                    info.add(availability);
                    infoDialog.add(info, BorderLayout.NORTH);

                    availabilities  = new JRadioButton[ava.getListAvailability().size()];
                    List<AvailableDay> avail = employee.getAvailability().getListAvailability();
                    avail.sort(Comparator.comparingInt(av -> av.getNumOfDate()));
                    ButtonGroup buttonGroup = new ButtonGroup();
                    for (int i = 0; i < ava.getListAvailability().size(); i++) {
                        String line = numToDate(ava.getListAvailability().get(i).getNumOfDate());
                        line += ": ";
                        line += String.valueOf(ava.getListAvailability().get(i).getStartTime());
                        line += " - ";
                        line += String.valueOf(ava.getListAvailability().get(i).getEndTime());
                        availabilities[i] = new JRadioButton(line);
                        buttonGroup.add(availabilities[i]);
                        info.add(availabilities[i]);
                    }
                    avaButtons = new JPanel();
                    avaButtons.setLayout(new BoxLayout(avaButtons, BoxLayout.Y_AXIS));
                    addAvailability = new JButton("Add Availability");
                    deleteAvailability = new JButton("Delete Availability");

                    addAvailability.addActionListener(new ActionListener() {

                        //REQUIRES: The frame variable should reference the main application frame.
                        //MODIFIES: The state of the inputDialog, dateComboBox, startTimeField, endTimeField, empl
                        // oyee, and availabilities variables.
                        //EFFECTS: Displays a dialog allowing the user to input availability details for a new
                        //          day of the week.
                        //           Removes any existing availability for the selected day of the week.
                        //           Adds the new availability to the employee's availability list and updates
                        //             the availabilities display.
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JDialog inputDialog = new JDialog(frame, "Add Availability", true);
                            inputDialog.setLayout(new GridLayout(5, 2)); // Rows, Columns
                            JLabel dateLabel = new JLabel("Day of Week: ");
                            String[] dates = {"Sunday", "Monday", "Tuesday", "Wednesday",
                                    "Thursday","Friday","Saturday"};
                            JComboBox<String> dateComboBox = new JComboBox<>(dates);
                            JLabel startTimeLabel = new JLabel("Start Time(e.g. 8.0 or 13.0):");
                            JTextField startTimeField = new JTextField();
                            JLabel endTimeLabel = new JLabel("End Time(e.g. 22.0):");
                            JTextField endTimeField = new JTextField();

                            inputDialog.add(dateLabel);
                            inputDialog.add(dateComboBox);
                            inputDialog.add(startTimeLabel);
                            inputDialog.add(startTimeField);
                            inputDialog.add(endTimeLabel);
                            inputDialog.add(endTimeField);

                            JButton okButton = new JButton("OK");
                            inputDialog.add(okButton);
                            okButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String dayOfweek = (String) dateComboBox.getSelectedItem();
                                    double startTime = Double.parseDouble(startTimeField.getText());
                                    double endTime = Double.parseDouble(endTimeField.getText());
                                    int index;
                                    for (int i = 0;i < employee.getAvailability().getListAvailability().size();i++) {
                                        if (employee.getAvailability().getListAvailability().get(i).getDayOfWeek()
                                                .equals(dayOfweek)) {
                                            employee.getAvailability().getListAvailability().remove(i);
                                        }
                                    }
                                    AvailableDay newAvail = new AvailableDay(dayOfweek,startTime,endTime);
                                    employee.getAvailability().getListAvailability().add(newAvail);
                                    availabilities = reloadAva(employee, availabilities, empName, position, tempID);

                                }


                            });
                            inputDialog.setSize(700, 200);
                            inputDialog.setLocationRelativeTo(frame);
                            inputDialog.setVisible(true);
                        }
                    });


                    deleteAvailability.addActionListener(new ActionListener() {

                        // REQUIRES: The deleteAvail() method should be defined to handle
                        // the deletion of selected availability.
                        //MODIFIES: The state of the employee and availabilities variables.
                        //EFFECTS: Calls the deleteAvail() method to handle the deletion of selected availability.
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            deleteAvail();

                        }
                    });
                    avaButtons.add(addAvailability);
                    avaButtons.add(deleteAvailability);
                    info.add(avaButtons, BorderLayout.SOUTH);
                    infoDialog.setSize(400, 450);
                    infoDialog.setLocationRelativeTo(frame);
                    infoDialog.setVisible(true);
                }
            }
        });
        empInfo.add(scrollPane2);
        empInfo.add(scrollPane2, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addEmp);
        buttonPanel.add(deleteEmp);
        buttonPanel.add(seeInfo);
        empInfo.add(buttonPanel, BorderLayout.SOUTH);

        // Add tabbed pane to the frame
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        frame.setJMenuBar(menubar);
        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setTitle("Shift Generator");
        frame.setVisible(true);
    }


    //REQUIRES: Atomic integer id to be provided to update the employee ID.
    //MODIFIES: The state of empName, idealTime, position, id, and ava variables.
    //EFFECTS: Updates the employee details and availability based on the selected employee's information.
    private Availability positionAssign(AtomicInteger id, Availability ava) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].isSelected()) {
                String selectedEmployeeName = employees[i].getText();

                Iterator<Employee> iterator = shiftGen.getEmpList().getListEmployee().iterator();
                while (iterator.hasNext()) {
                    employee = iterator.next();
                    if (employee.getName().equals(selectedEmployeeName)) {
                        empName = employee.getName();
                        idealTime = String.valueOf(employee.getIdealWorkTime());
                        if (employee.getPositionID() == 0) {
                            position = "Manager";
                        } else if (employee.getPositionID() == 1) {
                            position = "Supervisor";
                        } else if (employee.getPositionID() == 2) {
                            position = "Service Assistant";
                        } else {
                            position = "Invalid";
                        }
                        id.set(employee.getID());
                        ava = employee.getAvailability();
                        break;
                    }

                }
            }
        }
        return ava;
    }

    //MODIFIES: Updates the employee's availability by removing the selected day.
    // EFFECTS: Calls the checkDate method to determine which availability to delete.
    private void deleteAvail() {
        String selectedEmployeeName = "";
        for (int i = 0; i < employee.getAvailability().getListAvailability().size(); i++) {
            if (availabilities[i].isSelected()) {
                selectedEmployeeName = availabilities[i].getText();
            }
        }
        String strFirstThree = selectedEmployeeName.substring(0, 3);
        Iterator<AvailableDay> iterator = employee.getAvailability().getListAvailability().iterator();
        checkDate(availabilities, strFirstThree, iterator);
    }

    //REQUIRES: availabilities array, a day abbreviation (strFirstThree),
    // and an iterator for the employee's availability.
    //MODIFIES: Updates the employee's availability by removing the days that match the selected day.
    //EFFECTS: Calls the avaMatchesDay method to determine which days to remove from the availability.
    private void checkDate(JRadioButton[] availabilities, String strFirstThree, Iterator<AvailableDay> iterator) {
        List<AvailableDay> daysToRemove = new ArrayList<>();

        while (iterator.hasNext()) {
            AvailableDay ava = iterator.next();
            if (avaMatchesDay(ava, strFirstThree)) {
                daysToRemove.add(ava);
            }
        }


        employee.getAvailability().getListAvailability().removeAll(daysToRemove);


        this.availabilities = reloadAva(employee, availabilities, empName, position, tempID);
    }

    // REQUIRES: availabilities array, a day abbreviation (strFirstThree),
    // and an iterator for the employee's availability.
    // MODIFIES: Updates the employee's availability by removing the days that match the selected day.
    // EFFECTS: Calls the avaMatchesDay method to determine which days to remove from the availability.
    private boolean avaMatchesDay(AvailableDay ava, String dayAbbreviation) {
        int dayIndex = getDayIndex(dayAbbreviation);
        return ava.getNumOfDate() == dayIndex;
    }

    // REQUIRES: a valid day abbreviation (e.g., "Sun", "Mon").
    // EFFECTS: Returns the corresponding index (0 for "Sun", 1 for "Mon", ..., 6 for "Sat").
    private int getDayIndex(String dayAbbreviation) {
        switch (dayAbbreviation) {
            case "Sun":
                return 0;
            case "Mon":
                return 1;
            case "Tue":
                return 2;
            case "Wed":
                return 3;
            case "Thu":
                return 4;
            case "Fri":
                return 5;
            case "Sat":
                return 6;
            default:
                return -1;
        }
    }

    // REQUIRES: a valid name.
    // EFFECTS: Returns the Employee object with the matching name, or null if not found.
    private Employee getEmp(String name) {
        List<Employee> list = shiftGen.getEmpList().getListEmployee();
        for (Employee emp: list) {
            if (emp.getName().equals(name)) {
                return emp;
            }
        }
        return null;
    }

    // REQUIRES: a valid number representing a day (0 to 6).
    // EFFECTS: Returns the day of the week as a String.
    private String numToDate(int num) {
        if (num == 0) {
            return "Sunday";
        } else if (num == 1) {
            return "Monday";
        } else if (num == 2) {
            return "Tuesday";
        } else if (num == 3) {
            return "Wednesday";
        } else if (num == 4) {
            return "Thursday";
        } else if (num == 5) {
            return "Friday";
        } else {
            return "Saturday";
        }
    }

    // REQUIRES: a non-null name and a valid employee list.
    // EFFECTS: Returns true if the name is not found in the list; otherwise, returns false.
    private boolean checkName(String name,EmployeeList list) {
        List<Employee> temp = list.getListEmployee();
        boolean result = true;
        for (Employee emp: temp) {
            if (emp.getName().equals(name)) {
                result = false;
            }
        }
        return result;
    }

    // REQUIRES: a valid ID and a valid employee list.
    // EFFECTS: Returns true if the ID is not found in the list; otherwise, returns false.
    private boolean checkID(int id,EmployeeList list) {
        List<Employee> temp = list.getListEmployee();
        boolean result = true;

        int intID = id;
        for (Employee emp: temp) {
            if (emp.getID() == intID) {
                result = false;
            }
        }
        return result;
    }

    //EFFECTS: Updates the employee list, radio buttons, and related GUI components.
    private void reload() {
        List<Employee> emplist = shiftGen.getEmpList().getListEmployee();
        int sizeOfList = emplist.size();
        employees = new JRadioButton[sizeOfList];
        buttons = new ButtonGroup();

        radioButtonPanel.setLayout(new GridLayout(sizeOfList, 1));
        radioButtonPanel.removeAll();
        for (int i = 0; i < sizeOfList; i++) {
            employees[i] = new JRadioButton(emplist.get(i).getName());
            buttons.add(employees[i]);
            radioButtonPanel.add(employees[i]);
        }


        scrollPane2 = new JScrollPane(radioButtonPanel);
        scrollPane2.setPreferredSize(new Dimension(200, 300)); // Adjust as needed
        empInfo.removeAll();
        empInfo.add(scrollPane2, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addEmp);
        buttonPanel.add(deleteEmp);
        buttonPanel.add(seeInfo);
        empInfo.add(buttonPanel, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();

    }

    //EFFECTS: Updates the list, radio buttons, and related GUI components.
    private JRadioButton[] reloadAva(Employee emp,JRadioButton[] availabilities,
                                     String empName,String position, String tempID) {
        List<AvailableDay> ava = emp.getAvailability().getListAvailability();
        int sizeOfList = ava.size();
        JPanel avaButtonsPanel = new JPanel();
        avaButtonsPanel.setLayout(new BoxLayout(avaButtonsPanel, BoxLayout.Y_AXIS));
        JRadioButton[] newAvailabilities = new JRadioButton[sizeOfList];
        ButtonGroup buttonGroup = new ButtonGroup();

        ava.sort(Comparator.comparingInt(av -> av.getNumOfDate()));

        for (int i = 0; i < sizeOfList; i++) {
            String line = numToDate(ava.get(i).getNumOfDate());
            line += ": ";
            line += String.valueOf(ava.get(i).getStartTime());
            line += " - ";
            line += String.valueOf(ava.get(i).getEndTime());
            newAvailabilities[i] = new JRadioButton(line);
            buttonGroup.add(newAvailabilities[i]);
            avaButtonsPanel.add(newAvailabilities[i]);
        }


        info.removeAll();


        JLabel name = new JLabel("Name: " + empName);
        info.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel pos = new JLabel("Position: " + position);
        info.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel empID = new JLabel("ID: " + tempID);
        info.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel availability = new JLabel("Availabilities:");
        info.add(name);
        info.add(pos);
        info.add(empID);
        info.add(availability);


        info.add(avaButtonsPanel);


        info.add(avaButtons, BorderLayout.SOUTH);

        infoDialog.revalidate();
        infoDialog.repaint();
        return newAvailabilities;
    }

    //EFFECTS: call MainMenu constructor to start app
    public static void main(String[] args) {

        new MainMenu();
    }

    //REQUIRE:name != null && list != null
    //EFFECTS: true if the name is not found in the list; otherwise, returns false.
    public void generateNewData() {

        if (shiftGen.generateShift(shiftGen.getEmpList())) {
            List<Employee> empList = shiftGen.getEmpList().getListEmployee();
            dataValues = new String[empList.size()][8];

            for (int i = 0; i < empList.size(); i++) {
                dataValues[i][0] = empList.get(i).getName();
            }

            ShiftGenerator generator = new ShiftGenerator();
            Schedule shiftSchedule = new Schedule();
            generator.shiftGen(shiftGen.getEmpList(), shiftSchedule);

            for (Shift shift: shiftSchedule.getShifts()) {
                int index = findRow(dataValues, shift.getEmployee().getName());
                String result = String.valueOf((int)shift.getStartTime()) + ":00 - "
                        + String.valueOf((int)shift.getEndTime()) + ":00";
                dataValues[index][shift.getDateNum() + 1] = result;
            }


            MyTableModel newModel = new MyTableModel();
            table.setModel(newModel);
            TableColumn column = table.getColumnModel().getColumn(0);
            column.setPreferredWidth(200);
            for (int i = 1;i < 8;i++) {
                TableColumn column1 = table.getColumnModel().getColumn(i);
                column1.setPreferredWidth(150);
            }
            shiftGen.saveEmpInfo(shiftGen.getEmpList());
            shiftGen.loadEmpInfo();
            System.out.println("Auto Save and Loaded");
        } else {
            JOptionPane.showMessageDialog(MainMenu.this,
                    "Not enough employees to generate shifts!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    //EFFECTS: find a row which matches the name
    public int findRow(String[][] data, String name) {
        int sizeOfRow = data.length;
        for (int i = 0; i < sizeOfRow; i++) {
            if (data[i][0].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    //MODIFIES: employees
    //EFFECTS: delete employee from the list
    private void deleteSelectedEmployee() {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].isSelected()) {
                String selectedEmployeeName = employees[i].getText();

                Iterator<Employee> iterator = shiftGen.getEmpList().getListEmployee().iterator();
                while (iterator.hasNext()) {
                    Employee emp = iterator.next();
                    if (emp.getName().equals(selectedEmployeeName)) {
                        iterator.remove();
                    }
                }

                reload();
                return;
            }
        }
        JOptionPane.showMessageDialog(MainMenu.this,
                "Please select an employee to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    //EFFECTS: construct table for generator
    public class MyTableModel extends DefaultTableModel {

        MyTableModel() {

            super(dataValues, columnNames);

        }


    }

}
