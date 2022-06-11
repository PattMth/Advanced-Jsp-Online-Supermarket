/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.iot.model;

/**
 *
 * @author Symon
 */
public class Shipment {
    private String courierName;
    private String ShipmentStatus;
    private String trackingNumber;
    private String shipmentDate;
    private int shipmentDetailsID; // Foriegn Key
    private int orderID; // Foreign key
    private int shipmentID; // Primary Key

    public Shipment(String courierName, String ShipmentStatus, String trackingNumber, String shipmentDate, int shipmentDetailsID ,int orderID) {
        this.courierName = courierName;
        this.ShipmentStatus = ShipmentStatus;
        this.trackingNumber = trackingNumber;
        this.shipmentDate = shipmentDate;
        this.shipmentDetailsID = shipmentDetailsID;
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }
    
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    
    public int getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }

    
    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getShipmentStatus() {
        return ShipmentStatus;
    }

    public void setShipmentStatus(String ShipmentStatus) {
        this.ShipmentStatus = ShipmentStatus;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public int getShipmentDetailsID() {
        return shipmentDetailsID;
    }

    public void setShipmentDetailsID(int shipmentDetailsID) {
        this.shipmentDetailsID = shipmentDetailsID;
    }

    
    

    
    
    
}