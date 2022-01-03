package kowale.user;

import java.time.LocalDate;
import java.util.LinkedList;

import kowale.ticket.Ticket;

public class Client extends User {
    private LinkedList<Ticket> tickets;

    String gender;
    LocalDate birth;

    public Client(
        String name, String surname,
        String login, String password,
        String email, int phone, String gender,
        LocalDate birthDate
    ){
        super(name, surname, login, password, email, phone);
        tickets = new LinkedList<Ticket>();
        type = "client";
        this.birth = birthDate;
        this.gender = gender;
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

    public LocalDate getBirth(){
        return this.birth;
    }
    public String getGender(){
        return this.gender;
    }
}
