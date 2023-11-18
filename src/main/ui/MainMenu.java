package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    private JPanel empInfo;
    private JPanel generate;

    public MainMenu() {
        // Create frame instance
        JFrame frame = new JFrame();

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels
        empInfo = new JPanel();
        generate = new JPanel();

        // Add panels to the tabbed pane
        tabbedPane.addTab("Employee Information", empInfo);
        tabbedPane.addTab("Shift Generator", generate);

        // Add tabbed pane to the frame
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 1200, 800);
        frame.setTitle("Shift Generator");
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of MainMenu
        new MainMenu();
    }
}