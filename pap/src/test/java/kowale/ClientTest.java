package kowale;
import kowale.user.Client;
import kowale.user.User;

import java.util.LinkedList;
import kowale.ticket.Ticket;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClientTest {
    Client cl = new Client("John", "Rambo", "johnny", "1234");
    Ticket ticket = new Ticket(1, 100, "A", 25);

    @Test
    public void getTicketsTest(){
        LinkedList<Ticket> exp = new LinkedList<Ticket>();
        assertEquals(exp, cl.getTicketsList());
    }

    @Test
    public void buyTicketTest(){
        LinkedList<Ticket> exp = new LinkedList<Ticket>();
        exp.add(ticket);
        cl.buyTicket(ticket);
        assertEquals(exp, cl.getTicketsList());
    }

    @Test
    public void removeTicketTest(){
        LinkedList<Ticket> exp = new LinkedList<Ticket>();
        cl.buyTicket(ticket);
        cl.cancelTicket(ticket);
        assertEquals(exp, cl.getTicketsList());
    }
}
