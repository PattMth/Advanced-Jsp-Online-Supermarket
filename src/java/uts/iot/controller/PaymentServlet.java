/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.iot.controller;

import uts.iot.model.Payment;

import uts.iot.model.dao.PaymentDBManager;
import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.iot.model.dao.DBConnector;



public class PaymentServlet extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       HttpSession session = request.getSession();
       PaymentValidator validator = new PaymentValidator();
       int paymentID = Integer.parseInt(request.getParameter("paymentID"));
       String paymentDate = request.getParameter("paymentDate");
       
       PaymentDBManager manager = (PaymentDBManager) session.getAttribute("manager");
       Payment payment = null;
       session.setAttribute ("payment", null);
       session.setAttribute("searchErr", null);
       validator.clear(session);
       
       if (!validator.validateDate(paymentDate)) {
           session.setAttribute("dateErr", "Date is invalid! Follow: dd/mm/yyyy");
           request.getRequestDispatcher("payment.jsp").include(request,response);
        }/* else if (!validator.validateID(paymentID)) {
            session.setAttribute("idErr", "Error: ID format incorrect");
            request.getRequestDispatcher("payment.jsp").include(request, response); */
         
       
       else {
           try {
               payment = manager.findPayment(paymentID,paymentDate);
               if (payment != null) {
                   session.setAttribute("payment", payment);
                   
                   request.getRequestDispatcher("payment.jsp").include(request, response);
                   
               } else {
                   session.setAttribute("searchErr", "Payment does not exist in the Database! Please try again.");
                   request.getRequestDispatcher("payment.jsp").include(request, response);
               }
           } catch (SQLException ex) {
               Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
}