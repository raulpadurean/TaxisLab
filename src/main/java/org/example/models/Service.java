package org.example.models;

public abstract class Service implements HasId {

    protected Integer id;
    protected String name;
    protected double pricePerKm;

    public Service(Integer id, String name, double pricePerKm) {
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


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    // Abstract method to be implemented by subclasses
    public abstract String getServiceType();

    public static Service parse(String serviceData) {
        String[] parts = serviceData.split(":");
        String type = parts[0];

        return switch (type) {
            case "Basic" -> BasicService.parse(parts[1]);
            case "Custom" -> CustomService.parse(parts[1]);
            default -> throw new IllegalArgumentException("Unknown service type: " + type);
        };
    }
}
