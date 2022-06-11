package uts.iot.controller;

import uts.iot.model.Staff;
import uts.iot.model.Customer;
import uts.iot.model.User_Account;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.iot.model.dao.AccessDBManager;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        AccessValidator validator = new AccessValidator();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");

        //To figure out if a customer or a staff logging in
        User_Account user = null;
        Customer customer = null;
        Staff staff = null;
        validator.clear(session);

        //Input validations
        if (validator.checkEmpty(email, password)) {
            session.setAttribute("empErr", "Please fill in every textfield");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Error: Email format incorrect");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format incorrect");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else {
            try {
                user = manager.findUser(email, password);    //find user in the USER_ACCOUNT table
                customer = manager.findCustomer(email);      //find customer in the CUSTOMER table
                staff = manager.findStaff(email);            //find staff in the STAFF table

                    //if customer is the one logging in
                if (user != null && customer != null && staff == null) {
                    session.setAttribute("user", user);
                    session.setAttribute("customer", customer);
                    int id = user.getUserAccountID();               //get userId of the user                  
                    manager.addLog(id, "LOGIN");           //Add a row to the Access_Log table                                                  
                    request.getRequestDispatcher("main.jsp").include(request, response);

                    //if staff is the one logging in
                } else if (user != null && staff != null && customer == null) {
                    session.setAttribute("user", user);
                    session.setAttribute("staff", staff);
                    int id = user.getUserAccountID();                     //get userId of the user
                    manager.addLog(id, "LOGIN");                  //Add a row to the Access_Log table
                    request.getRequestDispatcher("staffMain.jsp").include(request, response);

                    //if username and password not found in teh database, clear the textfield
                } else {
                    session.setAttribute("existErr", "User does not exist in the database");
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }
            } catch (SQLException | NullPointerException ex) {
                System.out.println(ex.getMessage() == null ? "User does not exist" : "welcome");
            }
        }
    }
}