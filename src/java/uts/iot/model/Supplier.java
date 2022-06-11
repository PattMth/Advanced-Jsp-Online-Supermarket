package uts.iot.model;

import java.io.Serializable;

public class Supplier implements Serializable {

    private int supplierID;
    private String supplierName;
    private String companyType;
    private int contactNumber;
    private String email;
    private String address;
    private boolean status;

    public Supplier(int supplierID, String supplierName, String companyType, int contactNumber, String email, String address, boolean status) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.companyType = companyType;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.status = status;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
