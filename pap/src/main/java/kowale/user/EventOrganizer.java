package kowale.user;

import java.util.LinkedList;

import kowale.event.Event;

public class EventOrganizer extends User {
    private LinkedList<Event> events;

    public EventOrganizer(
        String name, String surname,
        String login, String pass
    ){
        super(name, surname, login, pass);
        events = new LinkedList<Event>();
    }
    public EventOrganizer(
        int id, String name, String surname,
        String login, String pass
    ){
        super(id, name, surname, login, pass);
        events = new LinkedList<Event>();
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void cancelEvent(Event event){
        events.remove(event);
    }

    public void changeEvent(){

    }

    public LinkedList<Event> getEventsList(){
        return events;
    }
}
