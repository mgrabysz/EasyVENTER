package kowale.ticket;

public class Ticket {
    /*
    Class that represents a ticket.
    */

    private String id;
    private int price;
    private String sector;
    private int seat;
    private Boolean isBought;
    private String owner;

    public Ticket(
        String id,
        int price,
        String sector,
        int seat
    ) {
        this.id = id;
        this.price = price;
        this.sector = sector;
        this.seat = seat;
        this.isBought = false;
        this.owner = null;
    }

    public String getId() {
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

    public Boolean isBought() {
        return isBought;
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
        ticketInfo += "\nIs bouth: " + isBought;
        ticketInfo += "\nOwner: " + owner;

        return ticketInfo;
    }
}
