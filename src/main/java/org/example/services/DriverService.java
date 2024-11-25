package org.example.services;

import org.example.models.Driver;
import org.example.repositories.IRepository;
import java.util.List;

/**
 * Service class for managing Driver entities.
 * Provides functionality to add, retrieve, update, and delete drivers.
 */
public class DriverService {

    private final IRepository<Driver> driverRepository;

    /**
     * Constructs a DriverService with the specified repository for managing Driver objects.
     *
     * @param driverRepository The repository for managing Driver objects.
     */
    public DriverService(IRepository<Driver> driverRepository) {
        this.driverRepository = driverRepository;
    }

    /**
     * Adds a new driver with the provided details.
     * Automatically generates a unique ID for the driver based on the current size of the repository.
     *
     * @param name    The name of the driver.
     * @param email   The email address of the driver.
     * @param address The address of the driver.
     * @param phone   The phone number of the driver.
     */
    public void addDriver(String name, String email, String address, String phone) {
        int id = driverRepository.readAll().size() + 1; // Generate ID based on current size
        Driver driver = new Driver(id, name, email, address, phone); // Create a new Driver object
        driverRepository.create(driver); // Save the driver in the repository
    }

    /**
     * Retrieves a driver by its ID.
     *
     * @param id The ID of the driver to retrieve.
     * @return The Driver object with the specified ID, or null if not found.
     */
    public Driver getDriver(int id) {
        return driverRepository.read(id);
    }

    /**
     * Retrieves all drivers in the repository.
     *
     * @return A list of all Driver objects in the repository.
     */
    public List<Driver> getAllDrivers() {
        return driverRepository.readAll();
    }

    /**
     * Updates an existing driver with the provided details.
     * Validates that the driver exists before updating.
     *
     * @param id      The ID of the driver to update.
     * @param name    The updated name of the driver.
     * @param email   The updated email address of the driver.
     * @param address The updated address of the driver.
     * @param phone   The updated phone number of the driver.
     * @throws IllegalArgumentException If the driver with the given ID does not exist.
     */
    public void updateDriver(int id, String name, String email, String address, String phone) {
        Driver existingDriver = driverRepository.read(id);
        if (existingDriver == null) {
            throw new IllegalArgumentException("Driver with ID " + id + " does not exist.");
        }

        // Create the updated Driver object
        Driver updatedDriver = new Driver(id, name, email, address, phone);
        driverRepository.update(updatedDriver); // Update the driver in the repository
    }

    /**
     * Deletes a driver by its ID.
     *
     * @param driverId The ID of the driver to delete.
     */
    public void deleteDriver(Integer driverId) {
        driverRepository.delete(driverId); // Remove the driver from the repository
    }
}
