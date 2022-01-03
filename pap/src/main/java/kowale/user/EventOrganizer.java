package kowale.user;

import java.util.LinkedList;

import kowale.event.Event;

public class EventOrganizer extends User {
    private LinkedList<Event> events;

    public EventOrganizer(
        String name, String surname,
        String login, String password
    ){
        super(name, surname, login, password);
        events = new LinkedList<Event>();
        type = "organizer";
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
