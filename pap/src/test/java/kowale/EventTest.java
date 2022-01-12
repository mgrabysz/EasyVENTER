package kowale;

import kowale.event.Event;
import kowale.ticket.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EventTest{
    String testDateTimeString = "27-11-1942 10:15";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime testDateTime = LocalDateTime.parse(testDateTimeString, formatter);
    
    Event event = new Event(
        "Meczyk",
        "PZPN",
        "Polska",
        "Warszafka",
        "Adres",
        testDateTime
    );
    Ticket ticket0 = new Ticket("A", 1, 20000);
    Ticket ticket1 = new Ticket("B", 1, 10000);
    Ticket ticket2 = new Ticket("B", 2, 10000);
    ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    @Test
    public void getNameTest(){
        assertEquals("Meczyk", event.getName());
    }

    @Test
    public void setNameTest(){
        event.setName("Meczyk");
        assertEquals("Meczyk", event.getName());
    }

    @Test
    public void getOrganizerTest(){
        assertEquals("PZPN", event.getOrganizer());
    }

    @Test
    public void setOrganizerTest(){
        event.setOrganizer("k20");
        assertEquals("k20", event.getOrganizer());
    }

    @Test
    public void getCountryTest(){
        assertEquals("Polska", event.getCountry());
    }

    @Test
    public void setCountryTest(){
        event.setCountry("Belgia");
        assertEquals("Belgia", event.getCountry());
    }

    @Test
    public void getCityTest(){
        assertEquals("Warszafka", event.getCity());
    }

    @Test
    public void setCityTest(){
        event.setCity("Gliwice");
        assertEquals("Gliwice", event.getCity());
    }

    @Test
    public void getAddressTest(){
        assertEquals("Adres", event.getAddress());
    }

    @Test
    public void setAddressTest(){
        event.setAddress("Polna 15");
        assertEquals("Polna 15", event.getAddress());
    }

    @Test
    public void getDateTimeTest(){
        assertEquals(testDateTime, event.getDateTime());
    }

    @Test
    public void setDateTest(){
        LocalDateTime newDate = LocalDateTime.now();
        event.setDateTime(newDate);
        assertEquals(newDate, event.getDateTime());
    }

    @Test
    public void ticketsTest(){
        tickets.add(ticket0);
        tickets.add(ticket1);
        tickets.add(ticket2);
        event.setTickets(tickets);;
        assertEquals(tickets, event.getTickets());
    }

    @Test
    public void getInfoTest(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String[] eventInfo = {event.getName(), event.getCity(), event.getAddress(), event.getDateTime().format(formatter)};
        
        assertArrayEquals(eventInfo, event.getInfo());
    }

    @Test
    public void getDetailsTest(){
        HashMap<String, String> details = new HashMap<String, String>();

        details.put("name", "Meczyk");
        details.put("organizer", "PZPN");
        details.put("country", "Polska");
        details.put("city", "Warszafka");
        details.put("address", "Adres");
        details.put("dateTime", testDateTimeString);
        
        assertEquals(details, event.getDetails());
    }

    @Test
    public void getExtendedDetailsTest(){
        HashMap<String, String> extendedDetails = new HashMap<String, String>();

        extendedDetails.put("name", "Meczyk");
        extendedDetails.put("organizer", "PZPN");
        extendedDetails.put("country", "Polska");
        extendedDetails.put("city", "Warszafka");
        extendedDetails.put("address", "Adres");
        extendedDetails.put("year", "1942");
        extendedDetails.put("month", "November");
        extendedDetails.put("day", "27");
        extendedDetails.put("hour", "10");
        extendedDetails.put("minute", "15");
        
        assertEquals(extendedDetails, event.getExtendedDetails());
    }

    @Test
    public void getTicketsMap(){
        HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();

        HashMap<String, String> numberPriceA = new HashMap<String, String>();

        numberPriceA.put("number", "1");
        numberPriceA.put("Price", "200.00");

        map.put("A", numberPriceA);

        HashMap<String, String> numberPriceB = new HashMap<String, String>();

        numberPriceB.put("number", "2");
        numberPriceB.put("Price", "100.00");

        map.put("B", numberPriceB);
    }
}
