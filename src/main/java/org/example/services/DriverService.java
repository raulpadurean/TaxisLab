package org.example.services;

import org.example.models.Driver;
import org.example.repositories.IRepository;
import java.util.List;

public class DriverService {

    private final IRepository<Driver> driverRepository;

    public DriverService(IRepository<Driver> driverRepository) {
        this.driverRepository = driverRepository;
    }

    // Add a driver with automatic ID generation
    public void addDriver(String name, String email, String address, String phone) {
        int id = driverRepository.readAll().size() + 1; // Generate ID based on current size
        Driver driver = new Driver(id, name, email, address, phone); // Create a new Driver object
        driverRepository.create(driver); // Save the driver
    }

    public Driver getDriver(int id) {
        return driverRepository.read(id);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.readAll();
    }

    public void updateDriver(int id, String name, String email, String address, String phone) {
        Driver existingDriver = driverRepository.read(id);
        if (existingDriver == null) {
            throw new IllegalArgumentException("Driver with ID " + id + " does not exist.");
        }
        Driver updatedDriver = new Driver(id, name, email, address, phone);
        driverRepository.update(updatedDriver);
    }

    public void deleteDriver(Integer driverId) {
        driverRepository.delete(driverId);
    }
}
