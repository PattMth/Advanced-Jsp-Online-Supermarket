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
import uts.iot.model.Staff;
import uts.iot.model.dao.AccessDBManager;

public class StaffDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");

        try {
            Staff staff = (Staff) session.getAttribute("staff");
            String staffEmail = staff.getEmail();  
            System.out.print(staffEmail);
            //Delete staff from STAFF and USER_ACCOUNT Table
            manager.deleteStaff(staffEmail);
        } catch (SQLException ex) {
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.invalidate();                                                   //delete the session
        request.getRequestDispatcher("index.jsp").include(request, response);   //direct user back to the index page
    }
}