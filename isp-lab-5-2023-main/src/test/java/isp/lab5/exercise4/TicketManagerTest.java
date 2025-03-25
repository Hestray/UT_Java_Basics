package isp.lab5.exercise4;

import org.junit.Assert;
import org.junit.Test;

public class TicketManagerTest {
    @Test
    public void TicketManagerTest() {
        Ticket t = new Ticket();
        t.setAvailableTickets(173);
        t.setTicketPrice(120);
        TicketManager tm = new TicketManager(t.getAvailableTickets());

        // test the generation of the code
        tm.acquireTicket(5, t.getTicketPrice()); // generate codes & ticket list
        int[] actual = new int[5];
        for (int i = 0; i < tm.getTicketList().size(); i++) {
            actual[i] = (int) (tm.getTicketList().get(i).getTicketCode() % 1000);
        }
        int[] expected = {173, 172, 171, 170, 169};
        Assert.assertArrayEquals(expected, actual);
    }
}
