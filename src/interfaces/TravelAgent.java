package interfaces;

public class TravelAgent {
    private final String fullname;
    private final String address;
    private final String phonenumber;


    public TravelAgent(String fullname, String address, String phonenumber) {
        this.fullname = fullname;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
