/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.iot.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.iot.model.Payment;
import uts.iot.model.dao.DBConnector;
import uts.iot.model.dao.PaymentDBManager;

/**
 *
 * @author Cowton
 */
public class TestServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            DBConnector connector = new DBConnector(); //Create a connection and initialize DB conn-field
            Connection conn = connector.openConnection(); //Get the protected connection instance from DB superclass to share for the application classes
            PaymentDBManager db = new PaymentDBManager(conn); //DBManger instance provide users with access to CRUD operati

            // add to databae
            //db.addPayment(ID, email, name, password, dob, favcol);
            // get from database
            ArrayList<Payment> listPayment = new ArrayList<>();
            listPayment = db.fetchPayments();
            // send as a part of request
            request.setAttribute("listPayment", listPayment);
            //session.setAttribute("listStudent", listStudent);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Payment.jsp");
            dispatcher.forward(request, response);

            //check if the list is empty or not          
            if (!listPayment.isEmpty()) {
                PrintWriter out = response.getWriter();
                for (Payment p : listPayment) {

                    out.println("<p> My Servlet TestServlet at " + p.getPaymentID() + "</p>");
                    out.println("<p> My Servlet TestServlet at " + p.getPaymentDate() + "</p>");

                }
            }
        } catch (SQLException ex) {
               Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    }
}