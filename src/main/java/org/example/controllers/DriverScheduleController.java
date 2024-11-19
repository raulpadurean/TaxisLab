package org.example.controllers;

import org.example.models.Driver;
import org.example.models.DriverSchedule;
import org.example.services.DriverScheduleService;

import java.util.Date;
import java.util.List;

public class DriverScheduleController {

    private final DriverScheduleService driverScheduleService;

    public DriverScheduleController(DriverScheduleService driverScheduleService) {
        this.driverScheduleService = driverScheduleService;
    }

    public void addDriverSchedule(Integer id,int driverId, int companyId, Date checkIn, Date checkOut) {
        DriverSchedule driverSchedule = new DriverSchedule(id, driverId, companyId, checkIn, checkOut);
        driverScheduleService.addDriverSchedule(driverSchedule);
    }

    public DriverSchedule getDriverSchedule(int id){
        return driverScheduleService.getDriverSchedule(id);
    }

    public List<DriverSchedule> getAllDriverSchedules(){
        return driverScheduleService.getAllDriverSchedules();
    }

    public void updateDriverSchedule(Integer id,int driverId, int companyId, Date checkIn, Date checkOut) {
        DriverSchedule driverSchedule = new DriverSchedule(id, driverId, companyId, checkIn, checkOut);
        driverScheduleService.updateDriverSchedule(driverSchedule);
    }

    public void deleteDriverSchedule(Integer driverScheduleId) {
        driverScheduleService.deleteDriverSchedule(driverScheduleId);

    }
}
