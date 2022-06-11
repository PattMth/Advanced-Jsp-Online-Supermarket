package uts.iot.controller;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import uts.iot.model.Supplier;
import uts.iot.model.dao.DBConnector;
import uts.iot.model.dao.SupplierDBManager;

public class SupplierTestDB {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            SupplierDBManager db = new SupplierDBManager(conn);
            
            
            //test addSupplier
            System.out.println("Testing addSupplier()");
            System.out.print("Supplier name: ");
            String type = in.nextLine();

            System.out.print("Company type: ");
            String name = in.nextLine();

            System.out.print("Contact number: ");
            int number = Integer.parseInt(in.nextLine());

            System.out.print("Address: ");
            String address = in.nextLine();

            System.out.print("Email: ");
            String email = in.nextLine();

            System.out.print("Status: ");
            boolean status = Boolean.parseBoolean(in.nextLine());

            db.addSupplier(name, type, number, email, address, status);
            System.out.println("Supplier has been added to the database.");
 
            
            //test findSupplierByName
            System.out.println("Testing findSupplierByName()");
            System.out.print("Enter name: ");
            name = in.nextLine();
            
            Supplier supplier = db.findSupplierByName(name);
            System.out.println(supplier.getSupplierName() + "has been found.");
            
           
            //test filterSuppliers
            System.out.println("Testing filterSuppliers()");
            System.out.print("Enter name: ");
            name = in.nextLine();
            
            System.out.print("Enter type: ");
            type = in.nextLine();
            
            ArrayList<Supplier> suppliers = db.filterSuppliers(name, type);
            for(Supplier s: suppliers){
                System.out.println("Found" + s.getSupplierName());
            }
            
            
            //test updateSupplier
            System.out.println("Testing updateSupplier()");
            System.out.print("New supplier name: ");
            type = in.nextLine();

            System.out.print("New company type: ");
            name = in.nextLine();

            System.out.print("New contact number: ");
            number = Integer.parseInt(in.nextLine());

            System.out.print("New address: ");
            address = in.nextLine();

            System.out.print("New email: ");
            email = in.nextLine();

            System.out.print("New status: ");
            status = Boolean.parseBoolean(in.nextLine());
            
            db.updateSupplier(1, name, type, number, email, address, status);
            System.out.println("Supplier has been updated.");
            
            
            //test deleteSupplier
            System.out.println("Testing deleteSupplier()");
            System.out.println("Deleting fifth supplier...");
            db.deleteSupplier(5);
            System.out.println("Supplier has been deleted.");
            
            
            //test fetchAll
            System.out.println("Testing fetchAll()");
            suppliers = db.fetchAll();
            for(Supplier s: suppliers){
                System.out.println("Found" + s.getSupplierName());
            }
            
            System.out.println("Test complete.");
            
            
            connector.closeConnection();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SupplierTestDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

}
