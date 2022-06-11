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
import uts.iot.model.Device;
import uts.iot.model.dao.DBConnector;
import uts.iot.model.dao.IoTDeviceDao;
import uts.iot.model.dao.OrderDao;

/**
 *
 * @author Simon
 */
public class CreateOrderServlet extends HttpServlet {

    private OrderDao orderDao;
    private IoTDeviceDao deviceDao;

    public CreateOrderServlet() throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();
        orderDao = new OrderDao(connector.openConnection());
        deviceDao = new IoTDeviceDao(connector.openConnection());

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
        String deviceId = request.getParameter("deviceId");
        String total = request.getParameter("total");
        String userId = "1"; // User user = (User)request.getSession().getAttribute("currentUser"); user.getId();
        String quantity = request.getParameter("quantity");
        boolean isValid = true;
        if (quantity == null || Integer.parseInt(quantity) < 1) {
            request.getSession().setAttribute("DeviceDetailError", "Quantity must be at least 1.");
            isValid = false;
        }
        try {
            Device d = deviceDao.getDeviceById(deviceId);
            if (Integer.parseInt(quantity) > d.getStock()) {
                request.getSession().setAttribute("DeviceDetailError", "Quantity must be smaller than stock left.");
                isValid = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (isValid) {
            request.getSession().setAttribute("DeviceDetailError", null);
            try {
                orderDao.createOrder(total, userId, deviceId, quantity);
                response.sendRedirect("OrderHistoryServlet");
            } catch (SQLException ex) {
                Logger.getLogger(CreateOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("DeviceDetailServlet?deviceId="+deviceId);
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