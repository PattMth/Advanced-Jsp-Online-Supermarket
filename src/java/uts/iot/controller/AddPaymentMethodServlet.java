package uts.iot.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.iot.model.PaymentMethod;
import uts.iot.model.dao.PaymentDBManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uts.iot.model.User;

public class AddPaymentMethodServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PaymentValidator validator = new PaymentValidator();
        String cardName = request.getParameter("cardName");
        int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
        int cvc = Integer.parseInt(request.getParameter("cvc"));
        String bankName = request.getParameter("bankName");
        PaymentMethod PaymentMethod = null;
        PaymentDBManager manager = (PaymentDBManager) session.getAttribute("manager");
        session.setAttribute("addSuccess", null);

        validator.clear(session);
       // if (!validator.validateCvc(cvc)) {
           // session.setAttribute("cvcErr", "Wrong cvc! Please try again.");
           // request.getRequestDispatcher("addPaymentDetails.jsp").include(request, response);
       //  } else {
            try {
                manager.addPaymentMethod(cardName, cardNumber, cvc, bankName);
                //request.setAttribute("PaymentMethod", PaymentMethod);
                //session.setAttribute("PaymentMethod", PaymentMethod);

                request.getRequestDispatcher("addPaymentDetails.jsp").include(request, response);
            } catch (SQLException ex) {
                
            Logger.getLogger(AddPaymentMethodServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        

    }