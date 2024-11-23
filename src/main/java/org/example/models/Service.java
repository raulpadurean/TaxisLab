package org.example.models;

public abstract class Service implements HasId {

    protected final Integer id;
    protected final String name;
    protected final double pricePerKm;

    public Service(int id, String name, double pricePerKm) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (pricePerKm < 0) {
            throw new IllegalArgumentException("Price per km cannot be negative.");
        }
        this.id = id;
        this.name = name;
        this.pricePerKm = pricePerKm;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    // Abstract method to be implemented by subclasses
    public abstract String getServiceType();
}
