package org.example.models;

/**
 * Represents a custom service with additional features. This class extends the {@link Service}
 * class and adds a new field for extra services. It also provides methods to access the service type
 * and convert the service to a string or parse it from a string.
 */
public class CustomService extends Service {

    private String extras;

    /**
     * Constructs a new CustomService object with the specified parameters.
     *
     * @param id         The unique identifier for the service.
     * @param name       The name of the service.
     * @param pricePerKm The price per kilometer for the service.
     * @param extras     A description of additional features or services for the custom service.
     * @throws IllegalArgumentException If the extras parameter is null or empty.
     */
    public CustomService(int id, String name, double pricePerKm, String extras) {
        super(id, name, pricePerKm, ServiceType.CUSTOM);
        if (extras == null || extras.trim().isEmpty()) {
            throw new IllegalArgumentException("Extras cannot be null or empty.");
        }
        this.extras = extras;
    }


    public CustomService() {

    }

    /**
     * Retrieves the additional features or services associated with the custom service.
     *
     * @return The extras (additional features) for the custom service.
     */
    public String getExtras() {
        return extras;
    }

    /**
     * Retrieves the service type. In this case, it returns "Custom".
     *
     * @return The string "Custom", representing the type of service.
     */
    @Override
    public ServiceType getType() {
        return this.type;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    /**
     * Returns a string representation of the CustomService object in the format:
     * "Custom:id;name;pricePerKm;extras".
     *
     * @return A string representation of the CustomService instance.
     */
    @Override
    public String toString() {
        return getType() + ":" + this.id + ";" + this.name + ";" + this.pricePerKm + ";" + this.extras;
    }

    /**
     * Parses a string representation of a CustomService object and returns a new
     * CustomService instance. The expected string format is:
     * "id;name;pricePerKm;extras".
     *
     * @param stringData The string representation of the CustomService object.
     * @return A new CustomService object created from the string data.
     * @throws NumberFormatException If the pricePerKm or id is not a valid number.
     * @throws IllegalArgumentException If the extras are invalid or the string is malformed.
     */
    public static CustomService parse(String stringData) {
        String[] data = stringData.split(";");
        return new CustomService(
                Integer.parseInt(data[0]),
                data[1],
                Double.parseDouble(data[2]),
                data[3]
        );
    }
}
