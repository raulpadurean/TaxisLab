package org.example.models;

public class BasicService extends Service {

    public BasicService(Integer id, String name, double pricePerKm) {
        super(id, name, pricePerKm);
    }

    @Override
    public String getServiceType() {
        return "Basic Service";
    }

    @Override
    public String toString() {
        return String.format("BasicService { id=%d, name='%s', pricePerKm=%.2f, type='%s' }",
                getId(), getName(), getPricePerKm(), getServiceType());
    }
}
