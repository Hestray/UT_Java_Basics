package isp.lab5.exercise4;

public class Ticket {
    private long ticketCode;
    private static double ticketPrice;
    private TicketStatus ticketStatus;
    private static int availableTickets;

    public Ticket() {
        ticketCode = 0;
        ticketStatus = TicketStatus.INVALID;
    }

    public Ticket(long ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Ticket(long ticketCode, double ticketPrice) {
        this.ticketCode = ticketCode;
        Ticket.ticketPrice = ticketPrice;
        this.ticketStatus = TicketStatus.INVALID;
    }

    public Ticket(long ticketCode, double ticketPrice, int availableTickets) {
        this.ticketCode = ticketCode;
        Ticket.ticketPrice = ticketPrice;
        Ticket.availableTickets = availableTickets;
        this.ticketStatus = TicketStatus.INVALID;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public long getTicketCode() {
        return ticketCode;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setTicketPrice(double ticketPrice) {
        Ticket.ticketPrice = ticketPrice;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public void setTicketCode(long ticketCode) {
        this.ticketCode = ticketCode;
    }

    public void setAvailableTickets(int availableTickets) {
        Ticket.availableTickets = availableTickets;
    }
}
