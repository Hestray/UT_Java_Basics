package isp.lab5.exercise4Advanced;

import java.time.LocalDateTime;

public class Event {
    private String eventName;
    private LocalDateTime date;
    private int ticketTotalNo;
    private int ticketsAvailable;
    private double ticketPrice;
    private Ticket[] tickets;

    public Event(String eventName, LocalDateTime date, int ticketTotalNo, double ticketPrice) {
        this.eventName = eventName;
        this.date = date;
        this.ticketTotalNo = ticketTotalNo;
        tickets = new Ticket[ticketTotalNo];    // keeps track of all allocated tickets
        this.ticketsAvailable = ticketTotalNo;  // at first, all tickets are available
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getEventName() {
        return eventName;
    }

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getTicketTotalNo() {
        return ticketTotalNo;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    public void setTicketTotalNo(int ticketTotalNo) {
        this.ticketTotalNo = ticketTotalNo;
    }
}
