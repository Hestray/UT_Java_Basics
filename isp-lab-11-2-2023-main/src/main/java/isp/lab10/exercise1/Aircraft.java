package isp.lab10.exercise1;

import java.util.Random;

public class Aircraft implements Runnable {
    // for composition, Aircraft would have lock
    // but since lock has to be a common object, then we have aggregation
    // we need lock to be a common object for both aircraft and outside
    // note: COLORS FOR DISPLAY OF THREAD TEXT:
    public String RESET    = "\u001B[0m";  // resets to normal text color and background
    public String FLYING   = "\u001B[36m"; // while flying
    public String ON_LAND  = "\u001B[33m"; // while on land
    public String STATUS   = "\u001B[32m"; // to display various messages

    // note: ATTRIBUTES OF CLASS AIRCRAFT:
    final Object    lock   = "plane";
    private String  id;
    private int     altitude;
    public boolean  statusFlying;   // false if on land, true if in the air
    public long     seconds;        // time (seconds) spent cruising
    public Aircraft(String id) {
        // this.lock = new Object();     // composition
        // this.lock = lock;             // aggregation
        this.id         = id;
        statusFlying    = false;
        System.out.println(STATUS + "Aircraft ID. " + this.id + " is ready to take off." + RESET);
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * This method allows an aircraft to take off or land by sending a synchronized notify to a waiting thread.
     * The method should never reach an invalid command, as the confirmation is taken care of by the menu,
     * but it is still here in order to prevent any form of error.
     * @param cmd to send to the aircraft.
     */
    public void receiveATCCommand(AtcCommand cmd) {
        // before notifying, process the command
        if (!statusFlying) {
            // only if the aircraft is on land can it take off; otherwise, this is invalid
            if (cmd.command.compareTo("TAKEOFF_CMD") == 0) {
                statusFlying = true;  // change status so it's considered flying
                synchronized (lock) { // synchronize only if it's a valid command
                    lock.notify();
                }
            } else {
                System.out.println("Invalid take off. Plane is already flying");
            }
        }
        else {
            // but if plane IS already flying
            if (cmd.command.compareTo("LAND_CMD") == 0) {
                statusFlying = false; // change status so it's considered on land
                synchronized (lock) { // synchronize only if it's a valid command
                    lock.notify();
                }
            } else {
                System.out.println("Invalid landing. Plane is already on land.");
            }
        }
    }

    /**
     * This (thread) method implements the stages a plane must go through in order to finalize a route.
     */
    public void run() {
        System.out.println(ON_LAND + "Aircraft " + id + " on stand" + RESET);
        synchronized (lock) {
            if (!statusFlying) {
                try {
                    lock.wait();
                } catch(InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(ON_LAND + "Aircraft " + id + " taxing" + RESET);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(ON_LAND + "Aircraft " + id + " taking off" + RESET);
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(FLYING + "Aircraft " + id + " ascending" + RESET);
        try {
            Thread.sleep(10 * 1000 * altitude);
            // Thread.sleep(1000 * altitude); // to help with checking & testing
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(FLYING + "Aircraft " + id + " cruising" + RESET);
        long startMillis = System.currentTimeMillis(); // current time in millis
        synchronized (lock) {
            if (statusFlying) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        seconds = (System.currentTimeMillis() - startMillis) / 1000;

        System.out.println(FLYING + "Aircraft " + id + " Descending" + RESET);
        try {
            Thread.sleep(10 * 1000 * altitude);
           // Thread.sleep(1000 * altitude); // to help with checking & testing
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        altitude = 0;     // set altitude to 0, as it is on land
        System.out.println(ON_LAND + "Aircraft " + id + " Landed" + RESET);
        System.out.println(STATUS + "Aircraft with ID " + id + " spent " + seconds + " seconds cruising." + RESET);
    }

    public String getId() {
        return id;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }
}
