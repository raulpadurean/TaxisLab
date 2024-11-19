package org.example.models;

/**
 * Represents a Basic Service in the Taxi Service Management system.
 * A Basic Service is a specialized type of {@link Service} that provides
 * standard transportation options at a specified price per kilometer.
 */
public class BasicService extends Service {

    /**
     * Constructs a new Basic Service with the specified details.
     *
     * @param id         the unique identifier for the service
     * @param name       the name of the basic service
     * @param pricePerKm the price per kilometer for the basic service
     */
    public BasicService(Integer id, String name, double pricePerKm) {
        super(id, name, pricePerKm);
    }

    /**
     * Returns the type of this service.
     *
     * @return a string indicating the type of the service, which is "Basic Service"
     */
    @Override
    public String getServiceType() {
        return "Basic Service";
    }

    /**
     * Returns a string representation of the Basic Service.
     *
     * @return a formatted string containing the details of the service,
     * including its ID, name, price per kilometer, and service type
     */
    @Override
    public String toString() {
        return String.format("BasicService { id=%d, name='%s', pricePerKm=%.2f, type='%s' }",
                getId(), getName(), getPricePerKm(), getServiceType());
    }
}
