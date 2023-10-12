package model;

public class Position {


    private final String name;
    private int positionID;

    public Position(String name) {
        this.name = name;
        assignPositionID();
    }

    //Modifies: this
    //Effects: Assign positionID depends on position
    public void assignPositionID() {
        if (name.equals("Manager")) {
            positionID = 0;
        } else if (name.equals("Supervisor")) {
            positionID = 1;
        } else if (name.equals("Service Assistant")) {
            positionID = 2;
        } else {
            positionID = 3; //which is supposed to be an error
        }
    }

    public String getPositionName() {
        return name;
    }

    public int getPositionID() {
        return positionID;
    }
}
