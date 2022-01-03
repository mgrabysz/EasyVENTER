package kowale.user;

import java.util.LinkedList;

import kowale.event.Event;

public class EventOrganizer extends User {
    private LinkedList<Event> events;

    private String companyName;

    public EventOrganizer(
        String name, String surname,
        String login, String password,
        String email, int phone, String company
    ){
        super(name, surname, login, password, email, phone);
        events = new LinkedList<Event>();
        type = "organizer";
        this.companyName = company;
    }

    public EventOrganizer(
        String login, String password
    ){
        super(login, password);
        events = new LinkedList<Event>();
        type = "organizer";
    }

    public String getCompanyName(){
        return this.companyName;
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void cancelEvent(Event event){
        events.remove(event);
    }

    public LinkedList<Event> getEventsList(){
        return events;
    }
}
