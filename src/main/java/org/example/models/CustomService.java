package org.example.models;

public class CustomService extends BasicService {

    private String extras;

    public CustomService(Integer id, String name, double pricePerKm, String extras) {
        super(id, name, pricePerKm); // Call the constructor of BasicService
        this.extras = extras;
    }

    // Getters and setters for extras
    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return "CustomService{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", pricePerKm=" + getPricePerKm() +
                ", extras='" + extras + '\'' +
                '}';
    }
}
