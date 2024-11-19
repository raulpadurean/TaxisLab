package org.example.models;

public class Car {
    private int id;
    private String brand;
    private String model;
    private String plateNr;
    private Driver driver;

    public Car(int id, String brand, String model, String plateNr, Driver driver) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plateNr = plateNr;
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNr() {
        return plateNr;
    }

    public void setPlateNr(String plateNr) {
        this.plateNr = plateNr;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", plateNr='" + plateNr + '\'' +
                ", driverId=" + driver +
                '}';
    }
}
