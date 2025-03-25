package isp.lab10.exercise1;

import java.util.Scanner;


public class Exercise1 {
    // note: works, but be careful when handling console commands; they may bug out because of the threads printing lines in console
    static Aircraft aircraft = null;
    static String ERROR      = "\u001B[31m"; // display color for errors
    static String RESET      = "\u001B[0m";  // resets to normal text color and background

    public static void displayMenu(ATC atc) {
        Scanner scanner = new Scanner(System.in);
        String readEnter = null;

        System.out.println("====================================================");
        System.out.println("Welcome to Air traffic Control System command center.");
        System.out.println("1. Add aircraft");
        System.out.println("2. Take off command");
        System.out.println("3. Land command");
        System.out.println("4. Show list of aircraft");
        System.out.println("5. Exit");

        System.out.println("Enter your command: ");
        String command = scanner.nextLine();
        String ID = null;
        int altitude = 0;
        switch (command) {
            case "1":
                System.out.print("Enter aircraft id: ");
                ID = scanner.nextLine();       // read ID
                aircraft = new Aircraft(ID);   // create object
                atc.addAircraft(ID, aircraft); // add to the list and start thread

                break;
            case "2":
                System.out.print("Send take off command to aircraft id: ");
                ID = scanner.nextLine();       // for aircraft with ID read here
                if (atc.aircrafts.get(ID) != null) {
                    if (!atc.aircrafts.get(ID).statusFlying) {
                        // if the aircraft is on land, send command to take off
                        System.out.println("Altitude to take off at is: ");
                        altitude = scanner.nextInt();
                        atc.aircrafts.get(ID).setAltitude(altitude);
                        atc.sendCommand(ID, new TakeoffCommand(altitude)); // send command and call .notify()
                    } else {
                        // otherwise mention that the land is already flying
                        System.out.println(ERROR + "Aircraft with ID " + ID + " is already flying." + RESET);
                    }
                } else {
                    System.out.println(ERROR + "Aircraft with ID " + ID + " has not been registered or does not exist." + RESET);
                }

                break;
            case "3":
                System.out.print("Send land command to aircraft id: ");
                ID = scanner.nextLine();
                if (atc.aircrafts.get(ID) != null) {
                    if (atc.aircrafts.get(ID).statusFlying) {
                        // if this aircraft is flying, send command to land
                        atc.sendCommand(ID, new LandCommand());
                    } else {
                        // otherwise, mention that the plane has already landed
                        System.out.println(ERROR + "Aircraft with ID " + ID + " is already on land." + RESET);
                    }
                } else {
                    System.out.println(ERROR + "Aircraft with ID " + ID + " has not been registered or does not exist." + RESET);
                }

                break;
            case "4":
                atc.showAircrafts();
                break;
            case "5":
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        ATC atc = new ATC();
        while (true) {
            displayMenu(atc);
        }
    }
}