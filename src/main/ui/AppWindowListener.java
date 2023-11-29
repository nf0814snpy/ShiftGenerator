package ui;

import model.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppWindowListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {

        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            System.out.println(event.getDescription());
        }

        System.exit(0);
    }
}