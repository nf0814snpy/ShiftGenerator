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
    public void addDay(AvailableDay availableTime) {
        availability.add(availableTime);
    }

    public List<AvailableDay> getListAvailability() {
        return availability;
    }
}
