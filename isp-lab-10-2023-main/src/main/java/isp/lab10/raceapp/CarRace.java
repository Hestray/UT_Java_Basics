package isp.lab10.raceapp;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CarRace {
    private ArrayList<Car> cars = new ArrayList<>();
    private static JFrame frame = new JFrame("Car Race");
    public CarRace(Leaderboard leaderboard) {
        this.window(leaderboard);
    }
    public void window(Leaderboard leaderboard) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CarPanel carPanel = new CarPanel();

        frame.getContentPane().add(carPanel);
        frame.pack();
        frame.setSize(500,300);

        Car car1 = new Car("Red car",    carPanel, leaderboard);
        Car car2 = new Car("Blue car",   carPanel, leaderboard);
        Car car3 = new Car("Green car",  carPanel, leaderboard);
        Car car4 = new Car("Yellow car", carPanel, leaderboard);
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
    }

    public void start() {
        frame.setVisible(true);

        // initiate the race
        cars.get(0).start();
        cars.get(1).start();
        cars.get(2).start();
        cars.get(3).start();
    }

    public void join() {
        try {
            cars.get(0).join();
            cars.get(1).join();
            cars.get(2).join();
            cars.get(3).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Car extends Thread {
    private String name;
    private int distance = 0;
    private CarPanel carPanel;
    private Leaderboard leaderboard = new Leaderboard();

    public Car(String name, CarPanel carPanel, Leaderboard leaderboard) {
        //set thread name;
        setName(name);
        this.name        = name;
        this.carPanel    = carPanel;
        this.leaderboard = leaderboard;
    }

    public void run() {
        while (distance < 400) {
            // simulate the car moving at a random speed
            int speed = (int) (Math.random() * 10) + 1;
            distance += speed;

            carPanel.updateCarPosition(name, distance);

            try {
                // pause for a moment to simulate the passage of time
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.leaderboard.addResults(this.name);
        carPanel.carFinished(name);
    }
}

class CarPanel extends JPanel {
    private int[] carPositions;
    private String[] carNames;
    private Color[] carColors;

    public CarPanel() {
        carPositions = new int[4];
        carNames     = new String[]{"Red car", "Blue car", "Green car", "Yellow car"};
        carColors    = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < 4; i++) {
            int yPos = 50 + i * 50; // Vertical position of the car
            int xPos = carPositions[i]; // Horizontal position of the car
            int carSize = 30; // Size of the car

            g.setColor(carColors[i]);
            g.fillOval(xPos, yPos, carSize, carSize);
            g.setColor(Color.BLACK);
            g.drawString(carNames[i], xPos, yPos - 5);
        }
    }

    public void updateCarPosition(String carName, int distance) {
        int carIndex = getCarIndex(carName);
        if (carIndex != -1) {
            carPositions[carIndex] = distance;
            repaint();
        }
    }

    public void carFinished(String carName) {
        System.out.println(carName + " finished race.");
    }

    private int getCarIndex(String carName) {
        for (int i = 0; i < 4; i++) {
            if (carNames[i].equals(carName)) {
                return i;
            }
        }
        return -1;
    }
}
