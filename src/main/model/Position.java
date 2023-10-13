package model;


/* Represent positions for each Employee have. This contains position name and this class assign position ID for each
    positions
*/

public class Position {


    private final String name;
    private int positionID;

    /*
     * EFFECTS: it stores name value into String name and assign position ID according to the name
     */
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
