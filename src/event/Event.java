package event;

import java.util.HashMap;
import java.time.LocalDateTime;

public class Event {
    /*
    Class that represents an event.
    */

    private String id;
    private String organizer;
    private String location;
    private LocalDateTime dateTime;
    private HashMap<String, Integer> tickets = new HashMap<String, Integer>();

    public Event(
        String id,
        String organizer,
        String location,
        LocalDateTime dateTime,
        HashMap<String, Integer> tickets
    ) {
        this.id = id;
        this.organizer = organizer;
        this.location = location;
        this.dateTime = dateTime;
        this.tickets = tickets;
    }

    public String getId() {
        return id;
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
        String eventInfo = "ID: " + id;
        eventInfo += "\nOrganizer: " + organizer;
        eventInfo += "\nLocation: " + location;
        eventInfo += "\nDate and time: " + dateTime;

        eventInfo += "\nTickets:";
        for (HashMap.Entry<String, Integer> entry : tickets.entrySet()) {
            eventInfo += ("\n   " + entry.getKey() + ": " + entry.getValue());
        }

        return eventInfo;
    }

}
