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
        return "Custom";
    }

    @Override
    public String toString() {
        return  getServiceType() + ":" + this.id + ";" + this.name + ";" + this.pricePerKm + ";" + this.extras;
    }

    public static CustomService parse(String stringData) {
        String[] data=stringData.split(";");
        return new CustomService(
                Integer.parseInt(data[0]),
                data[1],
                Integer.parseInt(data[2]),
                data[3]
        );
    }
}
