package interfaces;

public class Customer {
    private int customerId;
    private String customerName;
    private int customerPhoneNumber;
    private String customerEmail;
    private String cardNumber;
    private int cvv;
    private String customerRelationship;
    private int travelAgentId;

    public Customer(int customerId, String customerName, int customerPhoneNumber, String customerEmail, String cardNumber, int cvv, String customerRelationship, int travelAgentId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.customerRelationship = customerRelationship;
        this.travelAgentId = travelAgentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public String getCustomerRelationship() {
        return customerRelationship;
    }

    public int getTravelAgentId() {
        return travelAgentId;
    }
}
