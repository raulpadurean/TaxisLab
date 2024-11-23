package org.example.services;

import org.example.models.Company;
import org.example.models.Driver;
import org.example.models.DriverSchedule;
import org.example.repositories.IRepository;

import java.util.Date;
import java.util.List;

public class DriverScheduleService {

    private final IRepository<DriverSchedule> driverScheduleRepository;
    private final IRepository<Driver> driverRepository;
    private final IRepository<Company> companyRepository;

    public DriverScheduleService(IRepository<DriverSchedule> driverScheduleRepository,
                                 IRepository<Driver> driverRepository,
                                 IRepository<Company> companyRepository) {
        this.driverScheduleRepository = driverScheduleRepository;
        this.driverRepository = driverRepository;
        this.companyRepository = companyRepository;
    }

    public void addDriverSchedule(Integer id, int driverId, int companyId, Date checkIn, Date checkOut) {
        // Validate if the driver exists
        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver with ID " + driverId + " does not exist.");
        }

        // Validate if the company exists
        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        // Create a DriverSchedule with Driver and Company objects
        DriverSchedule driverSchedule = new DriverSchedule(id, driver, company, checkIn, checkOut);

        // Save the DriverSchedule
        driverScheduleRepository.create(driverSchedule);
    }

    public DriverSchedule getDriverSchedule(int id) {
        return driverScheduleRepository.read(id);
    }

    public List<DriverSchedule> getAllDriverSchedules() {
        return driverScheduleRepository.readAll();
    }

    public void updateDriverSchedule(Integer id, int driverId, int companyId, Date checkIn, Date checkOut) {
        // Validate if the driver schedule exists
        DriverSchedule existingSchedule = driverScheduleRepository.read(id);
        if (existingSchedule == null) {
            throw new IllegalArgumentException("DriverSchedule with ID " + id + " does not exist.");
        }

        // Validate if the driver exists
        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver with ID " + driverId + " does not exist.");
        }

        // Validate if the company exists
        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        // Create and update the DriverSchedule
        DriverSchedule updatedSchedule = new DriverSchedule(id, driver, company, checkIn, checkOut);
        driverScheduleRepository.update(updatedSchedule);
    }

    public void deleteDriverSchedule(Integer id) {
        driverScheduleRepository.delete(id);
    }
}
