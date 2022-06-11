/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.iot.controller;

 

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class DeviceListController extends HttpServlet {

 

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private IoTDeviceDao deviceDao;

 

    public DeviceListController() throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();
        deviceDao = new IoTDeviceDao(connector.openConnection());
    }

 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        List<Device> devices = null;
        String keyword = request.getParameter("keyword");
        String id = request.getParameter("deviceId");
        try {
            devices = deviceDao.searchDevicesByNameAndID(id, keyword);
        } catch (SQLException ex) {
            Logger.getLogger(DeviceListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("name", "Qianhui");
        request.setAttribute("devices", devices);
        request.getRequestDispatcher("/IoTDevices.jsp").include(request, response);
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
        List<Device> devices = null;
        try {
            deviceDao.deleteDevice(deviceId);
            devices = deviceDao.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(DeviceListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("devices", devices);
        request.getRequestDispatcher("/IoTDevices.jsp").include(request, response);
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