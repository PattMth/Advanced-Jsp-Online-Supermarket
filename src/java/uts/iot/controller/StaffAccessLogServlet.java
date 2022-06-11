package uts.iot.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.iot.model.Access_Log;
import uts.iot.model.dao.AccessDBManager;

public class StaffAccessLogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");

        try {
            //Get all the logs in the Access_Log Table
            ArrayList<Access_Log> lists = manager.fetchAllLog();
            request.setAttribute("list", lists);
            request.getRequestDispatcher("staffAccessLog.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAccessLogServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}