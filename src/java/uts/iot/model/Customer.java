package uts.iot.model;

import java.io.Serializable;

public class Customer implements Serializable {

    private int id;
    private String name;
    private String number;
    private String email;
    private String address;
    private boolean register;

    public Customer(int id, String name, String number, String email, String address, boolean register) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.register = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

}