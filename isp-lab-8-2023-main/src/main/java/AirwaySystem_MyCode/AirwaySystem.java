package AirwaySystem_MyCode;

import java.util.ArrayList;
import java.util.List;

public class AirwaySystem {
    private static List<Route> routes = new ArrayList<>();

    /**
     * Checks if the name of the route is unique. If it is, route is created.
     * @param name of the route
     * @return true if the name is unique and route was created
     */
    public boolean addRoute(String name) {
        if (checkNameUniqueness(name) == null) {
            routes.add(new Route(name));
            return true;
        }
        return false;
    }

    /**
     * Checks if the route exists. If it does, it removes it from the list of routes from here.
     * @param name of the route
     * @return true if the route was removed, false otherwise
     */
    public boolean removeRoute(String name) {
        Route r = checkNameUniqueness(name);
        if (r != null) {
            routes.remove(r);
            return true;
        }
        return false;
    }

    private Route checkNameUniqueness(String name) {
        for (Route r : routes) {
            if (r.getName().equals(name)) return r;
        }
        return null;
    }

    /**
     * This method sums the distance between each 2 sequential waypoints.
     * @param routeName is the name that identifies the route
     * @return the total distance per given route
     */
    public double calculateDistancePerRoute(String routeName) {
        for (Route r : routes) { // check for route
            if (r.getName().equals(routeName)) { // if route was found
                if (r.getWaypoints().size() >= 2) { // check that it has at least 2 waypoints
                    double distance = 0;
                    for (int i = 0; i < r.getWaypoints().size() - 1; i++) {
                        // pairs of waypoints: 0:1, 1:2, ..., n-2:n-1 for n waypoints
                        // go through each waypoint, calculate the distance
                        distance += r.calculateDistanceW2W(r.getWaypoints().get(i), r.getWaypoints().get(i+1));
                    }
                    return distance;
                }
            }
        }
        return 0; // route doesn't exist or start == finish
    }

    public static List<Route> getRoutes() {
        return routes;
    }
}
