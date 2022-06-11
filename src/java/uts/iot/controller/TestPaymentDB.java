package uts.iot.controller;

import uts.iot.model.dao.DBConnector;
import uts.iot.model.dao.PaymentDBManager;
import uts.iot.model.PaymentMethod;

import java.sql.*;

import java.util.*;

import java.util.logging.*;
import uts.iot.model.Payment;

public class TestPaymentDB {

    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private PaymentDBManager db;

    public static void main(String[] args) throws SQLException {

        (new TestPaymentDB()).runQueries();

    }

    public TestPaymentDB() {
        try {

            connector = new DBConnector();

            conn = connector.openConnection();

            db = new PaymentDBManager(conn);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestPaymentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private char readChoice() {
        System.out.println("Operation CRUDS or * to exit: ");
        return in.nextLine().charAt(0);
    }

    private void runQueries() throws SQLException {
        char c;
        while ((c = readChoice()) != '*') {
            switch (c) {
                case 'C':
                    testAdd();
                    break;
                case 'R':
                    testRead();
                    break;
                case 'U':
                    testUpdate();
                    break;
                case 'D':
                    testDelete();
                    break;
                case 'S':
                    showAll();
                    break;
                default:
                    System.out.println("Unknown Command");
                    break;

            }

        }

    }

    private void testAdd() {
        //System.out.print("Paymentid: ");

        //int PaymentID = in.nextInt();
       // in.nextLine();

        System.out.print("CardName ");

        String cardName = in.nextLine();

        System.out.print("Number: ");

        int  cardNumber = in.nextInt();
        in.nextLine();

        System.out.print("cvc ");

        int cvc = in.nextInt();
        in.nextLine();

        System.out.print("bName ");

        String bankName = in.nextLine();
        
         int userID = in.nextInt();
         in.nextLine();
        try {
            db.addPaymentMethod(cardName, cardNumber, cvc, bankName);
        } catch (SQLException ex) {
            Logger.getLogger(TestPaymentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Payment method is added to the database.");
    }

    private void testRead() throws SQLException {
        System.out.print("card num: ");
        int cardNumber = in.nextInt();
        in.nextLine();
        
        PaymentMethod paymentMethod = db.findMethod(cardNumber);
        if (paymentMethod != null) {
            System.out.println("Payment with an id of  " + paymentMethod.getCardNumber() + " exists in the database.");
        } else {
            System.out.println("Payment doesnt exist");
        }
    }

    private void testUpdate() throws SQLException {
        System.out.print("Payment id: ");
        int paymentID = in.nextInt();
        in.nextLine();
        System.out.print("Payment Date: ");
        String paymentDate = in.nextLine();

        try {
            if (db.checkPayment(paymentID, paymentDate)) {
                System.out.print("Total price: ");
                int totalPrice = in.nextInt();
                in.nextLine();
                System.out.print("Payment Type: ");
                String paymentType = in.nextLine();
                System.out.print("Payment Status: ");
                String paymentStatus = in.nextLine();
                db.updatePayment(paymentID, paymentDate, totalPrice, paymentType, paymentStatus);
            } else {
                System.out.println("Payment does not exist. ");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TestPaymentDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void testDelete() {
        System.out.print("Payment id: ");
        int paymentID = in.nextInt();
        in.nextLine();
        System.out.print("Payment Date: ");
        String paymentDate = in.nextLine();

        try {
            if (db.checkPayment(paymentID, paymentDate)) {
                db.deletePayment(paymentID);
            } else {
                System.out.println("Payment does not exist. ");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TestPaymentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showAll() {
        try {
            ArrayList<Payment> payments = db.fetchPayments();
            System.out.println("PAYMENT TABLE: ");
            payments.stream().forEach((payment) -> {
               System.out.printf("%-40s %-40s %-40s %-40s %-40s \n", payment.getPaymentID(), payment.getPaymentDate(), payment.getTotalPrice(), payment.getPaymentType(), payment.getPaymentStatus());
            });
            System.out.println();
        } catch (SQLException ex) {
            Logger.getLogger(TestPaymentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}