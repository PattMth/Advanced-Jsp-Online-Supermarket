package uts.iot.model;

import java.io.Serializable;

public class Staff implements Serializable {

    private int staffid;
    private String name;
    private String email;
    private String number;
    private String address;
    private String type;
    private String history;
    private int userAccountID;

    public Staff(int staffid, String name, String email, String number, String address, String type, String history, int userAccountID) {
        this.staffid = staffid;
        this.name = name;
        this.email = email;
        this.number = number;
        this.address = address;
        this.type = type;
        this.history = history;
        this.userAccountID = userAccountID;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public int getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(int userAccountID) {
        this.userAccountID = userAccountID;
    }

}