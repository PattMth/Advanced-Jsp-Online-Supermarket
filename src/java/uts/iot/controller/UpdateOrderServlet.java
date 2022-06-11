/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import uts.iot.model.dao.DBConnector;
import uts.iot.model.dao.OrderDao;

/**
 *
 * @author Simon
 */
public class UpdateOrderServlet extends HttpServlet {

    private OrderDao orderDao;

    public UpdateOrderServlet() throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();
        orderDao = new OrderDao(connector.openConnection());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oid = request.getParameter("oid");
        String notes = request.getParameter("notes");
        if (notes != null && notes.length() < 500) {
            request.getSession().setAttribute("orderError", null);
            try {
                orderDao.updateOrder(oid, notes);
                response.sendRedirect("OrderHistoryServlet");
            } catch (SQLException ex) {
                Logger.getLogger(CreateOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.getSession().setAttribute("orderError", "Notes should be shorter than 500 characters.");
            response.sendRedirect("OrderDetailServlet?oid="+oid);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}