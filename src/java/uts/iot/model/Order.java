package uts.iot.model;

public class Order {
    private int id;
    private String date;
    private double total;
    private String status;
    private String userId;
    private int deviceId;
    private int quantity;
    private String notes;

    public Order(int id, String date, double total, String status, String userId, int deviceId, int quantity, String notes) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.status = status;
        this.userId = userId;
        this.deviceId = deviceId;
        this.quantity = quantity;
        this.notes = notes;
    }

    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}