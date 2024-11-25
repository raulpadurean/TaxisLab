package org.example.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents the schedule of a driver within a company, including the driver's work shift times.
 * Implements the {@link HasId} interface to ensure each driver schedule has a unique identifier.
 */
public class DriverSchedule implements HasId {

    private Integer id;
    private Driver driver;
    private Company company;
    private Date checkIn;
    private Date checkOut;

    /**
     * Constructs a new DriverSchedule object with the specified parameters.
     *
     * @param id       The unique identifier for the driver schedule.
     * @param driver   The driver associated with the schedule.
     * @param company  The company the driver works for.
     * @param checkIn  The check-in date and time of the driver.
     * @param checkOut The check-out date and time of the driver.
     */
    public DriverSchedule(Integer id, Driver driver, Company company, Date checkIn, Date checkOut) {
        this.id = id;
        this.driver = driver;
        this.company = company;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    /**
     * Retrieves the unique identifier for the driver schedule.
     *
     * @return The ID of the driver schedule.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the driver schedule.
     *
     * @param id The ID to set for the driver schedule.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the driver associated with the schedule.
     *
     * @return The driver assigned to the schedule.
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Sets the driver associated with the schedule.
     *
     * @param driver The driver to assign to the schedule.
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the company that employs the driver for this schedule.
     *
     * @return The company the driver works for.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets the company that employs the driver for this schedule.
     *
     * @param company The company to assign to the schedule.
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Retrieves the check-in time for the driver.
     *
     * @return The check-in time of the driver.
     */
    public Date getCheckIn() {
        return checkIn;
    }

    /**
     * Sets the check-in time for the driver.
     *
     * @param checkIn The check-in time to set for the driver.
     */
    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * Retrieves the check-out time for the driver.
     *
     * @return The check-out time of the driver.
     */
    public Date getCheckOut() {
        return checkOut;
    }

    /**
     * Sets the check-out time for the driver.
     *
     * @param checkOut The check-out time to set for the driver.
     */
    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    /**
     * Returns a string representation of the DriverSchedule object in the format:
     * "id,driver,company,checkIn,checkOut".
     *
     * @return A string representation of the DriverSchedule instance.
     */
    @Override
    public String toString() {
        return id + "," + driver + "," + company + "," + checkIn + "," + checkOut;
    }

    /**
     * Parses a string representation of a DriverSchedule object and returns a new DriverSchedule instance.
     * The expected string format is:
     * "id,driver,company,checkIn,checkOut".
     *
     * @param line The string representation of the DriverSchedule object.
     * @return A new DriverSchedule object created from the string data.
     * @throws ParseException If there is an error parsing the date values.
     * @throws NumberFormatException If the ID is not a valid number.
     */
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
