package interfaces;

public class Customer {
    private int id;

    private String customerName;
    private int customerPhoneNumber;
    private String customerEmail;
    private String customerPaymentInfo;
    private String customerRelationship;

    public Customer(int id, String customerName, int customerPhoneNumber, String customerEmail, String customerPaymentInfo, String customerRelationship) {
        this.id = id;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.customerPaymentInfo = customerPaymentInfo;
        this.customerRelationship = customerRelationship;
    }

    public int getId() {
        return id;
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

    public String getCustomerPaymentInfo() {
        return customerPaymentInfo;
    }

    public String getCustomerRelationship() {
        return customerRelationship;
    }
}
