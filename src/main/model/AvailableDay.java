package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/*
   It contains available information per day.
   It has dayOfWeek and startTime and endTime of availability for that day
 */
public class AvailableDay implements Writable {
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
        if (date.equals("Sunday")) {
            numOfDay = 0;
        } else if (date.equals("Monday")) {
            numOfDay = 1;
        } else if (date.equals("Tuesday")) {
            numOfDay = 2;
        } else if (date.equals("Wednesday")) {
            numOfDay = 3;
        } else if (date.equals("Thursday")) {
            numOfDay = 4;
        } else if (date.equals("Friday")) {
            numOfDay = 5;
        } else {
            numOfDay = 6;
        }
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getNumOfDate() {
        return numOfDay;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dayOfWeek", dayOfWeek);
        json.put("startTime", startTime);
        json.put("endTime", endTime);
        return json;
    }

}
