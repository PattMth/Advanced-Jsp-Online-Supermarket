package uts.iot.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import uts.iot.model.Supplier;

public class SupplierDBManager {

    private Statement st;

    public SupplierDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    //Create
    public void addSupplier(String name, String type, int number, String email, String address, boolean status) throws SQLException {
        st.executeUpdate("INSERT INTO IOTBAYUSER.SUPPLIERS (SUPPLIERNAME, COMPANYTYPE, CONTACTNUMBER, EMAIL, ADDRESS, STATUS) "
                + "VALUES ('" + name + "','" + type + "', " + number + ", '" + email + "' , '" + address + "', " + status + ")");
    }

    //Read
    public Supplier findSupplierByName(String name) throws SQLException {
        String query = "SELECT * FROM IOTBAYUSER.SUPPLIERS WHERE SUPPLIERNAME = '" + name + "'";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String supplierName = rs.getString(2);

            if (name.equals(supplierName)) {
                int id = rs.getInt(1);
                String companyType = rs.getString(3);
                int contactNumber = rs.getInt(4);
                String email = rs.getString(5);
                String address = rs.getString(6);
                boolean status = rs.getBoolean(7);

                return new Supplier(id, supplierName, companyType, contactNumber, email, address, status);
            }
        }
        return null;
    }

    public ArrayList<Supplier> filterSuppliers(String name, String type) throws SQLException {
        String query = "SELECT * FROM IOTBAYUSER.SUPPLIERS WHERE COMPANYTYPE = '" + type + "' OR SUPPLIERNAME= '" + name + "'";
        ResultSet rs = st.executeQuery(query);
        ArrayList<Supplier> suppliers = new ArrayList();

        while (rs.next()) {
            int supplierID = rs.getInt(1);
            String supplierName = rs.getString(2);
            String companyType = rs.getString(3);
            int contactNumber = rs.getInt(4);
            String email = rs.getString(5);
            String address = rs.getString(6);
            boolean status = rs.getBoolean(7);

            suppliers.add(new Supplier(supplierID, supplierName, companyType, contactNumber, email, address, status));
        }
        return suppliers;
    }

    //Update
    public void updateSupplier(int ID, String name, String type, int number, String email, String address, boolean status) throws SQLException {
        st.executeUpdate("UPDATE IOTBAYUSER.SUPPLIERS SET SUPPLIERNAME='" + name + "', COMPANYTYPE= '" + type + "', CONTACTNUMBER= " + number
                + ", EMAIL= '" + email + "', ADDRESS= '" + address + "', STATUS= " + status + " WHERE SUPPLIERID= " + ID);
    }

    //Delete
    public void deleteSupplier(int id) throws SQLException {
        st.executeUpdate("DELETE FROM IOTBAYUSER.SUPPLIERS WHERE SUPPLIERID=" + id);
    }

    //Fetch all
    public ArrayList<Supplier> fetchAll() throws SQLException {
        String query = "select * from SUPPLIERS";
        ResultSet rs = st.executeQuery(query);
        ArrayList<Supplier> suppliers = new ArrayList();

        while (rs.next()) {
            int supplierID = rs.getInt(1);
            String supplierName = rs.getString(2);
            String companyType = rs.getString(3);
            int contactNumber = rs.getInt(4);
            String email = rs.getString(5);
            String address = rs.getString(6);
            boolean status = rs.getBoolean(7);

            suppliers.add(new Supplier(supplierID, supplierName, companyType, contactNumber, email, address, status));
        }
        return suppliers;
    }

}
