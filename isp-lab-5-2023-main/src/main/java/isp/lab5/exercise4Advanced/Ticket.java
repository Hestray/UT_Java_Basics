package isp.lab5.exercise4Advanced;

public class Ticket {
    // todo: future implementation: change ticketCode to be a QR code
    private long ticketCode;
    private TicketStatus ticketStatus;
    public Ticket() {
        this.ticketCode = 0;
        this.ticketStatus = TicketStatus.INVALID;
    }
    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public long getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(long ticketCode) {
        this.ticketCode = ticketCode;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
