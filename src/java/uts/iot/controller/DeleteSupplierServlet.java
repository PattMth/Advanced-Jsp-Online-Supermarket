package uts.iot.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.iot.model.dao.SupplierDBManager;

public class DeleteSupplierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String id = request.getParameter("id");

        SupplierDBManager manager = (SupplierDBManager) session.getAttribute("supplierManager");

        try {
            manager.deleteSupplier(Integer.parseInt(id));
            request.getRequestDispatcher("suppliers.jsp").include(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(DeleteSupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
