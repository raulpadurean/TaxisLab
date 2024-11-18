package org.example.models;

public class Car implements HasId {
    private int id;
    private String brand;
    private String model;
    private String plateNr;
    private int driverId;

    public Car(int id, String brand, String model, String plateNr, int driverId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plateNr = plateNr;
        this.driverId = driverId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", plateNr='" + plateNr + '\'' +
                ", driverId=" + driverId +
                '}';
    }
}
