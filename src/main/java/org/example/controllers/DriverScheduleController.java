package org.example.controllers;

import org.example.models.DriverSchedule;
import org.example.services.DriverScheduleService;

import java.util.Date;
import java.util.List;

public class DriverScheduleController {

    private final DriverScheduleService driverScheduleService;

    public DriverScheduleController(DriverScheduleService driverScheduleService) {
        this.driverScheduleService = driverScheduleService;
    }

    public void addDriverSchedule(int driverId, int companyId, Date checkIn, Date checkOut) {
        driverScheduleService.addDriverSchedule(driverId, companyId, checkIn, checkOut);
    }

    public void updateDriverSchedule(int id, int driverId, int companyId, Date checkIn, Date checkOut) {
        driverScheduleService.updateDriverSchedule(id, driverId, companyId, checkIn, checkOut);
    }

    public DriverSchedule getDriverSchedule(int id) {
        return driverScheduleService.getDriverSchedule(id);
    }

    public List<DriverSchedule> getAllDriverSchedules() {
        return driverScheduleService.getAllDriverSchedules();
    }

    public void deleteDriverSchedule(int id) {
        driverScheduleService.deleteDriverSchedule(id);
    }
}
