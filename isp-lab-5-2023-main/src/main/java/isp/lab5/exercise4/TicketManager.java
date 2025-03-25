package isp.lab5.exercise4;

import java.util.ArrayList;
import java.util.Random;

public class TicketManager implements PaymentGateway {
    private ArrayList<Ticket> ticketList = new ArrayList<>();
    private static int totalNumberOfTickets;

    public TicketManager() { }
    public TicketManager(int totalNumberOfTickets) {
        TicketManager.totalNumberOfTickets = totalNumberOfTickets;
    }

    @Override
    public boolean acquireTicket(int numTickets, double ticketPrice) {
        if (numTickets <= 0) return false;

        // the current available number of tickets is total - how_many_were_created
        int availableRN = totalNumberOfTickets - this.ticketList.size();
        if (numTickets > availableRN) {
            System.err.println("There are only " + availableRN + " tickets left. Order denied.");
            return false;
        }

        for (int i = 0; i < numTickets; i++) {
            // add ticket to list
            ticketList.add(generateTicket());
            if (ticketList.getLast() == null) return false;

            // set values
            ticketList.getLast().setTicketStatus(TicketStatus.VALID);

            // lower the number of available tickets
            ticketList.getLast().setAvailableTickets(availableRN - 1);
            availableRN--;
        }

        return true;
    }

    public Ticket generateTicket() {
        Ticket ticket = new Ticket();

        // generate code
        long ticketCode;
        Random num = new Random();
        int firstTwoDigits = num.nextInt(89) + 10; // num between 10 and 99, including those
        ticketCode = firstTwoDigits * 1000 + ticket.getAvailableTickets();

        // set values
        ticket.setTicketCode(ticketCode);

        return ticket;
    }

    @Override public String toString() {
        return "This event has a total of " + totalNumberOfTickets + " number of tickets.";
    }

    public void setTotalNumberOfTickets(int totalNumberOfTickets) {
        TicketManager.totalNumberOfTickets = totalNumberOfTickets;
    }

    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }
}