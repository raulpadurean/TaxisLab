package org.example.models;

/**
 * Represents a custom transportation service with additional features or options.
 * Extends the {@link Service} class and adds support for extra features through the {@code extras} field.
 */
public class CustomService extends Service {

    /**
     * Additional features or options available in the custom service.
     */
    private final String extras;

    /**
     * Constructs a new {@code CustomService} with the specified attributes.
     *
     * @param id         the unique identifier of the service
     * @param name       the name of the service
     * @param pricePerKm the price per kilometer for the service
     * @param extras     a description of the additional features or options available in the service
     */
    public CustomService(Integer id, String name, double pricePerKm, String extras) {
        super(id, name, pricePerKm); // Call the constructor of the parent Service class
        this.extras = extras;
    }

    /**
     * Returns the description of additional features or options available in the service.
     *
     * @return a string representing the extras of the service
     */
    public String getExtras() {
        return extras;
    }

    /**
     * Returns the type of the service as "Custom Service".
     * This method overrides the {@code getServiceType} method in the parent {@link Service} class.
     *
     * @return the service type as "Custom Service"
     */
    @Override
    public String getServiceType() {
        return "Custom Service";
    }

    /**
     * Returns a string representation of the {@code CustomService} object.
     * The string includes the service ID, name, price per kilometer, extras, and service type.
     *
     * @return a string representation of the {@code CustomService} object
     */
    @Override
    public String toString() {
        return String.format("CustomService { id=%d, name='%s', pricePerKm=%.2f, extras='%s', type='%s' }",
                getId(), getName(), getPricePerKm(), extras, getServiceType());
    }
}

