package uts.iot.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.iot.model.Customer;
import uts.iot.model.dao.AccessDBManager;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");
        try {
            Customer customer = (Customer) session.getAttribute("customer");
            String custEmail = customer.getEmail();
            //delete customer from CUSTOMER and USER_ACCOUNT Table
            manager.deleteCustomer(custEmail);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //delete the session
        session.invalidate();
        //direct user back to the index page
        request.getRequestDispatcher("index.jsp").include(request, response);
    }
}