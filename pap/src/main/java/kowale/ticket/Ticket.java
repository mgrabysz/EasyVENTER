package kowale.ticket;

public class Ticket {
    /*
    Class that represents a ticket.
    */

    private String category;
    private String sector;
    private int seat;
    private int price;

    public Ticket(
        // String category,
        String sector,
        int seat,
        int price
    ) {
        // this.category = category;
        this.sector = sector;
        this.seat = seat;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public float getPriceInPLN() {
        return price / 100f;
    }

    public String getCategory() {
        return category;
    }

    public String getSector() {
        return sector;
    }

    public int getSeat() {
        return seat;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // public String toString() {
    //     String ticketInfo = "ID: " + id;
    //     ticketInfo += "\nPrice: " + String.format("%.2f", this.getPriceInPLN()) + " PLN";
    //     ticketInfo += "\nSector " + sector;
    //     ticketInfo += "\nSeat: " + seat;
    //     ticketInfo += "\nOwner: " + owner;

    //     return ticketInfo;
    // }
}
