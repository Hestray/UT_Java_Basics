package isp.lab7.safehome;

public class Door {
    private DoorStatus status;
    public Door() {
        // whenever a new door is created, it's always closed
        this.status = DoorStatus.CLOSED;
    }
    public void lockDoor() {
        status = DoorStatus.CLOSED;
    }
    public void unlockDoor() {
        status = DoorStatus.OPEN;
    }

    public DoorStatus getStatus() {
        return status;
    }
}
