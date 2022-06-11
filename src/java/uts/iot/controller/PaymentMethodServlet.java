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



public class PaymentMethodServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       HttpSession session = request.getSession();
       PaymentValidator validator = new PaymentValidator();
       int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
       PaymentDBManager manager = (PaymentDBManager) session.getAttribute("shipmentManager");
       ArrayList <PaymentMethod> paymentD = null;
       session.setAttribute("existErr", null);
       session.setAttribute("paymentD", null);
        
       try {
           paymentD = manager.fetchPaymentMethod(cardNumber);
           if (paymentD != null) {
           session.setAttribute("paymentD", paymentD);
           request.getRequestDispatcher("paymentdetails.jsp").include(request,response);
           } else {
               session.setAttribute("existErr", "You don't have any saved shipment addresses");
               request.getRequestDispatcher("paymentdetails.jsp").include(request, response);
           }
       } catch (SQLException ex) {
           java.util.logging.Logger.getLogger(PaymentMethodServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
   }
}