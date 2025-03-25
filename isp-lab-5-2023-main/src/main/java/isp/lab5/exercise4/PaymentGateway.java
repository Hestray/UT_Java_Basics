package isp.lab5.exercise4;

public interface PaymentGateway {
    public boolean acquireTicket(int numTickets, double ticketPrice);
}
