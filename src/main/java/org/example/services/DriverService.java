package org.example.services;

import org.example.models.Driver;
import org.example.repositories.IRepository;
import java.util.List;
public class DriverService {

    final private IRepository<Driver> driverRepository;

    public DriverService(IRepository<Driver> driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void addDriver(Driver driver) {
        driverRepository.create(driver);
    }

    public Driver getDriver(int id) {
        return driverRepository.read(id);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.getAll();
    }

    public void updateDriver(Driver driver) {
        driverRepository.update(driver);
    }

    public void deleteDriver(Integer driverId) {

        driverRepository.delete(driverId);
    }

}
