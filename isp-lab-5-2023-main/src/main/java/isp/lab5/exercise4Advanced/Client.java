package isp.lab5.exercise4Advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends UserLogin {
    private int ID;
    private boolean hasTicket; // normally this should be T/F for EACH event available
    private ClientStatus attendanceStatus;

    public Client(String name, String surname, String username) {
        super(name, surname, username, TicketStatus.INVALID);
    }
    public Client(String name, String surname, String username, String password) {
        super(name, surname, username, password, TicketStatus.INVALID);
        this.attendanceStatus = ClientStatus.NOT_ATTENDED;
    }

    @Override public void buyTicket(int numOfTickets, Event event) {
        // todo: implement
        this.hasTicket = true;
    }
    @Override public boolean login(String username, String password) {
        // access file with users & password
        String filename = "users info.txt";
        File userInfo = new File(filename);
        ArrayList<String> data = new ArrayList<>();
        Scanner reader = null;

        // handle errors
        try {
            reader = new Scanner(userInfo);
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File could not be read.");
            System.exit(0);
        }

        // look through users for the given username and password
        while(reader.hasNextLine()) {
            // (username must be unique)
            String line = reader.nextLine();
            if(line.contains(username) && line.contains(password))
                return true;
        }
        // otherwise fail connecting
        return false;
    }
    @Override public String viewTicket() {
        if (hasTicket)
            return "Ticket code: " + super.getTicket().getTicketCode() + "\tstatus: " + super.getTicket().getTicketStatus();
        else
            return "No ticket purchased.";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ClientStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(ClientStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
