package AirwaySystem_MyCode;

import java.io.File;
import java.util.Scanner;

public class UserInterface {
    private static AirwaySystem airwaySystem = new AirwaySystem();
    private static final Scanner scanner = new Scanner(System.in);
    private String choice;

    public void menu() {
        double  latitude;
        double  longitude;
        int     altitude;
        boolean ok = false;
        System.out.println("Welcome to Airways To Hell. Please choose: ");
        System.out.println("1. Add a new route");
        System.out.println("2. Add waypoints to the route");
        System.out.println("3. Show all waypoints of a given route");
        System.out.println("4. Calculate total distance of a given route");
        System.out.println("5. Access default routes");
        System.out.println("6. Delete a route");
        System.out.println("0. Exit");
        choice = scanner.nextLine();
        switch(choice) {
            case "0" -> {
                System.out.println("Thank you for using this program.");
                System.exit(0);
            }
            case "1" -> {
                System.out.println("Route name: ");
                choice = scanner.nextLine();
                if (airwaySystem.addRoute(choice)) {
                    System.out.print("Route was added successfully.");
                    // create folder
                    SerializableJSON.createFolder(choice);
                    System.out.println("Folder leading to the route was also created successfully.");
                }
            }
            case "2" -> {
                System.out.println("Route to add waypoints to: ");
                choice = scanner.nextLine();
                for (Route r : AirwaySystem.getRoutes()) {
                    if (r.getName().equals(choice)) {
                        // only if the route exist, add waypoints
                        ok = true;
                        System.out.println("Type quit when to stop adding waypoints.");
                        while (true) {
                            System.out.println("New waypoint\nWaypoint name: ");
                            choice = scanner.next();
                            if (choice.equalsIgnoreCase("quit"))
                                break; // break from while
                            System.out.println("Latitude: ");
                            latitude = scanner.nextDouble();
                            System.out.println("Longitude: ");
                            longitude = scanner.nextDouble();
                            System.out.println("Altitude: ");
                            altitude = scanner.nextInt();

                            // create object and add it to the list of waypoints
                            if (r.addWaypoint(new Waypoint(choice, latitude, longitude, altitude))) {
                                // create file within route folder
                                System.out.println("Added waypoint.");
                                SerializableJSON.toJSONFile(r.getName(), r.getWaypoints().get(r.getWaypoints().size()-1));
                                System.out.println("Created file leading to waypoint.");
                            } else {
                                System.out.println("Make sure that the waypoint is unique.");
                            }
                        }
                        break; // break from for
                    }
                }
                if (!ok)
                    System.out.println("Seems like there is no route with that name.");
            }
            case "3" -> {
                System.out.println("Route name: ");
                choice = scanner.nextLine();
                for (Route r : AirwaySystem.getRoutes()) {
                    if (r.getName().equals(choice)) {
                        ok = true;
                        System.out.println("ROUTE: " + choice);
                        System.out.println("WAYPOINTS: ");
                        for (String waypoint : SerializableJSON.ls(choice)) {
                            waypoint = waypoint.replace(".json", "");
                            System.out.println(waypoint);
                        }
                        break;
                    }
                }
                if (!ok)
                    System.out.println("Seems like there is no route with that name.");
            }
            case "4" -> {
                System.out.println("Route name: ");
                choice = scanner.nextLine();
                for (Route r : AirwaySystem.getRoutes()) {
                    if (r.getName().equals(choice)) {
                        // only if route exist, calculate the distance
                        ok = true;
                        System.out.println("Distance: " + airwaySystem.calculateDistancePerRoute(choice));
                        break;
                    }
                }
                if (!ok)
                    System.out.println("Route may not exist.");
            }
            case "5" -> {
                System.out.println("Please make a choice: ");
                System.out.println("1. Display all available routes");
                System.out.println("2. Display routes and their waypoints");
                System.out.println("3. Display routes and their total distances");
                System.out.println("0. Go back");
                choice = scanner.nextLine();
                switch(choice) {
                    case "0" -> this.menu();
                    case "1" -> {
                        System.out.println("ROUTES: ");
                        for (String route : SerializableJSON.ls("")) {
                            System.out.println(route);
                        }
                    }
                    case "2" -> {
                        for (String route : SerializableJSON.ls("")) {
                            System.out.println("ROUTE: " + route);
                            System.out.println("WAYPOINTS: ");
                            for (String waypoint : SerializableJSON.ls(route)) {
                                waypoint = waypoint.replace(".json", "");
                                System.out.println(waypoint);
                            }
                        }
                    }
                    case "3" -> {
                        for (String route : SerializableJSON.ls("")) {
                            System.out.println("ROUTE NAME: " + route);
                            System.out.println("DISTANCE: " + airwaySystem.calculateDistancePerRoute(route));
                        }
                    }
                }
            }
            case "6" -> {
                System.out.println("Route name: ");
                choice = scanner.nextLine();
                for (String route : SerializableJSON.ls("")) {
                    if (route.equals(choice)) {
                        // delete folder only if it exists
                        ok = true;
                        File file = new File(".\\flights\\" + route);
                        SerializableJSON.deleteFolder(file);

                        // remove from airwaySystem too
                        airwaySystem.removeRoute(route);

                        break; // break for
                    }
                }
                if (!ok) System.out.println("Folder with route does not exist.");
            }
            default -> {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
