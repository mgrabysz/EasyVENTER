package kowale.user;

import java.util.LinkedList;

import kowale.ticket.Ticket;

public class Client extends User {
    private LinkedList<Ticket> tickets;

    public Client(
        String name, String surname,
        String login, String password
    ){
        super(name, surname, login, password);
        tickets = new LinkedList<Ticket>();
        type = "client";
    }

    public Client(
        String login, String password
    ){
        super(login, password);
        tickets = new LinkedList<Ticket>();
        type = "client";
    }

    // public void buyTicket(Ticket ticket){
    //     tickets.add(ticket);
    //     ticket.setOwner(this.getLogin());
    // }

    // public void cancelTicket(Ticket ticket){
    //     tickets.remove(ticket);
    //     ticket.removeOwner();
    // }

    public LinkedList<Ticket> getTicketsList(){
        return tickets;
    }
}
