package event;

import java.util.HashMap;
import java.time.LocalDateTime;

public class Event {
    /*
    Class that represents an event.
    */

    public String organizer;
    public String location;
    public LocalDateTime dateTime;
    public HashMap<String, Integer> tickets = new HashMap<String, Integer>();

    public Event(
        String organizer,
        String location,
        LocalDateTime dateTime,
        HashMap<String, Integer> tickets
    ) {
        this.organizer = organizer;
        this.location = location;
        this.dateTime = dateTime;
        this.tickets = tickets;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void changeDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public HashMap<String, Integer> getTickets() {
        return tickets;
    }

    public String toString() {
        String eventInfo = "Organizer: " + organizer;
        eventInfo += "\nLocation: " + location;
        eventInfo += "\nDate and time: " + dateTime;

        eventInfo += "\nTickets:";
        for (HashMap.Entry<String, Integer> entry : tickets.entrySet()) {
            eventInfo += ("\n   " + entry.getKey() + ": " + entry.getValue());
        }

        return eventInfo;
    }

}
