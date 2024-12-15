package org.example.models;

/**
 * Represents a basic service offering that extends the {@link Service} class.
 * This class provides a specific implementation of a service type called "Basic" and includes
 * a price per kilometer.
 */
public class BasicService extends Service {

    /**
     * Constructs a new BasicService object with the specified ID, name, and price per kilometer.
     *
     * @param id          The unique identifier for the service.
     * @param name        The name of the service.
     * @param pricePerKm  The price per kilometer for the service.
     */
    public BasicService(Integer id, String name, double pricePerKm) {
        super(id, name, pricePerKm, ServiceType.BASIC);
    }

    public BasicService() {

    }

    /**
     * Returns the type of the service, which is "Basic" for this implementation.
     *
     * @return A string representing the type of service ("Basic").
     */
    @Override
    public ServiceType getType() {
        return this.type;
    }

    /**
     * Returns a string representation of the BasicService object.
     * The format of the string is:
     * "Basic:<id>;<name>;<pricePerKm>"
     *
     * @return A string representing the BasicService object.
     */
    @Override
    public String toString() {
        return getType() + ":" + this.id + ";" + this.name + ";" + this.pricePerKm;
    }

    /**
     * Parses a string representation of a BasicService object and creates a new instance.
     * The string format is expected to be:
     * "Basic:<id>;<name>;<pricePerKm>"
     *
     * @param stringData The string representation of the BasicService.
     * @return A new BasicService instance created from the provided string data.
     */
    public static BasicService parse(String stringData) {
        String[] data = stringData.split(";");
        return new BasicService(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]));
    }
}
