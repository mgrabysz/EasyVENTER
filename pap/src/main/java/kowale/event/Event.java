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
        // DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String[] eventInfo = {name, city, address, dateTime.format(formatter)};

        return eventInfo;
    }

    public HashMap<String, String> getExtendedDetails() {
        HashMap<String, String> details = new HashMap<String, String>();
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        details.put("name", name);
        details.put("organizer", organizer);
        details.put("country", country);
        details.put("city", city);
        details.put("address", address);
        details.put("year", String.valueOf(dateTime.getYear()));
        String month = String.valueOf(dateTime.getMonth());
        month = month.toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);
        String day = String.valueOf(dateTime.getDayOfMonth());
        String hour = String.valueOf(dateTime.getHour());
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        String minute = String.valueOf(dateTime.getMinute());
        if (minute.length() == 1) {
            minute = "0" + minute;
        }
        details.put("month", month);
        details.put("day", day);
        details.put("hour", hour);
        details.put("minute", minute);

        // System.out.println(details);
        return details;
    }

    public HashMap<String, String> getDetails() {
        // DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        HashMap<String, String> details = new HashMap<String, String>();
        details.put("name", name);
        details.put("organizer", organizer);
        details.put("country", country);
        details.put("city", city);
        details.put("address", address);
        details.put("dateTime", dateTime.format(formatter));
        // System.out.println(dateTime.format(formatter));

        return details;
    }

    public HashMap<String, HashMap<String, String>> getTicketsMap() {
        // {"Sector", "Number of seats", "Base ticket price"}
        HashMap<String, HashMap<String, String>> sectors = new HashMap<String, HashMap<String, String>>();

        if (tickets.size() > 0) {
            for (int i = 0; i < tickets.size(); i++) {
                Ticket ticket = tickets.get(i);

                if (sectors.containsKey(ticket.getSector()) == false) {
                    // create new sector in sectors map
                    HashMap<String, String> numberPrice = new HashMap<String, String>();
                    numberPrice.put("number", String.valueOf(1));
                    numberPrice.put("price", String.valueOf(ticket.getPriceInPLN()));
                    sectors.put(ticket.getSector(), numberPrice);
                } else {
                    // increase number of tickets in sector
                    String sector = ticket.getSector();
                    HashMap<String, String> numberPrice = sectors.get(sector);
                    int number = Integer.valueOf(numberPrice.get("number"));
                    sectors.get(sector).put("number", String.valueOf(number+1));
                }
            }
        } else {
            // TODO: no tickets available
        }

        // System.out.println(sectors);

        return sectors;
    }

    public HashMap<String, String> getTicketsMapSpecificUser() {
        // {Category : Number of seats (posessed by this user)}
        // TODO: all
        HashMap<String, String> table = new HashMap<String, String>();

        if (tickets.size() > 0) {
            for (int i = 0; i < tickets.size(); i++) {
                Ticket ticket = tickets.get(i);
                String category = ticket.getCategory();

                if (!table.containsKey(category)) {

                    table.put(category, String.valueOf(1));

                } else {
                    // increase number of tickets in category
                    int number = Integer.valueOf(table.get(category));
                    table.put("number", String.valueOf(number+1));
                }
            }
        } else {
            // TODO: no tickets available
        }

        // System.out.println(sectors);

        return table;
    }

}
