package kowale.event;

import kowale.ticket.Ticket;

import java.util.HashMap;
import java.util.ArrayList;

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
    private String address;
    private LocalDateTime dateTime;
    private ArrayList<Ticket> tickets;
    // private HashMap<String, HashMap<String, Integer>> tickets;

    public Event(
        String name,
        String organizer,
        String country,
        String city,
        String address,
        LocalDateTime dateTime
    ) {
        this.name = name;
        this.organizer = organizer;
        this.country = country;
        this.city = city;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // public HashMap<String, HashMap<String, Integer>> getTickets() {
    //     return this.tickets;
    // }

    // public void setTickets(HashMap<String, HashMap<String, Integer>> tickets) {
    //     this.tickets = tickets;
    //     // System.out.println(tickets);
    //     // System.out.println(tickets.size());
    // }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String[] getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        String[] eventInfo = {name, organizer, address, dateTime.format(formatter)};

        return eventInfo;
    }

    public HashMap<String, String> getDetails() {
        // {"Number of sector", "Number of seats", "Adult ticket price"}
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        HashMap<String, String> details = new HashMap<String, String>();
        details.put("name", name);
        details.put("organizer", organizer);
        details.put("country", country);
        details.put("city", city);
        details.put("address", address);
        details.put("dateTime", dateTime.format(formatter));

        return details;
    }

    public HashMap<String, HashMap<String, Integer>> getTicketsMap() {
        HashMap<String, HashMap<String, Integer>> sectors = new HashMap<String, HashMap<String, Integer>>(); 
        
        if (tickets.size() > 0) {
            for (int i = 0; i < tickets.size(); i++) {
                Ticket ticket = tickets.get(i);
    
                if (sectors.containsKey(ticket.getSector()) == false) {
                    // create new sector in sectors map
                    HashMap<String, Integer> numberPrice = new HashMap<String, Integer>();
                    numberPrice.put("number", 1);
                    numberPrice.put("price", ticket.getPrice());
                    sectors.put(ticket.getSector(), numberPrice);
                } else {
                    // increase number of tickets in sector
                    String sector = ticket.getSector();
                    HashMap<String, Integer> numberPrice = sectors.get(sector);
                    int number = numberPrice.get("number");
                    sectors.get(sector).put("number", number+1);
                }
            }
        } else {
            // TODO: no tickets available
        }

        return sectors;
    }

}
