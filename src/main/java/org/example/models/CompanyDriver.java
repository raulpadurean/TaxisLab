package org.example.models;

/**
 * Represents a relationship between a driver and a company. This class associates a driver
 * with a company by storing their respective details and a unique identifier.
 * It implements the {@link HasId} interface, meaning each instance has a unique ID.
 */
public class CompanyDriver implements HasId {

    private Integer id;
    private Driver driver;
    private Company company;

    /**
     * Constructs a new CompanyDriver object with the specified ID, company, and driver.
     *
     * @param id      The unique identifier for the CompanyDriver relationship.
     * @param company The company associated with the driver.
     * @param driver  The driver associated with the company.
     */
    public CompanyDriver(Integer id, Company company, Driver driver) {
        this.id = id;
        this.company = company;
        this.driver = driver;
    }


    public CompanyDriver() {}

    /**
     * Retrieves the driver associated with the company.
     *
     * @return The driver associated with the company.
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Sets the driver associated with the company.
     *
     * @param driver The driver to set for the company.
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the company associated with the driver.
     *
     * @return The company associated with the driver.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets the company associated with the driver.
     *
     * @param company The company to set for the driver.
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Sets the unique identifier of the CompanyDriver instance.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the CompanyDriver object in the format:
     * "DriverDetails,CompanyDetails".
     *
     * @return A string representation of the CompanyDriver instance.
     */
    @Override
    public String toString() {
        return driver + "," + company;
    }

    /**
     * Parses a string representation of a CompanyDriver object and returns a new
     * CompanyDriver instance. The expected string format is:
     * "DriverDetails,CompanyDetails".
     *
     * @param line The string representation of the CompanyDriver object.
     * @return A new CompanyDriver object created from the string data.
     */
    public CompanyDriver parse(String line) {
        String[] fields = line.split(",");
        return new CompanyDriver(
                Integer.parseInt(fields[0]),
                Company.parse(fields[1]),
                Driver.parse(fields[0])
        );
    }

    /**
     * Retrieves the unique identifier of the CompanyDriver instance.
     *
     * @return The ID of the CompanyDriver.
     */
    @Override
    public Integer getId() {
        return id;
    }
}
