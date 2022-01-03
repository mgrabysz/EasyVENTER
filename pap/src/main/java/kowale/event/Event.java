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
    private String country;
    private String city;
    private String street;
    private LocalDateTime dateTime;
    // private int numOfSectors;
    private HashMap<String, HashMap<String, Integer>> tickets;

    public Event(
        String name,
        String organizer,
        String country,
        String city,
        String street,
        LocalDateTime dateTime
    ) {
        this.name = name;
        this.organizer = organizer;
        this.country = country;
        this.city = city;
        this.street = street;
        this.dateTime = dateTime;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
        // System.out.println(tickets);
        // System.out.println(tickets.size());
    }

    // public HashMap<String, Integer> getTickets() {
    //     return tickets;
    // }

    public String[] getEventInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        String[] eventInfo = {name, country, city, dateTime.format(formatter)};
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

    public String[][] getDetails() {
        // {"Number of sector", "Number of seats", "Adult ticket price"}

        String[][] details = new String[tickets.size()][3];
        
        if (tickets.size() > 0) {
            // details = new String[events.size()][4];
            System.out.println(tickets);

            for (int i = 0; i < tickets.size(); i++) {
                System.out.println(String.valueOf(i));
                HashMap<String, Integer> sector = tickets.get(String.valueOf(i+1));
                System.out.println(sector);
                details[i][0] = String.valueOf(i+1);
                details[i][1] = String.valueOf(sector.get("Number"));
                details[i][2] = String.valueOf(sector.get("Price"));
                // details[i][0] = String.valueOf(i+1);
            }
        }

        // System.out.println("details:");
        // System.out.println(details);

        return details;
    }

}
