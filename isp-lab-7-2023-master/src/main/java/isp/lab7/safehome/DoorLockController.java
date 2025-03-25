package isp.lab7.safehome;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoorLockController implements ControllerInterface {
    private Map<Tenant, AccessKey> validAccess; // tenant is key, accesskey is value
    protected List<AccessLog> accessLogs;
    private Door door;
    private int attempts;

    public DoorLockController(Map<Tenant, AccessKey> validAccess) {
        this.validAccess = validAccess;
        this.door        = new Door();
        this.attempts    = 0;
        this.accessLogs  = new ArrayList<>();
    }
    public DoorStatus enterPin(String pin) throws InvalidPinException, TooManyAttemptsException {
        if (pin.equals(MASTER_KEY)) {
            // situation if MASTER_KEY is used
            System.out.println("MASTER KEY was input. Attempts reset.");
            this.attempts = 0;
            if (door.getStatus() == DoorStatus.CLOSED) door.unlockDoor();
            // add this attempt to the log
            accessLogs.add(new AccessLog(
                    MASTER_TENANT_NAME,
                    LocalDateTime.now(),
                    "Unlocked door using MASTER_KEY",
                    DoorStatus.OPEN,
                    null));
            return DoorStatus.OPEN;    // door is still closed when the master key is input, it just resets the values
        } else {
            // if it's a tenant attempting to input a random pin
            String tenantName = "";
            if (++this.attempts < 3) {
                // if there are still attempts, check to see if pin is right
                for (Map.Entry<Tenant, AccessKey> attempt : validAccess.entrySet()) {
                    tenantName = attempt.getKey().getName();
                    if (attempt.getValue().getPin().equals(pin)) {
                        // pin right, open/close the door, reset attempts number
                        if (door.getStatus() == DoorStatus.OPEN) {
                            // close the door
                            door.lockDoor();
                            System.out.println("Door is now locked.");
                            // record to access log
                            accessLogs.add(new AccessLog(
                                    tenantName,
                                    LocalDateTime.now(),
                                    "Locked door by " + tenantName,
                                    DoorStatus.CLOSED,
                                    null));
                        } else {
                            // open the door
                            door.unlockDoor();
                            System.out.println("Door is now unlocked");
                            // record to access log
                            accessLogs.add(new AccessLog(
                                    tenantName,
                                    LocalDateTime.now(),
                                    "Unlocked door by " + tenantName,
                                    DoorStatus.OPEN,
                                    null));
                        }
                        this.attempts = 0;
                        return door.getStatus(); // should be OPEN if unlocked; CLOSED otherwise
                    }
                }
                // incorrect pin or tenant doesn't even exist
                // record to access log
                accessLogs.add(new AccessLog(
                        tenantName,
                        LocalDateTime.now(),
                        "PIN attempt by " + tenantName,
                        DoorStatus.CLOSED,
                        "Invalid PIN."));
                throw new InvalidPinException();
            }
            // record to access log
            door.lockDoor(); // set its status to CLOSED
            accessLogs.add(new AccessLog(
                    tenantName,
                    LocalDateTime.now(),
                    "PIN attempt",
                    DoorStatus.CLOSED,
                    "Too many incorrect attempts."));
            throw new TooManyAttemptsException();
        }
    }


    public void addTenant(String pin, String tenantName) throws TenantAlreadyExistsException {
        for (Tenant t : validAccess.keySet()) {
            if (t.getName().equals(tenantName)) {
                throw new TenantAlreadyExistsException();
            }
        }
        // add the tenant
        validAccess.put(new Tenant(tenantName), new AccessKey(pin));
    }

    public void removeTenant(String name) throws TenantNotFoundException {
        for (Map.Entry<Tenant, AccessKey> tenant : validAccess.entrySet()) {
            if (tenant.getKey().getName().compareTo(name) == 0) {
                validAccess.remove(tenant.getKey(), tenant.getValue());
                return;
            }
        }
        // tenant not found
        throw new TenantNotFoundException();
    }

    public List<AccessLog> getAccessLogs() {
        return accessLogs;
    }

    public Door getDoor() {
        return door;
    }
}
