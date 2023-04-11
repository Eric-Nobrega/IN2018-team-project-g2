package interfaces;

import java.util.Date;

public class Sale {
    private int id;
    private String customerName;
    private boolean isPayNow;
    private double amount;
    private boolean isCash;
    private boolean isCard;
    private boolean isDomestic;
    private boolean isInterline;
    private String currency;
    private Date date;
    private double discountApplied;
    private int travelAdvisorId;

    public Sale(int id, String customerName, boolean isPayNow, double amount, boolean isCash, boolean isCard, boolean isDomestic, boolean isInterline, String currency, Date date, double discountApplied, int travelAdvisorId) {
        this.id = id;
        this.customerName = customerName;
        this.isPayNow = isPayNow;
        this.amount = amount;
        this.isCash = isCash;
        this.isCard = isCard;
        this.isDomestic = isDomestic;
        this.isInterline = isInterline;
        this.currency = currency;
        this.date = date;
        this.discountApplied = discountApplied;
        this.travelAdvisorId = travelAdvisorId;
    }

    // Getter and Setter methods for the properties

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isPayNow() {
        return isPayNow;
    }

    public void setPayNow(boolean payNow) {
        isPayNow = payNow;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isCash() {
        return isCash;
    }

    public void setCash(boolean cash) {
        isCash = cash;
    }

    public boolean isCard() {
        return isCard;
    }

    public void setCard(boolean card) {
        isCard = card;
    }

    public boolean isDomestic() {
        return isDomestic;
    }

    public void setDomestic(boolean domestic) {
        isDomestic = domestic;
    }

    public boolean isInterline() {
        return isInterline;
    }

    public void setInterline(boolean interline) {
        isInterline = interline;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(double discountApplied) {
        this.discountApplied = discountApplied;
    }

    public int getTravelAdvisorId() {
        return travelAdvisorId;
    }

    public void setTravelAdvisorId(int travelAdvisorId) {
        this.travelAdvisorId = travelAdvisorId;
    }
}
