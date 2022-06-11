package uts.iot.controller;

import uts.iot.model.Customer;
import uts.iot.model.User_Account;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import java.util.logging.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.iot.model.dao.AccessDBManager;

public class CustomerUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccessValidator validator = new AccessValidator();
        int id = 0;                                               //id is auto-generated
        int accid = 0;                                           //id is auto-generated

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String number = request.getParameter("number");
        String address = request.getParameter("address");
        String news = request.getParameter(String.valueOf("news"));
        validator.clear(session);

        Customer customer = new Customer(id, name, number, email, address, Boolean.valueOf(news));
        User_Account user = new User_Account(accid, email, password, dob, gender, Boolean.valueOf(news), id);
        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");

        //Input validations
        if (validator.registerCheckEmpty(email, password, name, dob, number, gender, address)) {
            session.setAttribute("empErr", "Please fill in every textfield");
            request.getRequestDispatcher("customerDetails.jsp").include(request, response);
        } else if (!validator.validateNumber(number)) {
            session.setAttribute("numErr", "Error: Number format incorrect");
            request.getRequestDispatcher("customerDetails.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format incorrect");
            request.getRequestDispatcher("customerDetails.jsp").include(request, response);
        } else if (!validator.validateName(name)) {
            session.setAttribute("nameErr", "Error: Name format incorrect");
            request.getRequestDispatcher("customerDetails.jsp").include(request, response);
        } else {
            try {
                if (user != null) {
                    session.setAttribute("user", user);
                    session.setAttribute("customer", customer);
                    //Update both CUSTOMER and USER_ACCOUNT table
                    manager.updateCustomer(name, number, email, address, password, dob, gender, Boolean.TRUE);
                    //Displays a message to the user if update is successful
                    session.setAttribute("updated", "Update was successful");
                    request.getRequestDispatcher("customerDetails.jsp").include(request, response);
                } else {
                    session.setAttribute("updated", "Update was not successful");
                    request.getRequestDispatcher("customerDetails.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("customerDetails.jsp");
        }
    }
}