package org.example.controllers;

import org.example.models.Driver;
import org.example.services.DriverService;

import java.util.List;

public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    public void addDriver(String name, String email, String address, String phone) {
        driverService.addDriver(name, email, address, phone); // Delegate to service
    }

    public Driver getDriver(int id) {
        return driverService.getDriver(id);
    }

    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    public void updateDriver(int id, String name, String email, String address, String phone) {
        driverService.updateDriver(id, name, email, address, phone);
    }

    public void deleteDriver(Integer driverId) {
        driverService.deleteDriver(driverId);
    }
}
