
package uts.iot.model;


public class Device {

 

    
    private int id;
    private String name;
    private String type;
    private String unit;
    private double price;
    private int stock;

 

    public Device(int id, String name, String type, String unit, double price, int stock) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.price = price;
        this.stock = stock;
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

 

    public String getType() {
        return type;
    }

 

    public void setType(String type) {
        this.type = type;
    }

 

    public String getUnit() {
        return unit;
    }

 

    public void setUnit(String unit) {
        this.unit = unit;
    }

 

    public double getPrice() {
        return price;
    }

 

    public void setPrice(double price) {
        this.price = price;
    }

 

    public int getStock() {
        return stock;
    }

 

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
}