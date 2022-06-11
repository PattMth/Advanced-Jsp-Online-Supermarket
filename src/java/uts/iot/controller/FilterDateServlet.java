package uts.iot.controller;

import uts.iot.model.Access_Log;
import uts.iot.model.Staff;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import java.util.logging.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.iot.model.dao.AccessDBManager;

public class FilterDateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String date = request.getParameter("date");
        String userId = request.getParameter("userId");
        AccessDBManager manager = (AccessDBManager) session.getAttribute("accessManager");
        Staff staff = (Staff) session.getAttribute("staff");
      
        try {
                //Filter by date and userId
            if (staff != null && !userId.isEmpty() && !date.isEmpty()) {
                ArrayList<Access_Log> lists = manager.fetchLogDate(date, Integer.parseInt(userId));
                request.setAttribute("list", lists);
                request.getRequestDispatcher("staffAccessLog.jsp").forward(request, response);
                
                //Filter by date
            } else if (userId.isEmpty() && staff != null) {
                ArrayList<Access_Log> lists = manager.fetchLogOnlyDate(date);
                request.setAttribute("list", lists);
                request.getRequestDispatcher("staffAccessLog.jsp").forward(request, response);
                
                //Filter by userId
            } else if (date.isEmpty() && staff != null){
                ArrayList<Access_Log> lists = manager.fetchLog(Integer.parseInt(userId));
                request.setAttribute("list", lists);
                request.getRequestDispatcher("staffAccessLog.jsp").forward(request, response);                             
            }             
        } catch (SQLException ex) {
            Logger.getLogger(FilterDateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}