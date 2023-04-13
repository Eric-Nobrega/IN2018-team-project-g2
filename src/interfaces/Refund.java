package interfaces;

public class Refund {
    private String date;

    private int ticketID;
    private String customerName; //db method, get customer name by ticket id on ticket table

    public Refund(String date, int ticketID) {
        this.date = date;
        this.ticketID = ticketID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
