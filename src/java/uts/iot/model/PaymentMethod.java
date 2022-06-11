package uts.iot.model;

import java.io.Serializable;


public class PaymentMethod implements Serializable {
    
    
    private int methodID;
    private String cardName;
    private int cardNumber;
    private int cvc;
    private String bankName;

    public PaymentMethod(String cardName, int cardNumber, int cvc, String bankName) {
        
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.bankName = bankName;
    }

    public int getMethodID() {
        return methodID;
    }

    public void setMethodID(int methodID) {
        this.methodID = methodID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
}