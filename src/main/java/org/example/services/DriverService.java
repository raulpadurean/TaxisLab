package org.example.services;

import org.example.models.Driver;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * Service class for managing {@link Driver} entities.
 * Provides business logic for interacting with the underlying repository to perform CRUD operations on driver data.
 *
 * <p>This class handles the operations such as adding, retrieving, updating, and deleting driver records.</p>
 */
public class DriverService {

    final private IRepository<Driver> driverRepository;

    /**
     * Constructs a {@link DriverService} with the specified repository.
     *
     * @param driverRepository The repository to be used for storing and retrieving driver entities.
     */
    public DriverService(IRepository<Driver> driverRepository) {
        this.driverRepository = driverRepository;
    }

    /**
     * Adds a new driver to the repository.
     *
     * <p>This method persists the provided {@link Driver} entity to the repository.</p>
     *
     * @param driver The driver to be added. Must not be {@code null}.
     */
    public void addDriver(Driver driver) {
        driverRepository.create(driver);
    }

    /**
     * Retrieves a driver by its unique ID.
     *
     * @param id The unique identifier of the driver to retrieve.
     * @return The driver with the specified ID, or {@code null} if no such driver exists.
     */
    public Driver getDriver(int id) {
        return driverRepository.read(id);
    }

    /**
     * Retrieves all drivers from the repository.
     *
     * @return A list of all drivers in the repository.
     */
    public List<Driver> getAllDrivers() {
        return driverRepository.getAll();
    }

    /**
     * Updates an existing driver in the repository.
     *
     * <p>If the driver already exists in the repository, it will be updated with the new values.</p>
     *
     * @param driver The driver to update. The driver must not be {@code null}.
     */
    public void updateDriver(Driver driver) {
        driverRepository.update(driver);
    }

    /**
     * Deletes a driver from the repository.
     *
     * @param driverId The unique ID of the driver to be deleted.
     */
    public void deleteDriver(Integer driverId) {
        driverRepository.delete(driverId);
    }
}
