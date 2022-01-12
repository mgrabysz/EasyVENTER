package kowale;

import kowale.event.Event;
import kowale.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EventTest{

    LocalDateTime dateTime = LocalDateTime.now();
    Event event = new Event(
        "Mecz",
        "PZPN",
        "Polska",
        "Warszafka",
        "Adres",
        dateTime
    );
    Ticket ticket = new Ticket("A", 20, 20000);
    ArrayList<Ticket> tickets2 = new ArrayList<Ticket>();

    @Test
    public void getNameTest(){
        assertEquals("Mecz", event.getName());
    }

    @Test
    public void getOrganizerTest(){
        assertEquals("PZPN", event.getOrganizer());
    }

    @Test
    public void getDateTimeTest(){
        assertEquals(dateTime, event.getDateTime());
    }

    @Test
    public void getCountryTest(){
        assertEquals("Polska", event.getCountry());
    }

    @Test
    public void getCityTest(){
        assertEquals("Warszafka", event.getCity());
    }

    @Test
    public void getAddressTest(){
        assertEquals("Adres", event.getAddress());
    }

    @Test
    public void setOrganizerTest(){
        event.setOrganizer("k20");
        assertEquals("k20", event.getOrganizer());
    }

    @Test
    public void setNameTest(){
        event.setName("meczyk");
        assertEquals("meczyk", event.getName());
    }

    @Test
    public void setCountryTest(){
        event.setCountry("Belgia");
        assertEquals("Belgia", event.getCountry());
    }

    @Test
    public void setCityTest(){
        event.setCity("Gliwice");
        assertEquals("Gliwice", event.getCity());
    }

    @Test
    public void setAddressTest(){
        event.setAddress("Polna 15");
        assertEquals("Polna 15", event.getAddress());
    }

    @Test
    public void setDateTest(){
        LocalDateTime newDate = LocalDateTime.now();
        event.setDateTime(newDate);
        assertEquals(newDate, event.getDateTime());
    }

    @Test
    public void setTicketsTest(){
        tickets2.add(ticket);
        event.setTickets(tickets2);;
        assertEquals(tickets2, event.getTickets());
    }

}
