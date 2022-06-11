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
import uts.iot.model.User_Account;
import uts.iot.model.dao.AccessDBManager;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");
        try {
            User_Account user = (User_Account) session.getAttribute("user");
            int id = user.getUserAccountID();       //get the current session's userId
            manager.addLog(id, "LOGOUT");    //Add a logout row into the ACCESS_LOG Table
        } catch (SQLException ex) {
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.invalidate();   //delete the session
        request.getRequestDispatcher("index.jsp").include(request, response);   //direct user back to the index page
    }
}