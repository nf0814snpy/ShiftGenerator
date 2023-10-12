package model;

public class Employee {
    private String name;
    private Position position;
    private Availability availability;
    private double idealWorkTime;
    private double totalWorkTime;

    public Employee(String name,Position position,double ideal) {
        this.name = name;
        this.position = position;
        availability = new Availability();
        this.idealWorkTime = ideal;
        this.totalWorkTime = 0;
    }

    public String getName() {
        return name;
    }

    public double idealWorkTime() {
        return idealWorkTime;
    }

    public int getPositionID() {
        return position.getPositionID();
    }

    public void addWorkTime(double time) {
        totalWorkTime += time;
    }

    public double getTotalWorkTime() {
        return totalWorkTime;
    }


    public boolean isWorkEnough(double workTime) {
        return idealWorkTime - totalWorkTime == 0 || idealWorkTime - totalWorkTime < workTime;
    }

    public Availability getAvailability() {
        return availability;
    }
}
