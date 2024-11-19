package org.example.controllers;

import org.example.models.DriverSchedule;
import org.example.services.DriverScheduleService;

import java.util.Date;
import java.util.List;

/**
 * Controller class for managing Driver Schedule entities.
 * This class provides methods to handle operations such as adding, retrieving,
 * updating, and deleting driver schedule records. It interacts with the {@link DriverScheduleService}
 * to perform the required business logic.
 */
public class DriverScheduleController {

    private final DriverScheduleService driverScheduleService;

    /**
     * Constructs a new {@code DriverScheduleController} with the specified service.
     *
     * @param driverScheduleService the service instance for handling driver schedule-related operations
     */
    public DriverScheduleController(DriverScheduleService driverScheduleService) {
        this.driverScheduleService = driverScheduleService;
    }

    /**
     * Adds a new driver schedule with the specified details.
     *
     * @param id        the unique identifier of the driver schedule
     * @param driverId  the ID of the driver associated with the schedule
     * @param companyId the ID of the company the driver is associated with
     * @param checkIn   the check-in date and time for the driver's schedule
     * @param checkOut  the check-out date and time for the driver's schedule
     */
    public void addDriverSchedule(Integer id, int driverId, int companyId, Date checkIn, Date checkOut) {
        DriverSchedule driverSchedule = new DriverSchedule(id, driverId, companyId, checkIn, checkOut);
        driverScheduleService.addDriverSchedule(driverSchedule);
    }

    /**
     * Retrieves a driver schedule by its ID.
     *
     * @param id the unique identifier of the driver schedule
     * @return the {@link DriverSchedule} object with the specified ID, or {@code null} if not found
     */
    public DriverSchedule getDriverSchedule(int id) {
        return driverScheduleService.getDriverSchedule(id);
    }

    /**
     * Retrieves all driver schedules.
     *
     * @return a list of all {@link DriverSchedule} objects
     */
    public List<DriverSchedule> getAllDriverSchedules() {
        return driverScheduleService.getAllDriverSchedules();
    }

    /**
     * Updates an existing driver schedule with the specified details.
     *
     * @param id        the unique identifier of the driver schedule
     * @param driverId  the updated ID of the driver associated with the schedule
     * @param companyId the updated ID of the company the driver is associated with
     * @param checkIn   the updated check-in date and time for the driver's schedule
     * @param checkOut  the updated check-out date and time for the driver's schedule
     */
    public void updateDriverSchedule(Integer id, int driverId, int companyId, Date checkIn, Date checkOut) {
        DriverSchedule driverSchedule = new DriverSchedule(id, driverId, companyId, checkIn, checkOut);
        driverScheduleService.updateDriverSchedule(driverSchedule);
    }

    /**
     * Deletes a driver schedule by its ID.
     *
     * @param driverScheduleId the unique identifier of the driver schedule to be deleted
     */
    public void deleteDriverSchedule(Integer driverScheduleId) {
        driverScheduleService.deleteDriverSchedule(driverScheduleId);
    }
}
