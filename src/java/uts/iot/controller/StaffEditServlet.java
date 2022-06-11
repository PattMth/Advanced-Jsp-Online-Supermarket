package uts.iot.controller;

import uts.iot.model.Staff;
import uts.iot.model.User_Account;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import uts.iot.model.dao.AccessDBManager;

public class StaffEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");

        try {
            User_Account user = manager.findUser(email, password);
            Staff staff = manager.findStaff(email);
            if (user != null) {
                session.setAttribute("user", user);
                session.setAttribute("staff", staff);
                //Grab the staff's current session's details and post it on staffDetails.jsp
                request.getRequestDispatcher("staffDetails.jsp").include(request, response);
            } else {
                session.setAttribute("existErr", "User does not exist in the Database");
                request.getRequestDispatcher("staffDetails.jsp").include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffEditServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
        request.getRequestDispatcher("staffDetails.jsp").include(request, response);

    }

}