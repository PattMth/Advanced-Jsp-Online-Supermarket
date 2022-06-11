package uts.iot.controller;

import uts.iot.model.Staff;
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

public class StaffUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccessValidator validator = new AccessValidator();
        int id = 0;               //Id us auto-generated
        int accid = 0;           //userId is auto-generated
        String history = "";     //no history
        int points = 0;          //staff does not acquire points
        Boolean news = false;   //staff does not recieve promotional newsletter

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String number = request.getParameter("number");
        String address = request.getParameter("address");
        String type = request.getParameter("type");

        Staff staff = new Staff(id, name, email, number, address, type, history, accid);
        User_Account user = new User_Account(accid, email, password, dob, gender, news, id);
        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");

        if (validator.registerCheckEmpty(email, password, name, dob, number, gender, address)) {
            session.setAttribute("empErr", "Please fill in every textfield");
            request.getRequestDispatcher("staffDetails.jsp").include(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Error: Email format is incorrect");
            request.getRequestDispatcher("staffDetails.jsp").include(request, response);
        } else if (!validator.validateName(name)) {
            session.setAttribute("nameErr", "Error: Name format is incorrect");
            request.getRequestDispatcher("staffDetails.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Error: Password format is incorrect");
            request.getRequestDispatcher("staffDetails.jsp").include(request, response);
        } else {
            try {
                if (user != null) {
                    session.setAttribute("user", user);
                    session.setAttribute("staff", staff);
                    manager.updateStaff(name, email, number, address, type, password, dob, gender);
                    session.setAttribute("updated", "Update was successful");
                    request.getRequestDispatcher("staffDetails.jsp").include(request, response);
                } else {
                    session.setAttribute("updated", "Update was not successful");
                    request.getRequestDispatcher("staffDetails.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(StaffUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("staffDetails.jsp");
        }
    }
}