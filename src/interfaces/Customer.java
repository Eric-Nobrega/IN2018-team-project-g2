package interfaces;

public class Customer {
    private int customerId;
    private String customerName;
    private int CustomerphoneNumber;
    private String customerEmail;
    private String cardNumber;
    private int cvv;
    private String Relationship;
    private int travelAgentId;

    public Customer(int customerId, String customerName, int customerPhoneNumber, String customerEmail, String cardNumber, int cvv, String customerRelationship, int travelAgentId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.CustomerphoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.Relationship = customerRelationship;
        this.travelAgentId = travelAgentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerphoneNumber() {
        return CustomerphoneNumber;
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

    public String getRelationship() {
        return Relationship;
    }

    public int getTravelAgentId() {
        return travelAgentId;
    }
}
