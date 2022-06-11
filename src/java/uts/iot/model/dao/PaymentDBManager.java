package uts.iot.model.dao;

import uts.iot.model.Payment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import uts.iot.model.PaymentMethod;

public class PaymentDBManager {

    private Statement st;

    public PaymentDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    //Find payment by ID
    public Payment findPayment(int paymentID, String paymentDate) throws SQLException {

        String fetch = "select * from IOTBAYUSER.PAYMENT where PAYMENTID =" + paymentID + " and PAYMENTDATE='" + paymentDate + "'";

        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            int userPaymentID = rs.getInt(1);
            String userPaymentDate = rs.getString(2);
            if (userPaymentDate.equals(paymentDate) && userPaymentID == paymentID) {
                int totalPrice = rs.getInt(3);
                String paymentType = rs.getString(4);
                String paymentStatus = rs.getString(5);

                return new Payment(userPaymentID, userPaymentDate, totalPrice, paymentType, paymentStatus);
            }

        }
        return null;
    }

    //Create
    public void addPayment(int PaymentID, String PaymentDate, int TotalPrice, String PaymentType, String PaymentStatus) throws SQLException {
        st.executeUpdate("INSERT INTO IOTBAYUSER.PAYMENT (PAYMENTID, PAYMENTDATE, TOTALPRICE, PAYMENTTYPE, PAYMENTSTATUS) VALUES(" + PaymentID + ",'" + PaymentDate + "'," + TotalPrice + ",'" + PaymentType + "','" + PaymentStatus + "')");
    }

    //Update
    public void updatePayment(int paymentID, String paymentDate, int totalPrice, String paymentType, String paymentStatus) throws SQLException {
        st.executeUpdate("UPDATE IOTBAYUSER.PAYMENT SET PAYMENTDATE='" + paymentDate + "', TOTALPRICE=" + totalPrice + ", PAYMENTTYPE='" + paymentType + "', PAYMENTSTATUS='" + paymentStatus + "' WHERE PAYMENTID=" + paymentID + "");
    }

    //Delete
    public void deletePayment(int paymentID) throws SQLException {
        st.executeUpdate("DELETE FROM IOTBAYUSER.PAYMENT WHERE PAYMENTID=" + paymentID + "");
    }

    //Fetch Payment
    public ArrayList<Payment> fetchPayments() throws SQLException {
        String fetch = "select * from PAYMENT";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Payment> temp = new ArrayList();

        while (rs.next()) {
            int paymentID = rs.getInt(1);
            String paymentDate = rs.getString(2);
            int totalPrice = rs.getInt(3);
            String paymentType = rs.getString(4);
            String paymentStatus = rs.getString(5);
            temp.add(new Payment(paymentID, paymentDate, totalPrice, paymentType, paymentStatus));

        }
        return temp;
    }

    public boolean checkPayment(int paymentID, String paymentDate) throws SQLException {
        String fetch = "select * from IOTBAYUSER.PAYMENT where PaymentID=" + paymentID + " and PaymentDATE='" + paymentDate + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            int userPaymentID = rs.getInt(1);
            String userPaymentDate = rs.getString(2);
            if (userPaymentID == (paymentID) && userPaymentDate.equals(paymentDate)) {
                return true;
            }
        }
        return false;
    }

    //Method
    //Find paymentMethod by cardNum
    public PaymentMethod findMethod(int cardNumber) throws SQLException {

        String fetch = "select * from IOTBAYUSER.PAYMENT_METHOD_DETAILS where CARDNUMBER =" + cardNumber+"";

        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            int userCardNumber = rs.getInt(3);

            if (userCardNumber == cardNumber) {
                String cardName = rs.getString(2);               
                int cvc = rs.getInt(4);
                String bankName = rs.getString(5);
                return new PaymentMethod(cardName, userCardNumber, cvc, bankName);
                
            }

        }
        return null;
    }
    
  
    //Create
    public void addPaymentMethod( String cardName, int cardNumber, int cvc, String bankName) throws SQLException {
        st.executeUpdate("INSERT INTO IOTBAYUSER.PAYMENT_METHOD_DETAILS (CARDNAME, CARDNUMBER, CVC, BANKNAME, USERACCOUNTID) VALUES('" + cardName + "'," +cardNumber+ "," + cvc + ",'" + bankName + "', 1)");
    }

    //Update
    public void updatePaymentMethod(int methodID, String cardName, int cardNumber, int cvc, String bankName) throws SQLException {
        st.executeUpdate("UPDATE IOTBAYUSER.PAYMENT_METHOD_DETAILS SET CARDNAME='" + cardName + "', CARDNUMBER=" + cardNumber + ", CVC=" + cvc + ", BANKNAME='" + bankName + "' WHERE METHODID=" + methodID + "");
    }

    //Delete
    public void deletePaymentMehtod(int methodID) throws SQLException {
        st.executeUpdate("DELETE FROM IOTBAYUSER.PAYMENT_METHOD_DETAILS WHERE METHODID=" + methodID + "");
    }

    //Fetch All
    public ArrayList<PaymentMethod> fetchPaymentMethod(int num) throws SQLException {
        String fetch = "select * from PAYMENT_METHOD_DETAILS where CARDNUMBER =" + num;
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<PaymentMethod> table = new ArrayList();

        while (rs.next()) {
            if (num == rs.getInt(3)) {
                String cardName = rs.getString(2);
                
                int cvc = rs.getInt(4);
                String bankName = rs.getString(5);
                table.add(new PaymentMethod(cardName,num, cvc, bankName));
            }

        }
        if (!table.isEmpty()) {
            return table;
        } else {
            return null;
        }
    
}

public boolean checkPaymentMethod(int methodID) throws SQLException {
        String fetch = "select * from IOTBAY.PAYMENT_METHOD_DETAILS where METHODID=" + methodID + "";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            int userMethodID = rs.getInt(1);
            
            if (userMethodID == (methodID)) {
                return true;
            }
        }
        return false;
    }
}