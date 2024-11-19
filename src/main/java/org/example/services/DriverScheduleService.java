package org.example.services;

import org.example.models.DriverSchedule;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * Service class for managing {@link DriverSchedule} entities.
 * Provides business logic for interacting with the underlying repository to perform CRUD operations on driver schedules.
 *
 * <p>This class handles the operations such as adding, retrieving, updating, and deleting driver schedules.</p>
 */
public class DriverScheduleService {

    private final IRepository<DriverSchedule> driverScheduleRepository;

    /**
     * Constructs a {@link DriverScheduleService} with the specified repository.
     *
     * @param driverScheduleRepository The repository to be used for storing and retrieving driver schedule entities.
     */
    public DriverScheduleService(IRepository<DriverSchedule> driverScheduleRepository) {
        this.driverScheduleRepository = driverScheduleRepository;
    }

    /**
     * Adds a new driver schedule to the repository.
     *
     * <p>This method persists the provided {@link DriverSchedule} entity to the repository.</p>
     *
     * @param driverSchedule The driver schedule to be added. Must not be {@code null}.
     */
    public void addDriverSchedule(DriverSchedule driverSchedule) {
        driverScheduleRepository.create(driverSchedule);
    }

    /**
     * Retrieves a driver schedule by its unique ID.
     *
     * @param id The unique identifier of the driver schedule to retrieve.
     * @return The driver schedule with the specified ID, or {@code null} if no such schedule exists.
     */
    public DriverSchedule getDriverSchedule(int id) {
        return driverScheduleRepository.read(id);
    }

    /**
     * Retrieves all driver schedules from the repository.
     *
     * @return A list of all driver schedules in the repository.
     */
    public List<DriverSchedule> getAllDriverSchedules() {
        return driverScheduleRepository.getAll();
    }

    /**
     * Updates an existing driver schedule in the repository.
     *
     * <p>If the driver schedule already exists in the repository, it will be updated with the new values.</p>
     *
     * @param driverSchedule The driver schedule to update. The schedule must not be {@code null}.
     */
    public void updateDriverSchedule(DriverSchedule driverSchedule) {
        driverScheduleRepository.update(driverSchedule);
    }

    /**
     * Deletes a driver schedule from the repository.
     *
     * @param driverScheduleId The unique ID of the driver schedule to be deleted.
     */
    public void deleteDriverSchedule(Integer driverScheduleId) {
        driverScheduleRepository.delete(driverScheduleId);
    }
}
