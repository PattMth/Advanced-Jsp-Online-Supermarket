package uts.iot.model;


public class Payment {
    
    private int paymentID;
    private String paymentDate;
    private int totalPrice;
    private String paymentType;
    private String paymentStatus;

    public Payment(int paymentID, String paymentDate, int totalPrice, String paymentType, String paymentStatus) {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    
}