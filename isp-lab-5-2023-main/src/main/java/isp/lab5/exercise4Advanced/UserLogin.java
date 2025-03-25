package isp.lab5.exercise4Advanced;

public abstract class UserLogin implements PaymentGateway {
    private String name;
    private String surname;
    private String username;
    private String password;
    private Ticket ticket = new Ticket();
    private TicketManager ticketManager = new TicketManager();
    public UserLogin(String name, String surname, String username, String password, TicketStatus ticketStatus) {
        // the uniqueness of the username will be tested prior to this constructor
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.ticket.setTicketStatus(ticketStatus);
        // ticket status will be left INVALID until specified otherwise (either bought or organizer)
    }
    public UserLogin(String username, String password, TicketStatus ticketStatus) {
        this.username = username;
        this.password = password;
        this.ticket.setTicketStatus(ticketStatus);
        // ticket status will be left INVALID until specified otherwise (either bought or organizer)
    }

    public UserLogin(String name, String surname, String username, TicketStatus ticketStatus) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.ticket.setTicketStatus(ticketStatus);
    }

    abstract public boolean login(String username, String password);
    abstract public String viewTicket();

    public Ticket getTicket() {
        return ticket;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public TicketManager getTicketManager() {
        return ticketManager;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicketManager(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }
}
