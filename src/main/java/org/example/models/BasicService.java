package org.example.models;

public class BasicService {

    private int id;
    private String name;
    private double pricePerKm;

    public BasicService(int id, String name, double pricePerKm) {
        this.id = id;
        this.name = name;
        this.pricePerKm = pricePerKm;
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

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    @Override
    public String toString() {
        return "BasicService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pricePerKm=" + pricePerKm +
                '}';
    }
}
