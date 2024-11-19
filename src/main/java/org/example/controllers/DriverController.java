package org.example.controllers;

import org.example.models.Driver;
import org.example.services.DriverService;

import java.util.List;

/**
 * Controller class for managing Driver entities.
 * This class provides methods to handle operations such as adding, retrieving,
 * updating, and deleting driver records. It interacts with the {@link DriverService}
 * to perform the required business logic.
 */
public class DriverController {

    private final DriverService driverService;

    /**
     * Constructs a new {@code DriverController} with the specified service.
     *
     * @param driverService the service instance for handling driver-related operations
     */
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    /**
     * Adds a new driver with the specified details.
     *
     * @param id      the unique identifier of the driver
     * @param name    the name of the driver
     * @param email   the email address of the driver
     * @param address the address of the driver
     * @param phone   the phone number of the driver
     */
    public void addDriver(Integer id, String name, String email, String address, String phone) {
        Driver driver = new Driver(id, name, email, address, phone);
        driverService.addDriver(driver);
    }

    /**
     * Retrieves a driver by its ID.
     *
     * @param id the unique identifier of the driver
     * @return the {@link Driver} object with the specified ID, or {@code null} if not found
     */
    public Driver getDriver(int id) {
        return driverService.getDriver(id);
    }

    /**
     * Retrieves all drivers.
     *
     * @return a list of all {@link Driver} objects
     */
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    /**
     * Updates an existing driver with the specified details.
     *
     * @param id      the unique identifier of the driver
     * @param name    the updated name of the driver
     * @param email   the updated email address of the driver
     * @param address the updated address of the driver
     * @param phone   the updated phone number of the driver
     */
    public void updateDriver(Integer id, String name, String email, String address, String phone) {
        Driver driver = new Driver(id, name, email, address, phone);
        driverService.updateDriver(driver);
    }

    /**
     * Deletes a driver by its ID.
     *
     * @param driverId the unique identifier of the driver to be deleted
     */
    public void deleteDriver(Integer driverId) {
        driverService.deleteDriver(driverId);
    }
}

