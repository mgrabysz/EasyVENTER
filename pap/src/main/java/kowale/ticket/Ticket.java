package kowale.ticket;

public class Ticket {
    /*
    Class that represents a ticket.
    */

    private int id;
    private int price;
    private String sector;
    private int seat;
    private String owner;

    public Ticket(
        int id,
        int price,
        String sector,
        int seat
    ) {
        this.id = id;
        this.price = price;
        this.sector = sector;
        this.seat = seat;
        this.owner = null;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public float getPriceInPLN() {
        return price / 100f;
    }

    public String getSector() {
        return sector;
    }

    public int getSeat() {
        return seat;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void removeOwner() {
        this.owner = null;
    }

    public String toString() {
        String ticketInfo = "ID: " + id;
        ticketInfo += "\nPrice: " + String.format("%.2f", this.getPriceInPLN()) + " PLN";
        ticketInfo += "\nSector " + sector;
        ticketInfo += "\nSeat: " + seat;
        ticketInfo += "\nOwner: " + owner;

        return ticketInfo;
    }
}
