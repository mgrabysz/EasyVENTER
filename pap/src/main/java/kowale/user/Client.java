package kowale.user;

import java.util.LinkedList;

import kowale.ticket.Ticket;

public class Client extends User {
    private LinkedList<Ticket> tickets;

    public Client(
        String name, String surname,
        String login, String pass
    ){
        super(name, surname, login, pass);
        tickets = new LinkedList<Ticket>();
        type = "client";
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
