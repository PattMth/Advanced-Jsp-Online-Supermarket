package uts.iot.model.dao;

import uts.iot.model.Customer;
import uts.iot.model.Staff;
import uts.iot.model.Access_Log;
import uts.iot.model.User_Account;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.lang.String;

/*
    DBManager is the primary DAO class to interact with the database.
     Complete the existing methods of this classes to perform CRUD operations with the db.
 */
public class AccessDBManager {

    private Statement st;

    public AccessDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    /*
        The following functions are for the customer CRUD
     */
    //Find customer by email in the database
    public Customer findCustomer(String email) throws SQLException {
        //Find if the customer exists in the CUSTOMER TABLE
        String fetch = "select * from IOTBAYUSER.CUSTOMER where EMAIL='" + email + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            String customerEmail = rs.getString(4);
            if (customerEmail.equals(email)) {
                Integer customerId = rs.getInt(1);
                String customerName = rs.getString(2);
                String customerNumber = rs.getString(3);
                String customerAddress = rs.getString(5);
                Boolean customerReg = rs.getBoolean(6);
                return new Customer(customerId, customerName, customerNumber, customerEmail, customerAddress, customerReg);
            }
        }
        return null;
    }

    //Add a customer-data into the database
    public void addCustomer(String name, String number, String email, String address, Boolean register, String password, String dob,
            String gender, Boolean promo, int reward) throws SQLException {
        //Add into the CUSTOMER table
        st.executeUpdate("INSERT INTO IOTBAYUSER.CUSTOMER (NAME, CONTACTNUMBER, EMAIL, BILLINGADDRESS, ISREGISTERED) "
                + "VALUES ('" + name + "','" + number + "', '" + email + "', '" + address + "' , " + register + ")");
        //Add into the USER_ACCOUNT table
        st.executeUpdate("INSERT INTO IOTBAYUSER.USER_ACCOUNT (USERNAME, PASSWORD, DATEOFBIRTH, GENDER, PROMOTIONALNEWSLETTER, REWARDPOINTS) "
                + "VALUES ('" + email + "','" + password + "','" + dob + "','" + gender + "'," + promo + "," + reward + ")");

    }

    //update a customer details in the database
    public void updateCustomer(String name, String number, String email, String address, String password, String dob, String gender, Boolean promo)
            throws SQLException {
        //Update CUSTOMER table
        st.executeUpdate("UPDATE IOTBAYUSER.CUSTOMER SET NAME='" + name + "', BILLINGADDRESS= '" + address + "', CONTACTNUMBER='" + number
                + "' WHERE EMAIL='" + email + "'");
        //Update USER_ACCOUNT table
        st.executeUpdate("UPDATE IOTBAYUSER.USER_ACCOUNT SET PASSWORD= '" + password + "', DATEOFBIRTH= '" + dob + "', GENDER= '" + gender + "', PROMOTIONALNEWSLETTER= " + promo
                + " WHERE USERNAME='" + email + "'");
    }

    //delete a customer from the database
    public void deleteCustomer(String email) throws SQLException {
        //Delete from CUSTOMER table
        st.executeUpdate("DELETE FROM IOTBAYUSER.CUSTOMER WHERE EMAIL='" + email + "'");
        //Delete from USER_ACCOUNT table
        st.executeUpdate("DELETE FROM IOTBAYUSER.USER_ACCOUNT WHERE USERNAME='" + email + "'");
    }

    //check if customer exist in the database
    public boolean checkCustomer(String email) throws SQLException {
        String fetch = "select * from IOTBAYUSER.CUSTOMER where EMAIL ='" + email + "'";
        ResultSet rs = st.executeQuery(fetch);
        //Find if there is an email matching in the CUSTOMER table
        while (rs.next()) {
            String customerEmail = rs.getString(4);
            if (customerEmail.equals(email)) {
                return true;
            }
        }
        return false;
    }

    //get all customer rows in the CUSTOMER table
    public ArrayList<Customer> fetchCustomer() throws SQLException {
        String fetch = "select * from CUSTOMER";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Customer> temp = new ArrayList();

        while (rs.next()) {
            Integer customerId = rs.getInt(1);
            String customerEmail = rs.getString(4);
            String customerName = rs.getString(2);
            String customerNumber = rs.getString(3);
            String customerAddress = rs.getString(5);
            Boolean customerReg = rs.getBoolean(6);

            temp.add(new Customer(customerId, customerName, customerNumber, customerEmail, customerAddress, customerReg));
        }
        return temp;
    }

    /*
        The following functions are for staff CRUD
    */
    
    //Find staff by email and password in the database
    public Staff findStaff(String email) throws SQLException {
        //Find if the staff exists in the CUSTOMER TABLE
        String fetch = "select * from IOTBAYUSER.STAFF where EMAIL='" + email + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) 
        {
            String staffEmail = rs.getString(3);
            if (staffEmail.equals(email)) {
                Integer staffId = rs.getInt(1);
                String staffName = rs.getString(2);
                String staffNumber = rs.getString(4);
                String staffAddress = rs.getString(5);
                String staffType = rs.getString(6);
                String staffHistory = rs.getString(7);
                Integer userId = rs.getInt(8);
                return new Staff(staffId, staffName, staffNumber, staffEmail, staffAddress, staffType, staffHistory, userId);
            }
        }
        return null;
    }

    //Add a staff-data into the database
    public void addStaff(String name, String email, String number, String address, String type, String history,
            String password, String dob, String gender, Boolean promo, int points)
            throws SQLException {
        //Add staff in the USER_ACCOUNT table
        st.executeUpdate("INSERT INTO IOTBAYUSER.USER_ACCOUNT (USERNAME, PASSWORD, DATEOFBIRTH, GENDER, PROMOTIONALNEWSLETTER, REWARDPOINTS) "
                + "VALUES ('" + email + "','" + password + "','" + dob + "','" + gender + "'," + promo + "," + points + ")");

        //Add staff in the CUSTOMER table
        st.executeUpdate("INSERT INTO IOTBAYUSER.STAFF (NAME, EMAIL, PHONENUMBER, ADDRESS, STAFFTYPE, ACTIONHISTORY) "
                + "VALUES ('" + name + "', '"
                + email + "', '" + number + "', '"
                + address + "', '" + type + "', '" + history + "')");
    }

    //update a staff details in the database
    public void updateStaff(String name, String email, String number, String address, String type, String password, String dob, String gender) throws SQLException {
        //update STAFF table
        st.executeUpdate("UPDATE IOTBAYUSER.STAFF SET "
                + "NAME ='" + name
                + "',STAFFTYPE ='" + type
                + "',PHONENUMBER ='" + number
                + "',ADDRESS ='" + address
                + "' WHERE EMAIL='" + email + "'");
        //update USER_ACCOUNT table
        st.executeUpdate("UPDATE IOTBAYUSER.USER_ACCOUNT SET "
                + "PASSWORD ='" + password
                + "',DATEOFBIRTH ='" + dob
                + "',GENDER ='" + gender
                + "' WHERE USERNAME='" + email + "'");
    }

    //delete a staff from the database
    public void deleteStaff(String email) throws SQLException {
        //delete from STAFF table
        st.executeUpdate("DELETE FROM IOTBAYUSER.STAFF WHERE EMAIL='" + email + "'");
        //delete from USER_ACCOUNT table
        st.executeUpdate("DELETE FROM IOTBAYUSER.USER_ACCOUNT WHERE USERNAME='" + email + "'");

    }

    //get all rows in the STAFF table
    public ArrayList<Staff> fetchStaff() throws SQLException {
        String fetch = "select * from STAFF";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Staff> temp = new ArrayList();

        while (rs.next()) {
            Integer staffId = rs.getInt(1);
            String staffEmail = rs.getString(3);
            String staffName = rs.getString(2);
            String staffNumber = rs.getString(4);
            String staffAddress = rs.getString(5);
            String staffType = rs.getString(6);
            String staffHistory = rs.getString(7);
            int userId = rs.getInt(8);

            temp.add(new Staff(staffId, staffName, staffEmail, staffNumber, staffAddress, staffType, staffHistory, userId));
        }
        return temp;
    }

    //Check if staff exist in the database
    public boolean checkStaff(String email) throws SQLException {
        String fetch = "select * from IOTBAYUSER.STAFF where EMAIL ='" + email + "'";
        ResultSet rs = st.executeQuery(fetch);
        //Find if there is an email matching in the STAFF table
        while (rs.next()) {
            String staffEmail = rs.getString(3);
            if (staffEmail.equals(email)) {
                return true;
            }
        }
        return false;
    }

    /*
        The following functions are for the access log CRUD
        Since a user cannot delete or update any access logs
        no code is created for update and deleting logs
    */
    
    //Find if there is an Id matching in the Access_Log table
    public boolean checkLog(int id) throws SQLException {
        String fetch = "select * from IOTBAYUSER.ACCESS_LOG where USERACCOUNTID =" + id;
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            int logId = rs.getInt(1);
            if (logId == id) {
                return true;
            }
        }
        return false;
    }

    //Find log(s) that corresponds to the userId in the ACCESS_LOG table
    public Access_Log findLogId(int userId) throws SQLException {
        String fetch = "select * from IOTBAYUSER.ACCESS_LOG where USERACCOUNTID=" + userId;
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            int lognum = rs.getInt(1);
            if (lognum == userId) {
                String logDate = rs.getString(3);
                String logTime = rs.getString(4);
                String logAction = rs.getString(5);
                return new Access_Log(lognum, userId, logDate, logTime, logAction);
            }
        }
        return null;
    }
    //Find log(s) that corresponds to the date in the ACCESS_LOG table
    public Access_Log findLogDate(String date) throws SQLException {
 
        String fetch = "select * from IOTBAYUSER.ACCESS_LOG where DATE='" + date + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            String logDate = rs.getString(3);
            if (logDate.equals(date)) {
                int userId = rs.getInt(2);
                String logTime = rs.getString(4);
                Integer logId = rs.getInt(1);
                String logAction = rs.getString(5);
                return new Access_Log(logId, userId, logDate, logTime, logAction);
            }
        }
        return null;
    }

    //Add a log into the database
    public void addLog(int id, String action) throws SQLException {

        LocalDate date1 = LocalDate.now();                                      //Get local date
        LocalTime time1 = LocalTime.now();                                      //Get local time

        String date = date1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));    //Format date to day/month/year
        String time = time1.format(DateTimeFormatter.ofPattern("hh:mm a"));       //Format time to 12 hour clock

        st.executeUpdate("INSERT INTO IOTBAYUSER.ACCESS_LOG (USERACCOUNTID, DATE, TIME, ACTION) "
                + "VALUES (" + id + ",'" + date + "','" + time + "','" + action + "')");                           //Insert current time

    }

    //get all rows in the ACCESS_LOG table
    public ArrayList<Access_Log> fetchAllLog() throws SQLException {
        String fetch = "select * from ACCESS_LOG";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Access_Log> temp = new ArrayList();

        while (rs.next()) {
            Integer logId = rs.getInt(1);
            String logDate = rs.getString(3);
            String logTime = rs.getString(4);
            String logAction = rs.getString(5);
            int userId = rs.getInt(2);

            temp.add(new Access_Log(logId, userId, logDate, logTime, logAction));

        }
        return temp;
    }

    //Get all log(s) that corresponds to the userId in the Access_Log Table
    public ArrayList<Access_Log> fetchLog(int id) throws SQLException {
        String fetch = "select * from ACCESS_LOG";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Access_Log> temp = new ArrayList();

        while (rs.next()) {
            Integer logId = rs.getInt(1);
            String logDate = rs.getString(3);
            String logTime = rs.getString(4);
            String logAction = rs.getString(5);
            int userId = rs.getInt(2);
            
            if (userId == id) {
                temp.add(new Access_Log(logId, userId, logDate, logTime, logAction));
            }
        }
        return temp;
    }
    
    //Get all log(s) that corresponds to the date and userId in the Access_Log Table
    public ArrayList<Access_Log> fetchLogDate(String date, int id) throws SQLException {
        String fetch = "select * from ACCESS_LOG";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Access_Log> temp = new ArrayList();

        while (rs.next()) {
            Integer logId = rs.getInt(1);
            String logDate = rs.getString(3);
            String logTime = rs.getString(4);
            String logAction = rs.getString(5);
            int userId = rs.getInt(2);

            if (userId == id && date.equals(logDate)) {
                temp.add(new Access_Log(logId, userId, logDate, logTime, logAction));
            }
        }
        return temp;
    }
    //Get all log(s) that corresponds to the date in the Access_Log Table
    public ArrayList<Access_Log> fetchLogOnlyDate(String date) throws SQLException {
        String fetch = "select * from ACCESS_LOG";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Access_Log> temp = new ArrayList();

        while (rs.next()) {
            Integer logId = rs.getInt(1);
            String logDate = rs.getString(3);
            String logTime = rs.getString(4);
            String logAction = rs.getString(5);
            int userId = rs.getInt(2);

            if (date.equals(logDate)) {
                temp.add(new Access_Log(logId, userId, logDate, logTime, logAction));
            }
        }
        return temp;
    }

    /*
        User_Account account functions
    */
    
    //Find if the user exists in the USER_ACCOUNT table using email and password
    public User_Account findUser(String username, String password) throws SQLException {       
        String fetch = "SELECT * FROM IOTBAYUSER.USER_ACCOUNT WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            String userName = rs.getString(2);
            String userPassword = rs.getString(3);
            if (userName.equals(username) && userPassword.equals(password)) {
                Integer userId = rs.getInt(1);
                String userDob = rs.getString(4);
                String userGender = rs.getString(5);
                Boolean userNews = rs.getBoolean(6);
                int userPoints = rs.getInt(7);
                return new User_Account(userId, userName, userPassword, userDob, userGender, userNews, userPoints);
            }
        }
        return null;
    }
    
    //Find if the user exists in the USER_ACCOUNT table using email
    public User_Account findUserEmail(String username) throws SQLException {       
        String fetch = "SELECT * FROM IOTBAYUSER.USER_ACCOUNT WHERE USERNAME='" + username + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            String userName = rs.getString(2);
            if (userName.equals(username)) {
                Integer userId = rs.getInt(1);
                String userDob = rs.getString(4);
                String userGender = rs.getString(5);
                Boolean userNews = rs.getBoolean(6);
                int userPoints = rs.getInt(7);
                String userPassword = rs.getString(3);
                return new User_Account(userId, userName, userPassword, userDob, userGender, userNews, userPoints);
            }
        }
        return null;
    }

}