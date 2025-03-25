package isp.lab5.exercise4Advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Organizer extends UserLogin {
    private ArrayList<Client> clientList = new ArrayList<>();
    private String eventOrganized;
    private final FileHandlingSystem FILE = new FileHandlingSystem();

    public Organizer(String username, String password, String eventOrganized) {
        super(username, password, TicketStatus.ORGANIZER);
        this.eventOrganized = eventOrganized;

        // add info to files
        // FILE FORMAT: name, surname, username, eventOrganized
        // only adds the username and eventOrganized
        FILE.addOrganizer("organizer data.txt", super.getUsername(), this.eventOrganized);
        this.clientList = FILE.clientListUpdate(this.eventOrganized);
    }
    public Organizer(String name, String surname, String username, String password, String eventOrganized) {
        super(name, surname, username, password, TicketStatus.ORGANIZER);
        this.eventOrganized = eventOrganized;

        FILE.addOrganizer("organizers data.txt", super.getName(), super.getSurname(), super.getUsername(), this.eventOrganized);
        FILE.addUserCredentials("users info.txt", super.getName(), super.getSurname(), super.getUsername(), super.getPassword());

        // update clientList
        this.clientList = FILE.clientListUpdate(this.eventOrganized);
    }

    @Override
    public void buyTicket(int numOfTickets, Event event) {
        System.out.println("You don't have to buy a ticket. You are automatically allowed at the event.");
    }

    @Override public boolean login(String username, String password) {
        // access file with users & password
        // FILE FORMAT: name, surname, username, password
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

        // otherwise create a quick account automatically
        new Organizer(username, password, quickAccount());
        System.out.println("Account created.");
        // name, surname can be completed after

        return false;
    }

    public String quickAccount() {
        System.out.println("Account needs to be created. What event are you organizing? ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @Override public String viewTicket() {
        return "Organizer access. No ticket required.";
    }

    public void checkIn(int ID) {
        // method is meant to be used within a loop
        if (!this.clientList.isEmpty() && ID < this.clientList.size()) {
            // ticketCode from ID in clientList
            long ticketCode = clientList.get(ID).getTicket().getTicketCode();
            // ticketCode(s) from the event in the current existing list of events
            // the event in question is the one the organizer is managing
            Event event = super.getTicketManager().selectEvent(this.eventOrganized);
            long existingCode;
            for (int i = 0; i < event.getTickets().length; i++) {
                existingCode = event.getTickets()[i].getTicketCode();
                if (Long.valueOf(ticketCode).equals(existingCode)) {
                    // change status of user to ATTENDED
                    this.clientList.get(ID).setAttendanceStatus(ClientStatus.ATTENDED);
                    i = event.getTickets().length; // to end the for earlier
                }
            }
        }
    }

    public void scanTicket(int ID) {
        // method is meant to be used within a loop
        if (!this.clientList.isEmpty() && ID < this.clientList.size()) {
            long ticketCode = clientList.get(ID).getTicket().getTicketCode();
            Event event = super.getTicketManager().selectEvent(this.eventOrganized);

            long existingCode;
            for (int i = 0; i < event.getTickets().length; i++) {
                existingCode = event.getTickets()[i].getTicketCode();
                if (Long.valueOf(ticketCode).equals(existingCode)) {
                    // change status of user to ATTENDED
                    this.clientList.get(ID).getTicket().setTicketStatus(TicketStatus.SCANNED);
                    i = event.getTickets().length; // to end for loop earlier
                }
            }
        }
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public FileHandlingSystem getFILE() {
        return FILE;
    }

    public String getEventOrganized() {
        return eventOrganized;
    }

    public void setEventOrganized(String eventOrganized) {
        this.eventOrganized = eventOrganized;
    }
}
