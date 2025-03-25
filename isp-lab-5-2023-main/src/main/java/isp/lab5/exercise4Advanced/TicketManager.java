package isp.lab5.exercise4Advanced;

import java.util.ArrayList;

public class TicketManager implements PaymentGateway {
    private Ticket ticket = new Ticket();
    private ArrayList<Event> events;
    private FileHandlingSystem FILE = new FileHandlingSystem();

    public TicketManager() {
        // reads event list from a file and assign it to current TicketManager
        this.events = FILE.updateEventList("event list.txt");
    }

    public int sizeOfNumber(int number) {
        String num = (Integer.valueOf(number)).toString();
        return num.length();
    }

    public Ticket generateTicket(Event event) {
//        Random randomNumber = new Random();
//        int firstTwoDigits = event.getID(); // first 2 digits will be event ID
//        long code;
//
//        // code generation
//        firstTwoDigits = randomNumber.nextInt(89) + 10; // number between 10 and 99
//        code = firstTwoDigits * (long)(Math.pow(10, sizeOfNumber(events[current].getTicketsAvailable())));
//        code += this.events[current].getTicketsAvailable();
//
//        // set code and number of
//        this.events[current]
//        this.ticket.setTicketCode(code);
//        event.setTicketsAvailable(this.event.getTicketsAvailable()-1); // one more ticket has been generated and bought
//
        return this.ticket;
    }

    public TicketStatus validateTicket(Ticket ticket) {
        if (!this.events.isEmpty()) {
            int ID = (int)ticket.getTicketCode() / 1000;
            if (ID < this.events.size())
                for (int i = 0; i < this.events.size(); i++)
                    if (Long.valueOf(ticket.getTicketCode()).equals(this.events.get(ID).getTickets()[i]))
                        return TicketStatus.VALID;
        }
        return TicketStatus.INVALID;
    }

    public Event selectEvent(String eventName) {
        if (!this.events.isEmpty())
            for (int i = 0; i < this.events.size(); i++)
                if (this.events.get(i).getEventName().compareTo(eventName) == 0)
                    return this.events.get(i);
        return null;
    }

    public String toString(String eventName) {
        Event event = selectEvent(eventName);
        if (event != null)
            return "Event Name: " + event.getEventName() + "\twill take place on " + event.getDate() + "\t\tAvailable number of tickets: " + event.getTicketsAvailable();
        return "There are no events with this name";    // if no such event is found
    }

    public void buyTicket(int numOfTickets, Event event) {
        // todo: implementation
        // access a file with all the tickets created (sold)
        // lower number of available ticket for the given event
        event.setTicketsAvailable(event.getTicketsAvailable() - numOfTickets);

        //
    }

    public Ticket getTicket() {
        return ticket;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
