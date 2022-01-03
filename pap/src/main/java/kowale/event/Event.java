package kowale.event;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    /*
    Class that represents an event.
    */

    private String name;
    private String organizer;
    private String location;
    private LocalDateTime dateTime;
    // private int numOfSectors;
    private HashMap<String, HashMap<String, Integer>> tickets =
        new HashMap<String, HashMap<String, Integer>>();

    public Event(
        String name,
        String organizer,
        String location,
        LocalDateTime dateTime
    ) {
        this.name = name;
        this.organizer = organizer;
        this.location = location;
        this.dateTime = dateTime;
        // this.numOfSectors = numOfSectors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public HashMap<String, HashMap<String, Integer>> getTickets() {
        return this.tickets;
    }

    public void setTickets(HashMap<String, HashMap<String, Integer>> tickets) {
        this.tickets = tickets;
    }

    // public HashMap<String, Integer> getTickets() {
    //     return tickets;
    // }

    public String[] getEventInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        String[] eventInfo = {name, organizer, location, dateTime.format(formatter)};
        // String eventInfo = "\nName: " + name;
        // eventInfo += "\nOrganizer: " + organizer;
        // eventInfo += "\nLocation: " + location;
        // eventInfo += "\nDate and time: " + dateTime;
        // eventInfo += "\nTickets:";
        // for (HashMap.Entry<String, Integer> entry : tickets.entrySet()) {
        //     eventInfo += ("\n   " + entry.getKey() + ": " + entry.getValue());
        // }

        return eventInfo;
    }

}
