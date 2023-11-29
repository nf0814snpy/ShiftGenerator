package model;

import java.util.*;

/*
    Availability has a list of AvailableDay.
    It contains availableDay for whole week.
 */
public class Availability {
    private List<AvailableDay> availability;

    /*
     * MODIFIES: this
     */
    public Availability() {
        availability = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: add new availableDay object to a list
     */
    public void addDay(AvailableDay availableTime, Employee employee) {
        availability.add(availableTime);

        EventLog eventLog = EventLog.getInstance();
        String logMessage = "AvailableDay added for employee " + employee.getName()
                + " " + availableTime.getDayOfWeek() + " " + availableTime.getStartTime()
                + " - " + availableTime.getEndTime();
                ;
        Event logEvent = new Event(logMessage);
        eventLog.logEvent(logEvent);
    }

    public void removeAvaLog(AvailableDay availableTime,Employee employee) {
        EventLog eventLog = EventLog.getInstance();
        String logMessage = "Availability removed: " + employee.getName()
                + " " + availableTime.getDayOfWeek() + " " + availableTime.getStartTime()
                + " - " + availableTime.getEndTime();
        ;
        Event logEvent = new Event(logMessage);
        eventLog.logEvent(logEvent);
    }

    public void addDayForLoad(AvailableDay availableTime, Employee employee) {
        availability.add(availableTime);
    }

    public List<AvailableDay> getListAvailability() {
        return availability;
    }
}
