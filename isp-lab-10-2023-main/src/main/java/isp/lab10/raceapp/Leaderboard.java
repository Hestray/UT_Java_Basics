package isp.lab10.raceapp;

import javax.swing.*;
import java.awt.image.Kernel;
import java.util.ArrayList;

public class Leaderboard {
    private JFrame frame = new JFrame("Leaderboard");
    private ArrayList<String> results = new ArrayList<>();
    public Leaderboard() {
        this.window();
    }
    public void addResults(String car) {
        this.results.add(car);
        // display
        JLabel label = new JLabel((results.size()) + "    " + car);
        label.setBounds(50, (results.size()-1) * 50, 500, 50);
        this.frame.add(label);
        // repaint the frame
        this.frame.revalidate();
        this.frame.repaint();
    }
    public void window() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
    }

    public void display() {
        this.frame.setVisible(true);
    }

    public void raceStopwatch(long time) {
        JLabel timer = new JLabel("RACE LASTED FOR: " + time/100.0 + " SECONDS.");
        timer.setBounds(50, (results.size()) * 50, 500, 50);
        this.frame.add(timer);
        this.frame.revalidate();
        this.frame.repaint();
        JLabel empty = new JLabel("");
        empty.setBounds(50, (results.size()+1) * 50, 500, 50);
        this.frame.add(empty);
        this.frame.revalidate();
        this.frame.repaint();
    }
}
