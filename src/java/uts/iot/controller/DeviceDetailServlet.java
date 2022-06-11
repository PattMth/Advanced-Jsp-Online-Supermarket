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
public class DeviceDetailServlet extends HttpServlet {
    private IoTDeviceDao deviceDao;

 

    public DeviceDetailServlet() throws SQLException, ClassNotFoundException {
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
                request.setAttribute("currentDevice", device);
            } catch (SQLException ex) {
                Logger.getLogger(EditDeviceServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("deviceDetail.jsp?deviceId="+deviceId).forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
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