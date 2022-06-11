package uts.iot.model;
import java.io.Serializable;



public class PaymentDetails implements Serializable {
    private String CustomerEmail;
    private String methodOfPayment;
    private String hashedCreditedCardNumber;
    private String cardSecurityCode;
    private String cardExpiryDate;

    public PaymentDetails(String CustomerEmail, String methodOfPayment, String hashedCreditedCardNumber, String cardSecurityCode, String cardExpiryDate) {
        this.CustomerEmail = CustomerEmail;
        this.methodOfPayment = methodOfPayment;
        this.hashedCreditedCardNumber = hashedCreditedCardNumber;
        this.cardSecurityCode = cardSecurityCode;
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String CustomerEmail) {
        this.CustomerEmail = CustomerEmail;
    }

    public String getMethodOfPayment() {
        return methodOfPayment;
    }

    public void setMethodOfPayment(String methodOfPayment) {
        this.methodOfPayment = methodOfPayment;
    }

    public String getHashedCreditedCardNumber() {
        return hashedCreditedCardNumber;
    }

    public void setHashedCreditedCardNumber(String hashedCreditedCardNumber) {
        this.hashedCreditedCardNumber = hashedCreditedCardNumber;
    }

    public String getCardSecurityCode() {
        return cardSecurityCode;
    }

    public void setCardSecurityCode(String cardSecurityCode) {
        this.cardSecurityCode = cardSecurityCode;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }
}