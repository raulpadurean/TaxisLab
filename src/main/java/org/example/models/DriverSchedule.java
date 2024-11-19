package org.example.models;

import java.util.Date;

public class DriverSchedule {
    private int id;
    private Driver driver;
    private Company company;
    private Date checkIn;
    private Date checkOut;

    public DriverSchedule(int id, Driver driver, Company company, Date checkIn, Date checkOut) {
        this.id = id;
        this.driver = driver;
        this.company = company;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
                ", driverId=" + driver +
                ", companyId=" + company +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
