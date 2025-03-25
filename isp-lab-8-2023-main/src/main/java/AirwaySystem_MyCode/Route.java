package AirwaySystem_MyCode;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter // public
@Getter // public
public class Route {
    private List<Waypoint> waypoints = new ArrayList<>(); // all the waypoints that define this route
    private String name;    // unique for each map

    public Route(String name) {
        this.name = name;
    }

    /**
     * This method checks if the given waypoint is unique. If it is, it is added to the list.
     * @param waypoint to be checked and added to the list of waypoints associated with the current route
     * @return true if the waypoint is unique and was added to the list
     */
    public boolean addWaypoint(Waypoint waypoint) {
        for (Waypoint w : waypoints) {
            if (w.getName().equals(waypoint.getName())) {
                return false;
            }
        }
        waypoints.add(waypoint);
        return true;
    }

    /**
     * This method calculates the distance between 2 given waypoints. The waypoints MUST be one after another in the waypoints list.
     * @param W1 is the first waypoint
     * @param W2 is the second waypoint
     * @return the distance in kilometers
     */
    public double calculateDistanceW2W(Waypoint W1, Waypoint W2) {
        double distance = 0;
        // computation
        int earthRadius = 6371; // Radius of the Earth in kilometers
        double dLat = Math.toRadians(W1.getLatitude() - W2.getLatitude());
        double dLon = Math.toRadians(W1.getLongitude() - W2.getLongitude());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(W1.getLatitude())) * Math.cos(Math.toRadians(W2.getLatitude())) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        // calculate the distance
        distance = earthRadius * c;
        return distance;
    }
}
