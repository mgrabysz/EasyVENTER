package kowale.user;

import java.util.LinkedList;

import kowale.ticket.Ticket;

public class Client extends User {
    private LinkedList<Ticket> tickets;

    public Client(
        int id, String name, String surname,
        String login, String pass
    ){
        super(id, name, surname, login, pass);
    }

    public void buyTicket(Ticket ticket){
        tickets.add(ticket);
        ticket.setOwner(this.getLogin());
    }

    public void cancelTicket(Ticket ticket){
        tickets.remove(ticket);
        ticket.removeOwner();
    }

    public LinkedList<Ticket> getTicketsList(){
        return tickets;
    }
}
