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
        return this.id + "," + this.name + "," + this.pricePerKm;
    }

    public BasicService parse(String stringData) {
        String[] data=stringData.split(",");
        return new BasicService(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]));
    }
}
