package org.example.models;

public class CustomService extends Service {

    private final String extras;



    public CustomService(Integer id, String name, double pricePerKm, String extras) {
        super(id, name, pricePerKm); // Call the constructor of BasicService
        this.extras = extras;
    }

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
