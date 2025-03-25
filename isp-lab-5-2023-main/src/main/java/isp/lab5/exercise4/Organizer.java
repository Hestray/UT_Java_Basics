package isp.lab5.exercise4;

import java.util.ArrayList;

public class Organizer extends User {
    private ArrayList<Client> clientList;

    public Organizer(String username, String password, ArrayList<Client> clientList) {
        super(username, password);
        this.clientList = clientList;
    }

    public Organizer(String username, String password, int totalNumberOfTickets, ArrayList<Client> clientList) {
        super(username, password, totalNumberOfTickets);
        this.clientList = clientList;
    }

    public Organizer(String username, String password, TicketManager ticketManager, ArrayList<Client> clientList) {
        super(username, password, ticketManager);
        this.clientList = clientList;
    }

    @Override
    public boolean acquireTicket(int numTickets, double ticketPrice) {
        System.out.println("You do not have to buy the ticket.");
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        if (this.getUsername().compareTo(username) != 0 || this.getPassword().compareTo(password) != 0) return false;
        return true;
    }

    @Override
    public String viewTicket() {
        return "You have an ORGANIZER VIP ticket.";
    }

    /**
     * This method will change Ticket Status for the tickets of the clients if the tickets are VALID
     * The change is from VALID to SCANNED
     * @return true if and only if there is at least one client with at least one valid ticket. Otherwise, returns false
     */
    public boolean scanTicket() {
        if (this.clientList.isEmpty()) return false;
        boolean scanned = false; // checks for at least one client with at least one valid ticket

        for (int i = 0; i < this.clientList.size(); i++) {
            // for every user
            if (this.clientList.get(i).getNumOfTickets() == 0) return false;

            for (int j = 0; j < this.clientList.get(i).getNumOfTickets(); j++) {
                // for every ticket that the user has
                if (this.clientList.get(i).getTicket().get(j).getTicketStatus() == TicketStatus.VALID) {
                    this.clientList.get(i).getTicket().get(j).setTicketStatus(TicketStatus.SCANNED);
                    scanned = true;
                }
            }
        }

        return scanned;
    }

    /**
     * This method checks if the clients' tickets are scanned and changed their attendance to the event.
     * The change is from NOT_ATTENDED to ATTENDED
     * @return true if and only if all the tickets of at least one client are scanned
     */
    public boolean checkIn() {
        if (this.clientList.isEmpty()) return false;
        boolean attendance = false; // checks for at least one client with all tickets scanned

        for (int i = 0; i < this.clientList.size(); i++) {
            // for every user
            if (this.clientList.get(i).getNumOfTickets() == 0) return false;

            int ok = 0; // to check SCANNED tickets
            for (int j = 0; j < this.clientList.get(i).getNumOfTickets(); j++) {
                // count how many SCANNED tickets the client has
                if (this.clientList.get(i).getTicket().get(j).getTicketStatus() == TicketStatus.SCANNED)
                    ok++;
                if (this.clientList.get(i).getNumOfTickets() == ok) {
                    this.clientList.get(i).setClientStatus(ClientStatus.ATTENDED);
                    attendance = true;
                }
            }
        }

        return attendance;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public void setClientList(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }
}
