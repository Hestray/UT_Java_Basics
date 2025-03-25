package isp.lab5.exercise4;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserDerivedTest {
    @Test
    public void ClientTest() {
        // Client side
        Ticket t = new Ticket();
        t.setAvailableTickets(45);
        t.setTicketPrice(120);
        TicketManager tm = new TicketManager(t.getAvailableTickets());
        Client client = new Client("JohnSmith", "12345pass", tm);

        Assert.assertEquals("You have no tickets", client.viewTicket());
        Assert.assertFalse(client.login("john smith", "12345pass"));
        Assert.assertTrue(client.login("JohnSmith", "12345pass"));
        Assert.assertTrue(client.credentials("John", "Smith", "12345678", "999"));
        Assert.assertFalse(client.credentials("john100", "smith2", "1234", "abc"));
        Assert.assertTrue(client.acquireTicket(3, t.getTicketPrice()));
        Assert.assertTrue(client.viewTicket().contains("Your tickets are:"));
    }

    @Test public void OrganizerTest() {
        // Organizer side
        Ticket t = new Ticket();
        t.setAvailableTickets(45);
        t.setTicketPrice(120);
        TicketManager tm = new TicketManager(t.getAvailableTickets());
        Client client = new Client("JohnSmith", "12345pass", tm);
        Client c2 = new Client("yickes", "abcd", new TicketManager());

        client.acquireTicket(3, t.getTicketPrice());
        c2.acquireTicket(1, t.getTicketPrice());

        ArrayList<Client> clientList = new ArrayList<>();
        clientList.add(client); clientList.add(c2);
        Organizer organizer = new Organizer("MasterOG", "101100", clientList);

        Assert.assertTrue(organizer.scanTicket());
        Assert.assertTrue(organizer.checkIn());

        // have one client (c2) with all tickets scanned
        client.acquireTicket(3, t.getTicketPrice());
        c2.acquireTicket(1, t.getTicketPrice());
        client.getTicket().getLast().setTicketStatus(TicketStatus.INVALID);
        Assert.assertTrue(organizer.scanTicket());
        Assert.assertTrue(organizer.checkIn());

        // have no client with all tickets scanned
        client.acquireTicket(3, t.getTicketPrice());
        c2.acquireTicket(1, t.getTicketPrice());
        client.getTicket().getLast().setTicketStatus(TicketStatus.INVALID);
        c2.getTicket().getFirst().setTicketStatus(TicketStatus.INVALID);
        Assert.assertTrue(organizer.scanTicket());
        Assert.assertFalse(organizer.checkIn());
    }
}
