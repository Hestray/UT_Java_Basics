package isp.lab5.exercise4;

import java.util.ArrayList;

public abstract class User implements PaymentGateway {
    private String username;
    private String password;
    private ArrayList<Ticket> ticket = new ArrayList<>(); // either this or the one from TM is redundant if I adjust the code some more
    private TicketManager ticketManager;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.ticketManager = new TicketManager();
    }

    public User(String username, String password, int totalNumberOfTickets) {
        this.username = username;
        this.password = password;
        this.ticketManager = new TicketManager(totalNumberOfTickets);
    }

    public User(String username, String password, TicketManager ticketManager) {
        this.username = username;
        this.password = password;
        this.ticketManager = ticketManager;
    }

    public abstract boolean login(String username, String password);
    public abstract String viewTicket();

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Ticket> getTicket() {
        return ticket;
    }

    public TicketManager getTicketManager() {
        return ticketManager;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTicket(ArrayList<Ticket> ticket) {
        this.ticket = ticket;
    }

    public void setTicketManager(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }
}
