/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.iot.model.dao;

 

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uts.iot.model.Customer;
import uts.iot.model.Device;


public class IoTDeviceDao {

 

    private Statement st;

 

    public IoTDeviceDao(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

 

    public List<Device> findAll() throws SQLException {
        String fetch = "select * from IOTBAY.DEVICE";
        ResultSet rs = st.executeQuery(fetch);
        return processResultList(rs);
    }

 

    public List<Device> searchDevicesByNameAndID(String id, String name) throws SQLException {
        String fetch = "select * from DEVICE where id>-1";
        if(id != null && !id.isEmpty()) {
            fetch = fetch + " and id=" +id;
        }
        if(name != null && !name.isEmpty()) {
            fetch = fetch + " and name like '%" +name + "%'";
        }
        ResultSet rs = st.executeQuery(fetch);
        return processResultList(rs);
    }
    
    public Device getDeviceById(String id) throws SQLException {
        String fetch = "select * from DEVICE where id=" + id;
        ResultSet rs = st.executeQuery(fetch);
        return processResultList(rs).get(0);
    }
    
    
    private List<Device> processResultList(ResultSet rs) throws SQLException{
        List<Device> deviceList = new ArrayList();
        while (rs.next()) //goes through every row in the CUSTOMER table
        {
            Integer deviceId = rs.getInt(1);
            String deviceName = rs.getString(2);
            String deviceType = rs.getString(3);
            String deviceUnit = rs.getString(4);
            Double devicePrice = rs.getDouble(5);
            Integer deviceStock = rs.getInt(6);
            deviceList.add(new Device(deviceId, deviceName, deviceType, deviceUnit, devicePrice, deviceStock));
        }
        return deviceList;
    }

 

    public void save(Device device) throws SQLException{
        if(device.getId() > 0) {
            updateDevice(device.getId(), device.getName(), device.getType(), device.getUnit(), device.getPrice(), device.getStock());
        } else {
            addDevice(device.getId(), device.getName(), device.getType(), device.getUnit(), device.getPrice(), device.getStock());
        }
    }
    
    public void addDevice(int id, String name, String type, String unit, double price, int stock) throws SQLException {
        st.executeUpdate("INSERT INTO DEVICE(\"NAME\", \"TYPE\", \"UNIT\", \"PRICE\", \"STOCK\") " + "VALUES ('" + name + "', '" + type + "', '" + unit + "', " + price + ", " + stock + ")");
    }

 

    public void updateDevice(int id, String name, String type, String unit, double price, int stock) throws SQLException {
        st.executeUpdate("UPDATE DEVICE SET NAME='" + name + "', TYPE='" + type + "',UNIT= '" + unit + "',PRICE= " + price + ", STOCK=" + stock + " WHERE ID=" + id);
    }
    

 

    public void deleteDevice(String id) throws SQLException {
        st.executeUpdate("DELETE FROM DEVICE WHERE id=" + id);
    }

 

    public ArrayList<Device> fectDevice() throws SQLException {
        String fetch = "Select * from DEVICE";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Device> temp = new ArrayList();

 

        while (rs.next()) {
            Integer deviceId = rs.getInt(1);
            String deviceName = rs.getString(2);
            String deviceType = rs.getString(3);
            String deviceUnit = rs.getString(4);
            Double devicePrice = rs.getDouble(5);
            Integer deviceStock = rs.getInt(6);
            temp.add(new Device(deviceId, deviceName, deviceType, deviceUnit, devicePrice, deviceStock));
        }
        return temp;
    }

 

    public boolean checkDevice(int id, String name) throws SQLException {
        String fetch = "select * from IOTBAY.DEVICE where NAME = '" + name + "' and ID=" + id;
        ResultSet rs = st.executeQuery(fetch);

 

        while (rs.next()) {
            String deviceName = rs.getString(2);
            Integer deviceId = rs.getInt(1);
            if (deviceName.equals(name) && deviceId.equals(id)) {
                return true;
            }
        }
        return false;
    }

 

}