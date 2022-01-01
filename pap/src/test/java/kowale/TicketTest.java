package kowale;

import kowale.ticket.Ticket;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicketTest{
    Ticket ticket = new Ticket(0, 1000, "A", 10);
    
    @Test
    public void getIdTest(){
        assertEquals(0, ticket.getId());
    }

    @Test
    public void getPrice(){
        assertEquals(1000, ticket.getPrice());
    }

    @Test
    public void getPriceInPLN(){
        // third parameter is maximum delta
        assertEquals(10.00f, ticket.getPriceInPLN(), 0.01);
    }

    @Test
    public void getSector(){
        assertEquals("A", ticket.getSector());
    }

    @Test
    public void getSeat(){
        assertEquals(10, ticket.getSeat());
    }

    @Test
    public void getOwner(){
        assertEquals(null, ticket.getOwner());
    }

    @Test
    public void setOwner(){
        ticket.setOwner("John");
        assertEquals("John", ticket.getOwner());
    }

    @Test
    public void removeOwner(){
        ticket.removeOwner();
        assertEquals(null, ticket.getOwner());
    }
}
