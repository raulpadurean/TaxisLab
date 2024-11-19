package org.example.models;

/**
 * Represents the association between a driver and a company.
 * A {@code CompanyDriver} object stores the relationship between a driver and a company,
 * identified by the driver's ID and the company's ID.
 * Implements the {@link HasId} interface to provide a unique identifier for each driver.
 */
public class CompanyDriver implements HasId {

    private Integer driverId;
    private int companyId;

    /**
     * Constructs a new {@code CompanyDriver} with the specified driver ID and company ID.
     *
     * @param driverId  the unique identifier of the driver
     * @param companyId the unique identifier of the company
     */
    public CompanyDriver(Integer driverId, int companyId) {
        this.driverId = driverId;
        this.companyId = companyId;
    }

    /**
     * Returns the unique identifier of the driver associated with the company.
     *
     * @return the unique identifier of the driver
     */
    public int getDriverId() {
        return driverId;
    }

    /**
     * Sets the unique identifier of the driver associated with the company.
     *
     * @param driverId the unique identifier of the driver to set
     */
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    /**
     * Returns the unique identifier of the company associated with the driver.
     *
     * @return the unique identifier of the company
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * Sets the unique identifier of the company associated with the driver.
     *
     * @param companyId the unique identifier of the company to set
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * Returns a string representation of the {@code CompanyDriver} object.
     * The string includes the driver's ID and the company's ID.
     *
     * @return a string representation of the {@code CompanyDriver} object
     */
    @Override
    public String toString() {
        return "CompanyDriver{" +
                "driverId=" + driverId +
                ", companyId=" + companyId +
                '}';
    }

    /**
     * Returns the unique identifier of the driver, which is used as the primary ID.
     * This method is required by the {@link HasId} interface.
     *
     * @return the unique identifier of the driver
     */

    public Integer getId() {
        return driverId;
    }
}
