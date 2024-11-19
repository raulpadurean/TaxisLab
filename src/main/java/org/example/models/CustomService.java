package org.example.models;

public class CustomService extends Service {

    private final String extras;

    public CustomService(int id, String name, double pricePerKm, String extras) {
        super(id, name, pricePerKm);
        if (extras == null || extras.trim().isEmpty()) {
            throw new IllegalArgumentException("Extras cannot be null or empty.");
        }
        this.extras = extras;
    }

    // Getters and setters for extras
    public String getExtras() {
        return extras;
    }

    @Override
    public String getServiceType() {
        return "Custom Service";
    }

    @Override
    public String toString() {
        return String.format("CustomService { id=%d, name='%s', pricePerKm=%.2f, extras='%s', type='%s' }",
                getId(), getName(), getPricePerKm(), extras, getServiceType());
    }
}
