package uts.iot.controller;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;

public class SupplierValidator implements Serializable {

    private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";
    private String numberPattern = "([0-9]{8,10})";

    public SupplierValidator() {
    }

    public boolean validate(String pattern, String input) {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);
        return match.matches();

    }    

    public boolean checkEmpty(String name, String type, String number, String email, String address) {
        return name.isEmpty() || type.isEmpty() || number.isEmpty() || email.isEmpty() || address.isEmpty();
    }

    public boolean validateEmail(String email) {
        return validate(emailPattern, email);
    }

    public boolean validateNumber(String number) {
        return validate(numberPattern, number);
    }

    public void clear(HttpSession session) {
        session.setAttribute("supplierEmailErr", "");
        session.setAttribute("supplierNumberErr", "");
        session.setAttribute("supplierEmptyErr", "");
    }
}
