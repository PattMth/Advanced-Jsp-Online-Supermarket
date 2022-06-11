package uts.iot.controller;

import uts.iot.model.Supplier;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import java.util.logging.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.iot.model.dao.SupplierDBManager;

public class FilterSupplierServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String type = request.getParameter("type");
        SupplierDBManager manager = (SupplierDBManager) session.getAttribute("supplierManager");
      
        try {
                ArrayList<Supplier> suppliers = manager.filterSuppliers(name, type);
                request.setAttribute("suppliers", suppliers);
                request.getRequestDispatcher("filteredSuppliers.jsp").forward(request, response);                       
                         
        } catch (SQLException ex) {
            Logger.getLogger(FilterSupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}