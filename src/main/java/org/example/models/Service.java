package org.example.models;

/**
 * Represents a general service offered by a company.
 * This is an abstract class, and specific types of services (such as {@link BasicService} or {@link CustomService})
 * should extend this class and provide their own implementation of the {@link #getServiceType()} method.
 * The service includes an identifier, a name, and a price per kilometer.
 * Implements the {@link HasId} interface to ensure that every service has a unique identifier.
 */
public abstract class Service implements HasId {

    protected Integer id;
    protected String name;
    protected double pricePerKm;

    /**
     * Constructs a Service object with the specified ID, name, and price per kilometer.
     * The constructor validates that the ID is positive, the name is not null or empty,
     * and the price per kilometer is not negative.
     *
     * @param id          The unique identifier for the service. Must be positive.
     * @param name        The name of the service. Cannot be null or empty.
     * @param pricePerKm  The price per kilometer for the service. Cannot be negative.
     * @throws IllegalArgumentException If any argument is invalid (e.g., ID is non-positive, name is empty, or price is negative).
     */
    public Service(Integer id, String name, double pricePerKm) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (pricePerKm < 0) {
            throw new IllegalArgumentException("Price per km cannot be negative.");
        }
        this.id = id;
        this.name = name;
        this.pricePerKm = pricePerKm;
    }

    /**
     * Retrieves the unique identifier of the service.
     *
     * @return The ID of the service.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Retrieves the name of the service.
     *
     * @return The name of the service.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price per kilometer of the service.
     *
     * @return The price per kilometer for the service.
     */
    public double getPricePerKm() {
        return pricePerKm;
    }

    /**
     * Sets the unique identifier for the service.
     *
     * @param id The ID to set for the service.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Sets the name for the service.
     *
     * @param name The name to set for the service.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price per kilometer for the service.
     *
     * @param pricePerKm The price per kilometer to set for the service.
     */
    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    /**
     * Abstract method to be implemented by subclasses to return the specific type of the service.
     *
     * @return A string representing the service type (e.g., "Basic", "Custom").
     */
    public abstract String getServiceType();

    /**
     * Parses a string representation of a service and returns the corresponding service object.
     * The string is expected to follow the format "<serviceType>:<serviceDetails>".
     * Based on the service type (e.g., "Basic" or "Custom"), the appropriate subclass of {@link Service} will be created.
     *
     * @param serviceData The string representing the service data, in the format "<serviceType>:<serviceDetails>".
     * @return A {@link Service} object of the correct subclass based on the type specified in the input string.
     * @throws IllegalArgumentException If the service type is unknown.
     */
    public static Service parse(String serviceData) {
        String[] parts = serviceData.split(":");
        String type = parts[0];

        return switch (type) {
            case "Basic" -> BasicService.parse(parts[1]);
            case "Custom" -> CustomService.parse(parts[1]);
            default -> throw new IllegalArgumentException("Unknown service type: " + type);
        };
    }
}
