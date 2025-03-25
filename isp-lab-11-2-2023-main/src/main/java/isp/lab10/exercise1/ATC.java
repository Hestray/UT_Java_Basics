package isp.lab10.exercise1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ATC {

    Map<String, Aircraft> aircrafts = new HashMap<>(); // <ID, aircraft>
    public void addAircraft(String id, Aircraft aircraft){
        System.out.println("Aircraft with ID. " + id + " added");
        aircrafts.put(id, aircraft);   // add to the map
        aircraft.start();              // start thread
    }
    public void sendCommand(String id, AtcCommand command){
        System.out.println("Aircraft with id " + id + " received command " + command);
        // send command to receiveATCCommand()
        aircrafts.get(id).receiveATCCommand(command);
    }

    public void showAircrafts(){
        if (aircrafts.isEmpty())
            System.out.println("There are no aircrafts registered.");
        else {
            System.out.println("Here is a list of currently registered aircrafts: ");
            for (Map.Entry<String, Aircraft> aircraft : aircrafts.entrySet()) {
                System.out.println("Aircraft\tID: " + aircraft.getKey());
            }
        }
    }
}

class AtcCommand {
    String command;
    String message; // to help with toString and to modify it based on command type
    public AtcCommand(String command) {
        this.command = command;
        this.message = "AtcCommand {command='" + command + "'}";
    }

    @Override
    public String toString() {
        return message;
    }
}

class TakeoffCommand extends AtcCommand {
    private int altitude;
    public TakeoffCommand(int altitude) {
        super("TAKEOFF_CMD");
        this.altitude = altitude;
        message = message + " {altitude='" + (this.altitude * 1000) + "'}";
    }
    public int getAltitude() {
        return altitude;
    }
}

class LandCommand extends AtcCommand {
    public LandCommand() {
        super("LAND_CMD");
    }
}
