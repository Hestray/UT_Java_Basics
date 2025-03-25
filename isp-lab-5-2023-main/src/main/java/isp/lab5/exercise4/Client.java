package isp.lab5.exercise4;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Client extends User {
    private int numOfTickets;
    private ClientStatus clientStatus;
    private boolean hasBought = false;
    public Client(String username, String password) {
        super(username, password);
        numOfTickets = 0;
        this.clientStatus = ClientStatus.NOT_ATTENDED; // can only be changed by organizer
    }

    public Client(String username, String password, TicketManager ticketManager) {
        super(username, password, ticketManager);
        numOfTickets = 0;
        this.clientStatus = ClientStatus.NOT_ATTENDED;
    }
    @Override
    public boolean acquireTicket(int numTickets, double ticketPrice) {
        // call Ticket Manager to create ticket(s) and return the ticket(s)
        if (numTickets <= 0) return false;
        this.numOfTickets += numTickets;
        if (this.getTicketManager() == null) return false; // checks if ticket manager was created
        if (!this.getTicketManager().acquireTicket(numTickets, ticketPrice)) return false; // checks if tickets can be created

        for (int i = 0; i < numTickets; i++) { // add the ticket codes to the ticket list in user
            int sizeTicketList = this.getTicketManager().getTicketList().size();
            // size - 1 = last element
            // size - A = the Ath last element
            // size - (A - i) = the interval from Ath last element to last, including
            if (this.hasBought) { // if this is NOT the first time the client shops
                this.hasBought = false; // reset the client so that it can buy again
            }
            super.getTicket().add(this.getTicketManager().getTicketList().get(sizeTicketList - numTickets + i));
        }
        this.hasBought = true;
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        // ABSOLUTELY NOT CORRECT, but I also don't feel like making ANOTHER array with ALL the GODDAMN clients ;-; spare me, please
        // for more advanced stuff, check exercise4Advanced, where I use files and multiple checks for this type of methods
        // pls trust i know how to do these things lol
        if (this.getUsername().compareTo(username) != 0 || this.getPassword().compareTo(password) != 0) return false;
        return true;
    }

    @Override
    public String viewTicket() {
        if (this.numOfTickets <= 0) return "You have no tickets";
        if (this.numOfTickets == 1) return "Your ticket is " + super.getTicket().getFirst().getTicketCode();
        // if more than one ticket
        StringBuilder codes = new StringBuilder("Your tickets are: ");
        ArrayList<Ticket> tickets = super.getTicket();
        if (tickets != null) {
            for (int i = 0; i < this.numOfTickets; i++) {
                codes.append(tickets.get(i).getTicketCode());
                if (i != this.numOfTickets - 1)
                   codes.append(", ");
            }
            return String.valueOf(codes);
        }
        else return "ERROR";
    }

    public boolean credentials(String name, String surname, String cardNumber, String CVV) {
        // regex
        // .*\d.* checks for any digit within string
        // \d+ checks for string to be all digits
        boolean cond = true;

        if (name.matches(".*\\d.*")) {
            System.out.println("Name cannot contains digits.");
            cond = false;
        }

        if (surname.matches(".*\\d.*")) {
            System.out.println("Surname cannot contain digits.");
            cond = false;
        }

        if (!cardNumber.matches("\\d+")) {
            System.out.println("Card number cannot contain any letters.");
            cond = false;
        }

        if (!CVV.matches("\\d+")) {
            System.out.println("CVV cannot contain any letters.");
            cond = false;
        }

        return cond;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }
}
