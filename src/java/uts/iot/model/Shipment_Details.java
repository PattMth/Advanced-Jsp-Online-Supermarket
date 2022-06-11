/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.iot.model;

/**
 *
 * @author  Symon
 */
public class Shipment_Details {
    private String streetAddress;
    private String suburb;
    private int postcode;
    private String state;
    private int shipmentDetailsID;

    public Shipment_Details(String streetAddress, String suburb, int postcode, String state) {
        this.streetAddress = streetAddress;
        this.suburb = suburb;
        this.postcode = postcode;
        this.state = state;
    }

    public int getShipmentDetailsID() {
        return shipmentDetailsID;
    }

    public void setShipmentDetailsID(int shipmentDetailsID) {
        this.shipmentDetailsID = shipmentDetailsID;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String addressForm(int shipmentDetailsID) {
        String fullform = streetAddress + ", " + suburb + ", " + postcode + ", " + state;
        return fullform;
    }
}