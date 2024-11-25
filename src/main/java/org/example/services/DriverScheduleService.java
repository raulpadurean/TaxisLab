package org.example.services;

import org.example.models.Company;
import org.example.models.Driver;
import org.example.models.DriverSchedule;
import org.example.repositories.IRepository;

import java.util.Date;
import java.util.List;

/**
 * Service class for managing DriverSchedule entities.
 * Provides functionality to add, retrieve, update, and delete driver schedules.
 */
public class DriverScheduleService {

    private final IRepository<DriverSchedule> driverScheduleRepository;
    private final IRepository<Driver> driverRepository;
    private final IRepository<Company> companyRepository;

    /**
     * Constructs a DriverScheduleService with the specified repositories.
     *
     * @param driverScheduleRepository The repository for managing DriverSchedule objects.
     * @param driverRepository         The repository for managing Driver objects.
     * @param companyRepository        The repository for managing Company objects.
     */
    public DriverScheduleService(IRepository<DriverSchedule> driverScheduleRepository,
                                 IRepository<Driver> driverRepository,
                                 IRepository<Company> companyRepository) {
        this.driverScheduleRepository = driverScheduleRepository;
        this.driverRepository = driverRepository;
        this.companyRepository = companyRepository;
    }

    /**
     * Adds a new driver schedule with the provided details.
     * Automatically generates a unique ID for the schedule.
     * Validates that the driver and company exist before creating the schedule.
     *
     * @param driverId The ID of the driver assigned to the schedule.
     * @param companyId The ID of the company associated with the schedule.
     * @param checkIn The check-in date and time for the driver.
     * @param checkOut The check-out date and time for the driver.
     * @throws IllegalArgumentException If the driver or company does not exist.
     */
    public void addDriverSchedule(int driverId, int companyId, Date checkIn, Date checkOut) {
        // Validate driver and company existence
        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver with ID " + driverId + " does not exist.");
        }

        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        // Generate an ID for the new DriverSchedule
        int id = driverScheduleRepository.readAll().size() + 1;

        // Create and save the DriverSchedule
        DriverSchedule driverSchedule = new DriverSchedule(id, driver, company, checkIn, checkOut);
        driverScheduleRepository.create(driverSchedule);
    }

    /**
     * Retrieves a driver schedule by its ID.
     *
     * @param id The ID of the driver schedule to retrieve.
     * @return The DriverSchedule object with the specified ID, or null if not found.
     */
    public DriverSchedule getDriverSchedule(int id) {
        return driverScheduleRepository.read(id);
    }

    /**
     * Retrieves all driver schedules in the repository.
     *
     * @return A list of all DriverSchedule objects.
     */
    public List<DriverSchedule> getAllDriverSchedules() {
        return driverScheduleRepository.readAll();
    }

    /**
     * Updates an existing driver schedule with the provided details.
     * Validates that the driver and company exist before updating the schedule.
     *
     * @param id         The ID of the driver schedule to update.
     * @param driverId   The ID of the driver assigned to the schedule.
     * @param companyId  The ID of the company associated with the schedule.
     * @param checkIn    The updated check-in date and time for the driver.
     * @param checkOut   The updated check-out date and time for the driver.
     * @throws IllegalArgumentException If the driver, company, or driver schedule does not exist.
     */
    public void updateDriverSchedule(int id, int driverId, int companyId, Date checkIn, Date checkOut) {
        // Validate driver and company existence
        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver with ID " + driverId + " does not exist.");
        }

        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        // Validate existing DriverSchedule
        DriverSchedule existingSchedule = driverScheduleRepository.read(id);
        if (existingSchedule == null) {
            throw new IllegalArgumentException("DriverSchedule with ID " + id + " does not exist.");
        }

        // Update and save the DriverSchedule
        DriverSchedule updatedSchedule = new DriverSchedule(id, driver, company, checkIn, checkOut);
        driverScheduleRepository.update(updatedSchedule);
    }

    /**
     * Deletes a driver schedule by its ID.
     *
     * @param id The ID of the driver schedule to delete.
     */
    public void deleteDriverSchedule(int id) {
        driverScheduleRepository.delete(id);
    }
}
