package interfaces;

public class Customer {
    private final int customerId;
    private final String customerName;
    private final int CustomerphoneNumber;
    private final String customerEmail;
    private final String cardNumber;
    private final int cvv;
    private final String Relationship;
    private final int travelAgentId;

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
