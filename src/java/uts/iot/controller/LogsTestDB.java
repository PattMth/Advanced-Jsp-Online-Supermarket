package uts.iot.controller;

import uts.iot.model.dao.AccessDBManager;
import uts.iot.model.Access_Log;
import java.sql.*;

import java.util.*;
import java.util.logging.*;
import uts.iot.model.dao.DBConnector;


public class LogsTestDB {

    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private AccessDBManager db;
    
    public static void main(String[] args) throws SQLException {
        (new LogsTestDB()).runQueries();
        
    }
    
    public LogsTestDB()
    {
        try
        {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new AccessDBManager(conn);
        }catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(LogsTestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private char readChoice()
    {
        System.out.print("Operation CRS or * to exit: ");
        return in.nextLine().charAt(0);
    }
    
    private void runQueries() throws SQLException
    {
        char c;
        
        while ((c = readChoice()) != '*'){
            switch (c){
               case 'C':
                    testAdd();
                    break;
                case 'R':
                    testRead();
                    break;
                case 'S':
                    showAll();
                    break;
                default:
                    System.out.println("Unknown Command");
                    
            }
            
        }
    }
    
    
    
    private void testRead() throws SQLException{
        System.out.print("Log date:");
        String date = in.nextLine();
        Access_Log logs = db.findLogDate(date);
        
        if(logs != null){
            System.out.println("log  exists in the database.");
        }else{
            System.out.println("log does not exist in the database.");
        }
    }
    
    private void showAll(){
        
        System.out.print("Log date:");
        String date = in.nextLine();
        System.out.print("Log id:");
        int  id = in.nextInt();
        try{
            ArrayList<Access_Log> logs = db.fetchLogDate(date, id);
            System.out.println("Log Table:");
            logs.stream().forEach((log) ->{
                System.out.printf("%-30s %-30s %-30s %-30s \n", 
                 log.getAccessid(), log.getAccid(), log.getDate(), log.getTime());
            });
            System.out.println();        
        }catch (SQLException ex) {
            Logger.getLogger(LogsTestDB.class.getName()).log(Level.SEVERE,null, ex);
        }      
    
    }

    
    private void testAdd(){
        
        System.out.print("user id: ");
        int id = in.nextInt();
        
       
       

        try{
            db.addLog(id, "LOGIN");
        } catch (SQLException ex){
            Logger.getLogger(CustomerTestDB.class.getName()).log(Level.SEVERE,null, ex);
        }
        System.out.println("Log is added to the database.");
        
    }
    
}