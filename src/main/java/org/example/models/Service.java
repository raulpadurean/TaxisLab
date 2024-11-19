package org.example.models;

/**
 * Represents a generic service with an associated cost per kilometer, a name, and a unique identifier.
 *
 * <p>The {@code Service} class is an abstract base class designed for different types of services.
 * It provides common attributes such as ID, name, and price per kilometer, while allowing
 * subclasses to define the specific type of service through the {@link #getServiceType()} method.</p>
 *
 * <p>This class implements {@link HasId}, ensuring that all services have a unique identifier.</p>
 */
public abstract class Service implements HasId {

    private final Integer id;
    private final String name;
    private final double pricePerKm;

    /**
     * Constructs a {@code Service} instance with the specified ID, name, and price per kilometer.
     *
     * @param id          The unique identifier for the service. Must be positive.
     * @param name        The name of the service. Cannot be null or empty.
     * @param pricePerKm  The cost of the service per kilometer. Cannot be negative.
     * @throws IllegalArgumentException If {@code id <= 0}, {@code name} is null or empty, or {@code pricePerKm < 0}.
     */
    public Service(int id, String name, double pricePerKm) {
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
     * Gets the unique identifier of the service.
     *
     * @return The unique identifier.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Gets the name of the service.
     *
     * @return The name of the service.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the service per kilometer.
     *
     * @return The price per kilometer.
     */
    public double getPricePerKm() {
        return pricePerKm;
    }

    /**
     * Provides a string representation of the {@code Service}.
     *
     * @return A string describing the service, including its ID, name, and price per kilometer.
     */
    @Override
    public String toString() {
        return String.format("Service { id=%d, name='%s', pricePerKm=%.2f }", id, name, pricePerKm);
    }

    /**
     * Abstract method to get the specific type of service.
     *
     * <p>This method must be implemented by subclasses to provide information about
     * the type of service (e.g., "Basic Service", "Custom Service").</p>
     *
     * @return The type of service as a {@code String}.
     */
    public abstract String getServiceType();
}
