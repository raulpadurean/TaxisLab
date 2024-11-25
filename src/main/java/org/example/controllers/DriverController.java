package org.example.controllers;

import org.example.models.Driver;
import org.example.services.DriverService;

import java.util.List;

/**
 * Controller class responsible for managing {@link Driver} objects.
 * This controller provides methods to add, retrieve, update, and delete drivers,
 * delegating the actual business logic to the {@link DriverService} class.
 */
public class DriverController {

    private final DriverService driverService;

    /**
     * Constructs a {@link DriverController} with the specified {@link DriverService}.
     * This service is responsible for the business logic related to driver operations.
     *
     * @param driverService The {@link DriverService} used to delegate business logic.
     */
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    /**
     * Adds a new {@link Driver} with the provided details.
     * Delegates the actual operation to the {@link DriverService}.
     *
     * @param name The name of the driver.
     * @param email The email of the driver.
     * @param address The address of the driver.
     * @param phone The phone number of the driver.
     */
    public void addDriver(String name, String email, String address, String phone) {
        driverService.addDriver(name, email, address, phone); // Delegate to service
    }

    /**
     * Retrieves a {@link Driver} by its unique identifier.
     * Delegates the actual retrieval to the {@link DriverService}.
     *
     * @param id The unique identifier of the driver.
     * @return The {@link Driver} object with the specified ID, or {@code null} if not found.
     */
    public Driver getDriver(int id) {
        return driverService.getDriver(id);
    }

    /**
     * Retrieves all {@link Driver} objects.
     * Delegates the actual retrieval to the {@link DriverService}.
     *
     * @return A list of all {@link Driver} objects.
     */
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    /**
     * Updates the details of an existing {@link Driver} with the provided information.
     * Delegates the actual update to the {@link DriverService}.
     *
     * @param id The unique identifier of the driver to update.
     * @param name The updated name of the driver.
     * @param email The updated email of the driver.
     * @param address The updated address of the driver.
     * @param phone The updated phone number of the driver.
     */
    public void updateDriver(int id, String name, String email, String address, String phone) {
        driverService.updateDriver(id, name, email, address, phone);
    }

    /**
     * Deletes a {@link Driver} by its unique identifier.
     * Delegates the actual deletion to the {@link DriverService}.
     *
     * @param driverId The unique identifier of the driver to delete.
     */
    public void deleteDriver(Integer driverId) {
        driverService.deleteDriver(driverId);
    }
}
