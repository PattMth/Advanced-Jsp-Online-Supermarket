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

 

/**
 *
 * @author apple1
 */
public class EditDeviceServlet extends HttpServlet {

 

    private IoTDeviceDao deviceDao;

 

    public EditDeviceServlet() throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();
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
        String deviceId = request.getParameter("deviceId");
        if (deviceId != null && !deviceId.isEmpty()) {
            try {
                Device device = deviceDao.getDeviceById(deviceId);
                System.out.println("============Name:"+device.getName());
                request.setAttribute("currentDevice", device);
            } catch (SQLException ex) {
                Logger.getLogger(EditDeviceServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("editDevice.jsp?deviceId="+deviceId).forward(request, response);
        } else {
            request.getRequestDispatcher("createDevice.jsp").forward(request, response);
        }
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
        int id = request.getParameter("deviceId").isEmpty() ? 0 : Integer.parseInt(request.getParameter("deviceId"));
        String name = request.getParameter("deviceName");
        String type = request.getParameter("deviceType");
        String unit = request.getParameter("deviceUnit");
        double price = request.getParameter("devicePrice").isEmpty() ? 0 : Double.parseDouble(request.getParameter("devicePrice"));
        int stock = request.getParameter("deviceStock").isEmpty() ? 0 : Integer.parseInt(request.getParameter("deviceStock"));
        Device device = new Device(id, name, type, unit, price, stock);
        try {
            deviceDao.save(device);
        } catch (SQLException ex) {
            Logger.getLogger(EditDeviceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("devices");
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

 

    private boolean isEmpty(String s) {
        return s==null || s.isEmpty();
    }
}