package org.example.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        return id + "," + driver + "," + company + "," + checkIn + "," + checkOut;
    }

    public static DriverSchedule parse(String line) throws ParseException {
        String[] fields = line.split(",");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return new DriverSchedule(
                Integer.parseInt(fields[0]),
                Driver.parse(fields[1]),
                Company.parse(fields[2]),
                dateFormat.parse(fields[3].trim()),
                dateFormat.parse(fields[4].trim())
        );
    }
}
