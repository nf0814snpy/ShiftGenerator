package model;

/*
   It contains available information per day.
   It has dayOfWeek and startTime and endTime of availability for that day
 */
public class AvailableDay {
    private String dayOfWeek;
    private double startTime;
    private double endTime;
    private int numOfDay;


    //REQUIRES: startTime >=8.0 && startTime <= 22.0 && endTime >=8.0 && endTime <=22.0 && startTime<endTime
    //EFFECTS: assign each component and assign numOfDay corresponding to day of week
    public AvailableDay(String dayOfWeek,double startTime, double endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        convertDayStringToNum(dayOfWeek);
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    /*
     * REQUIRES: date should be "Sunday", "Monday", "Tuesday"... or "Saturday"
     * MODIFIES: this
     * EFFECTS: assign numOfDay for corresponds to date
     */
    public void convertDayStringToNum(String date) {
        if (date == "Sunday") {
            numOfDay = 0;
        } else if (date == "Monday") {
            numOfDay = 1;
        } else if (date == "Tuesday") {
            numOfDay = 2;
        } else if (date == "Wednesday") {
            numOfDay = 3;
        } else if (date == "Thursday") {
            numOfDay = 4;
        } else if (date == "Friday") {
            numOfDay = 5;
        } else {
            numOfDay = 6;
        }
    }

    public int getNumOfDate() {
        return numOfDay;
    }
}
