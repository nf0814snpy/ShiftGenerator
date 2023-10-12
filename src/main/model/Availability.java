package model;

import java.util.*;

public class Availability {
    private List<AvailableDay> availability;

    public Availability() {
        availability = new ArrayList<>();
    }

    public void addDay(AvailableDay availableTime) {
        availability.add(availableTime);
    }

    public List<AvailableDay> getListAvailability() {
        return availability;
    }
}
