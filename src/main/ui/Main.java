package ui;

import persistence.*;

import java.util.*;



public class Main {

    private static final String JSON_STORE = "./data/EmployeeInfo.json";
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static void main(String[] args) {
        new ShiftGen();
    }

}
