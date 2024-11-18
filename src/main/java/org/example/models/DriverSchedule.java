package org.example.models;

import java.util.Date;

public class DriverSchedule implements HasId {
    private int id;
    private int driverId;
    private int companyId;
    private Date checkIn;
    private Date checkOut;

    public DriverSchedule(int id, int driverId, int companyId, Date checkIn, Date checkOut) {
        this.id = id;
        this.driverId = driverId;
        this.companyId = companyId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "DriverSchedule{" +
                "id=" + id +
                ", driverId=" + driverId +
                ", companyId=" + companyId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
