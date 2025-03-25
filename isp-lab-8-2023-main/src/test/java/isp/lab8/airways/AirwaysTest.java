package isp.lab8.airways;

import AirwaySystem_MyCode.AirwaySystem;
import AirwaySystem_MyCode.Route;
import AirwaySystem_MyCode.SerializableJSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AirwaysTest {
    AirwaySystem airwaySystem;
    @Before public void initializeAirwaySystem() {
        airwaySystem = new AirwaySystem();
    }

    @Test public void testLoadingAirwaySystem() {
        try {
            SerializableJSON.loadRoutesAndWaypoints();
        } catch(IOException e) {
            System.out.println("Could not load routes and waypoints.");
        }
        Assert.assertNotNull(AirwaySystem.getRoutes());
    }

    @Test public void testCreateFolder() {
        Assert.assertTrue(SerializableJSON.createFolder("temp"));
    }

    @Test public void testDeleteFromAirwaySystem() {
        Assert.assertTrue(airwaySystem.removeRoute("temp"));
    }

    @Test public void testDeleteFolder() {
        Assert.assertTrue(SerializableJSON.deleteFolder(new File(".\\flights\\temp")));
    }

    @Test public void testAccessingWaypointsOfARoute() {
        List<String> waypoints = new ArrayList<>();
        List<String> manualWaypoints = Arrays.asList("BIRGU", "LRCL", "LROP", "SOPAV", "TAS----[ ] OD");
        for (Route r : AirwaySystem.getRoutes()) {
            if (r.getName().equals("LRCL-LROP")) {
                for (String waypoint : SerializableJSON.ls("LRCL-LROP")) {
                    waypoint = waypoint.replace(".json", "");
                    waypoints.add(waypoint);
                }
                break;
            }
        }
        Assert.assertArrayEquals(waypoints.toArray(), manualWaypoints.toArray());
    }
}
