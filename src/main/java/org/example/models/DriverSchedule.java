package org.example.models;

import java.util.Date;

/**
 * Represents a driver's schedule in the system, including details about the driver,
 * the company they are associated with, and their check-in and check-out times.
 */
public class DriverSchedule implements HasId {

    /**
     * The unique identifier for the driver schedule.
     */
    private Integer id;

    /**
     * The unique identifier of the driver.
     */
    private int driverId;

    /**
     * The unique identifier of the company associated with the driver.
     */
    private int companyId;

    /**
     * The check-in time for the driver's schedule.
     */
    private Date checkIn;

    /**
     * The check-out time for the driver's schedule.
     */
    private Date checkOut;

    /**
     * Constructs a new {@code DriverSchedule} with the specified attributes.
     *
     * @param id        the unique identifier of the schedule
     * @param driverId  the ID of the driver
     * @param companyId the ID of the associated company
     * @param checkIn   the check-in time for the schedule
     * @param checkOut  the check-out time for the schedule
     */
    public DriverSchedule(Integer id, int driverId, int companyId, Date checkIn, Date checkOut) {
        this.id = id;
        this.driverId = driverId;
        this.companyId = companyId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    /**
     * Retrieves the unique identifier of the schedule.
     *
     * @return the unique schedule ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the schedule.
     *
     * @param id the new ID to set for the schedule
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the unique identifier of the driver.
     *
     * @return the driver's ID
     */
    public int getDriverId() {
        return driverId;
    }

    /**
     * Sets the unique identifier of the driver.
     *
     * @param driverId the new driver ID
     */
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    /**
     * Retrieves the unique identifier of the company associated with the schedule.
     *
     * @return the company ID
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * Sets the unique identifier of the associated company.
     *
     * @param companyId the new company ID
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * Retrieves the check-in time for the driver's schedule.
     *
     * @return the check-in time
     */
    public Date getCheckIn() {
        return checkIn;
    }

    /**
     * Updates the check-in time for the driver's schedule.
     *
     * @param checkIn the new check-in time
     */
    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * Retrieves the check-out time for the driver's schedule.
     *
     * @return the check-out time
     */
    public Date getCheckOut() {
        return checkOut;
    }

    /**
     * Updates the check-out time for the driver's schedule.
     *
     * @param checkOut the new check-out time
     */
    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    /**
     * Returns a string representation of the {@code DriverSchedule} object.
     * The string includes the schedule's ID, driver ID, company ID, and check-in/check-out times.
     *
     * @return a string representation of the {@code DriverSchedule} object
     */
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
