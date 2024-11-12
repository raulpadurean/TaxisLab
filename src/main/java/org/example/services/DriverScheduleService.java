package org.example.services;

import org.example.models.DriverSchedule;
import org.example.repositories.IRepository;
import java.util.List;

public class DriverScheduleService {

    private final IRepository<DriverSchedule> driverScheduleRepository;

    public DriverScheduleService(IRepository<DriverSchedule> driverScheduleRepository) {
        this.driverScheduleRepository = driverScheduleRepository;
    }

    public void addDriverSchedule(DriverSchedule driverSchedule) {
        driverScheduleRepository.create(driverSchedule);
    }

    public DriverSchedule getDriverSchedule(int id) {
        return driverScheduleRepository.read(id);
    }

    public List<DriverSchedule> getAllDriverSchedules() {
        return driverScheduleRepository.getAll();
    }

    public void updateDriverSchedule(DriverSchedule driverSchedule) {
        driverScheduleRepository.update(driverSchedule);
    }

    public void deleteDriverSchedule(int id) {
        driverScheduleRepository.delete(id);
    }

}
