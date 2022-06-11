package uts.iot.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.iot.model.PaymentMethod;
import uts.iot.model.dao.PaymentDBManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ViewPaymentMethodServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       HttpSession session = request.getSession();
       PaymentValidator validator = new PaymentValidator();
       int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
       PaymentDBManager manager = (PaymentDBManager) session.getAttribute("shipmentManager");
       PaymentMethod paymentM = null; 
       session.setAttribute("existErr", null);
       session.setAttribute("paymentM", null);
       validator.clear(session);
       if (!validator.validateCardNum(cardNumber)) {
           session.setAttribute("numErr", "Number pattern incorrect");
           request.getRequestDispatcher("paymentDetails.jsp").include(request,response);
        }
       try {
               paymentM = manager.findMethod(cardNumber);
               if (paymentM != null) {
                   session.setAttribute("paymentM", paymentM);
                   
                   request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
                   
               } else {
                   session.setAttribute("searchErr", "Payment Method does not exist in the Database! Please try again.");
                   request.getRequestDispatcher("paymentDetails.jsp").include(request, response);
               }
           } catch (SQLException ex) {
               Logger.getLogger(ViewPaymentMethodServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }