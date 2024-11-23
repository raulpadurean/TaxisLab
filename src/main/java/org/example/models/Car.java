package org.example.models;

public class Car implements HasId {
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return id + "," + brand + "," + model + "," + plateNr + "," + driver;
    }

    public static Car parse(String line) {
        String[] fields = line.split(",");
        return new Car(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3],
                Driver.parse(fields[4])
        );
    }
}
