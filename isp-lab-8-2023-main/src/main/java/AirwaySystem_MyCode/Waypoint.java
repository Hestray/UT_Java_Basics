package AirwaySystem_MyCode;

import lombok.*;
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class Waypoint {
    private String name;
    private double latitude;
    private double longitude;
    private int altitude;

    @Override public String toString() {
        return "WAYPOINT " +
                "NAME: " + this.name + " ### " +
                "COORDINATES: [" + this.altitude + " / " +
                this.latitude + ", " + this.longitude + "];";
    }
}
