package kowale;

import kowale.ticket.Ticket;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicketTest{
    Ticket ticket = new Ticket("A", 20, 2000);

    @Test
    public void getPrice(){
        assertEquals(2000, ticket.getPrice());
    }

    @Test
    public void getPriceInPLN(){
        // third parameter is maximum delta
        assertEquals(20.00f, ticket.getPriceInPLN(), 0.01);
    }

    @Test
    public void getCategory(){
        ticket.setCategory("VIP");
        assertEquals("VIP", ticket.getCategory());
    }

    @Test
    public void getSector(){
        assertEquals("A", ticket.getSector());
    }

    @Test
    public void getSeat(){
        assertEquals(20, ticket.getSeat());
    }
}
