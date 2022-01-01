package kowale;

import kowale.event.Event;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EventTest{

    LocalDateTime dateTime = LocalDateTime.now();
    Event event = new Event(0, "Mecz", "PZPN", "PGE Narodowy", dateTime, 3);

    @Test
    public void getIdTest(){
        assertEquals(0, event.getId());
    }

    @Test
    public void getNameTest(){
        assertEquals("Mecz", event.getName());
    }

    @Test
    public void getOrganizerTest(){
        assertEquals("PZPN", event.getOrganizer());
    }

    @Test
    public void getLocationTest(){
        assertEquals("PGE Narodowy", event.getLocation());
    }

    @Test
    public void getDateTimeTest(){
        assertEquals(dateTime, event.getDateTime());
    }
}
