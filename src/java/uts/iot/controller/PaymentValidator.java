package uts.iot.controller;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;

public class PaymentValidator implements Serializable {

    private String IDPattern = "([1-9]|[1-9][0-9]|[1-9][0-9][0-9])";  //1-999 number patern
    private String datePattern="^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
    private String pricePattern = "([0-9]{0,2}((.)[0-9]{0,2}))"; //Any number with optional decimal.
    private String cvcPattern = "[0-9]\\{3\\}"; //Any string or number for paymentStatus or Type. 
    private String numPattern = "([1-9]|[1-9][0-9]|[1-9][0-9][0-9])";

    public PaymentValidator() {
    }

    public boolean validate(String pattern, String input) {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);

        return match.matches();

    }

  //Function to check for empty textfield in the payment.jsp
    public boolean checkEmpty(String paymentID, String paymentDate) {
        return paymentID.isEmpty() || paymentDate.isEmpty();
    }


    //function to check if paymentID format is valid
    public boolean validateID(String paymentID) {
        return validate(IDPattern, paymentID);
    }

    //function to check if paymentDate format is valid
    public boolean validateDate(String paymentDate) {
        return validate(datePattern, paymentDate);
    }

    //function to check if totalPrice format is valid
    public boolean validatePrice(String totalPrice) {
        return validate(pricePattern, totalPrice);
    }

    //function to check if status format is valid
    public boolean validateInt (String pattern, int input) {
        Pattern regEx = Pattern.compile(pattern);
        String i = Integer.toString(input);
        Matcher match = regEx.matcher(i);
        return match.matches();
    }
    
    public boolean validateCvc(int cvc) {
        return validateInt(cvcPattern, cvc);
    }
     public boolean validateCardNum(int cardNumber) {
        return validateInt(numPattern, cardNumber);
    }

    //function to clear errors when page is refreshed
    public void clear(HttpSession session) {
        session.setAttribute("idErr", "");
        session.setAttribute("dateErr", "dd/mm/yyyy");
        session.setAttribute("numErr", "");
        session.setAttribute("existErr", "");
        session.setAttribute("cvcErr", "");
        session.setAttribute("empErr", "");

    }
}