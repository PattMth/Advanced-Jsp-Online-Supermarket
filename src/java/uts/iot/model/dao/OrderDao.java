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
import uts.iot.model.Order;

public class OrderDao {

    private Statement st;

    public OrderDao(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public List<Order> findOrderByUserId() throws SQLException {
        String fetch = "select * from \"ORDER\"";
        ResultSet rs = st.executeQuery(fetch);
        return processResultList(rs);
    }
    
    public List<Order> searchOrder(String id, String from, String to) throws SQLException {
        String fetch = "select * from \"ORDER\" where id>0";
        if(id != null && !id.equals("")) {
            fetch = fetch + " and id="+id;
        }
        if(from != null && !from.equals("")) {
            fetch = fetch + " and \"DATE\">=TIMESTAMP('"+from+" 00:00:00')";
        }
        if(to != null && !to.equals("")) {
            fetch = fetch + " and \"DATE\"<=TIMESTAMP('"+to+" 00:00:00')";
        }
        ResultSet rs = st.executeQuery(fetch);
        return processResultList(rs);
    }
    
    public Order findOrderById(String id) throws SQLException {
        String fetch = "select * from \"ORDER\" where id=" + id;
        ResultSet rs = st.executeQuery(fetch);
        return processResultList(rs).get(0);
    }
    
    public void deleteOrder(String id) throws SQLException {
        String sql = "delete from \"ORDER\" where id=" + id;
        st.executeUpdate(sql);
    }
    
    public void createOrder(String total, String userId, String deviceId, String quantity) throws SQLException {
        String sql = "INSERT INTO \"ORDER\" (\"TOTAL\", \"STATUS\", \"USERID\", \"DEVICEID\", \"QUANTITY\") VALUES("+total+", 'Unpaid', '"+userId+"', "+deviceId+", "+quantity+")";
        st.executeUpdate(sql);
    }
    
    public void updateOrder(String id,String notes) throws SQLException {
        String sql = "update \"ORDER\" set notes ='"+notes.replace("'", "''")+"' where id=" + id;
        st.executeUpdate(sql);
    }

    private List<Order> processResultList(ResultSet rs) throws SQLException{
        List<Order> orderList = new ArrayList();
        while (rs.next()) //goes through every row in the CUSTOMER table
        {
            orderList.add(new Order(rs.getInt(1), rs.getString(3), rs.getDouble(2), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8)));
        }
        return orderList;
    }
}