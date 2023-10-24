package model;

/*
    Class for passing integer as a reference
 */
class IntegerWrapper {
    private int value;

    // EFFECTS: constructs an IntegerWrapper with the given initial value
    public IntegerWrapper(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // MODIFIES: this
    // EFFECTS: sets the value of this IntegerWrapper to the given value
    public void setValue(int value) {
        this.value = value;
    }
}
