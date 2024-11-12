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
        Driver driver = new Driver(0, name, email, address, phone);
        driverService.addDriver(driver);
    }

    public Driver getDriver(int id){
        return driverService.getDriver(id);
    }

    public List<Driver> getAllDrivers(){
        return driverService.getAllDrivers();
    }

    public void updateDriver(Driver driver){
        driverService.updateDriver(driver);
    }

    public  void deleteDriver(int id){
        driverService.deleteDriver(id);
    }
}