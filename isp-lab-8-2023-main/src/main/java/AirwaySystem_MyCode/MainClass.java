package AirwaySystem_MyCode;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) {
        // load the existing routes & waypoints to airwaySystem
        try {
            SerializableJSON.loadRoutesAndWaypoints();
        } catch (IOException e) {
            System.out.println("Couldn't load routes and waypoints.");
        }

        // menu
        UserInterface userInterface = new UserInterface();
        while (true) {
            userInterface.menu();
        }
    }
}
