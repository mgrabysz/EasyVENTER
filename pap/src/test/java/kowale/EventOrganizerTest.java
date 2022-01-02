package kowale;
import kowale.event.Event;
import kowale.user.EventOrganizer;
import static org.junit.Assert.assertEquals;
// import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.Test;

public class EventOrganizerTest {
    EventOrganizer eo = new EventOrganizer("John", "Rambo", "johnny", "1234");
    LocalDateTime dt;
    int numOfSectors;
    // HashMap<String, Integer> tickets;
    Event ev = new Event("Kezak", "firma", "Warsaw0", dt);

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
