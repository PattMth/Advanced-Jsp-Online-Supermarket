package uts.iot.controller;

import uts.iot.model.Customer;
import uts.iot.model.User_Account;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import uts.iot.model.dao.AccessDBManager;

public class CustomerEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");

        try {
            User_Account user = manager.findUser(email, password);
            Customer customer = manager.findCustomer(email);
            if (user != null) {
                //Grab all the details of the logged in user and post it on the customerDetails.jsp
                session.setAttribute("user", user);
                session.setAttribute("customer", customer);
                request.getRequestDispatcher("customerDetails.jsp").include(request, response);
            } else {
                session.setAttribute("existErr", "User does not exist in the Database");
                request.getRequestDispatcher("customerDetails.jsp").include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerEditServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
        request.getRequestDispatcher("customerDetails.jsp").include(request, response);

    }

}