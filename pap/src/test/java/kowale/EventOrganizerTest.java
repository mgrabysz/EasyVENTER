package kowale;
import kowale.event.Event;
import kowale.user.EventOrganizer;
import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.Test;

public class EventOrganizerTest {
    EventOrganizer eo = new EventOrganizer("John", "Rambo", "johnny", "1234",
    "email@wp.pl", 666666666, "krzak firma");
    LocalDateTime dt;
    int numOfSectors;
    Event ev = new Event(
        "Mecz",
        "krzak firma",
        "Polska",
        "Warszafka",
        "Ulica",
        dt
    );

    @Test
    public void getCompanyTest(){
        assertEquals("krzak firma", eo.getCompanyName());
    }

    @Test
    public void getEventsListTest(){
        LinkedList<Event> exp = new LinkedList<Event>();
        assertEquals(exp, eo.getEventsList());
    }

    @Test
    public void addEventTest(){
        LinkedList<Event> exp = new LinkedList<Event>();
        exp.add(ev);
        eo.addEvent(ev);
        assertEquals(exp, eo.getEventsList());
    }

    @Test
    public void cancelEventTest(){
        LinkedList<Event> exp = new LinkedList<Event>();
        eo.addEvent(ev);
        eo.cancelEvent(ev);
        assertEquals(exp, eo.getEventsList());
    }
}
