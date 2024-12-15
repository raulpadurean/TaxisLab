package org.example.controllers;

import org.example.models.DriverSchedule;
import org.example.services.DriverScheduleService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller class responsible for managing {@link DriverSchedule} objects.
 * This controller provides methods to add, retrieve, update, and delete driver schedules,
 * delegating the actual business logic to the {@link DriverScheduleService} class.
 */
public class DriverScheduleController {

    private final DriverScheduleService driverScheduleService;

    /**
     * Constructs a {@link DriverScheduleController} with the specified {@link DriverScheduleService}.
     * This service is responsible for the business logic related to driver schedule operations.
     *
     * @param driverScheduleService The {@link DriverScheduleService} used to delegate business logic.
     */
    public DriverScheduleController(DriverScheduleService driverScheduleService) {
        this.driverScheduleService = driverScheduleService;
    }

    /**
     * Adds a new {@link DriverSchedule} with the provided details.
     * Delegates the actual operation to the {@link DriverScheduleService}.
     *
     * @param driverId The ID of the driver.
     * @param companyId The ID of the company.
     * @param checkIn The check-in time of the driver.
     * @param checkOut The check-out time of the driver.
     */
    public void addDriverSchedule(int driverId, int companyId, LocalDateTime checkIn, LocalDateTime checkOut) {
        driverScheduleService.addDriverSchedule(driverId, companyId, checkIn, checkOut);
    }

    /**
     * Updates the details of an existing {@link DriverSchedule} with the provided information.
     * Delegates the actual update to the {@link DriverScheduleService}.
     *
     * @param id The unique identifier of the driver schedule to update.
     * @param driverId The updated driver ID.
     * @param companyId The updated company ID.
     * @param checkIn The updated check-in time.
     * @param checkOut The updated check-out time.
     */
    public void updateDriverSchedule(int id, int driverId, int companyId, LocalDateTime checkIn, LocalDateTime checkOut) {
        driverScheduleService.updateDriverSchedule(id, driverId, companyId, checkIn, checkOut);
    }

    /**
     * Retrieves a {@link DriverSchedule} by its unique identifier.
     * Delegates the actual retrieval to the {@link DriverScheduleService}.
     *
     * @param id The unique identifier of the driver schedule.
     * @return The {@link DriverSchedule} object with the specified ID, or {@code null} if not found.
     */
    public DriverSchedule getDriverSchedule(int id) {
        return driverScheduleService.getDriverSchedule(id);
    }

    /**
     * Retrieves all {@link DriverSchedule} objects.
     * Delegates the actual retrieval to the {@link DriverScheduleService}.
     *
     * @return A list of all {@link DriverSchedule} objects.
     */
    public List<DriverSchedule> getAllDriverSchedules() {
        return driverScheduleService.getAllDriverSchedules();
    }

    /**
     * Deletes a {@link DriverSchedule} by its unique identifier.
     * Delegates the actual deletion to the {@link DriverScheduleService}.
     *
     * @param id The unique identifier of the driver schedule to delete.
     */
    public void deleteDriverSchedule(int id) {
        driverScheduleService.deleteDriverSchedule(id);
    }
}
