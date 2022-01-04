package kowale;

import kowale.event.Event;
import java.time.LocalDateTime;
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

    @Test
    public void getNameTest(){
        assertEquals("Mecz", event.getName());
    }

    @Test
    public void getOrganizerTest(){
        assertEquals("PZPN", event.getOrganizer());
    }

    // @Test
    // public void getLocationTest(){
    //     assertEquals("PGE Narodowy", event.getLocation());
    // }

    @Test
    public void getDateTimeTest(){
        assertEquals(dateTime, event.getDateTime());
    }

    // @Test
    // public void getEventInfo(){
    //     DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    //     String[] strings = {"Mecz", "PZPN", "PGE Narodowy", dateTime.format(formatter)};
    //     assertArrayEquals(strings, event.getEventInfo());
    // }
}
