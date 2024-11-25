package org.example.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an order made by a client for a service, including details such as the service requested,
 * the client, driver, company, and the time of the order.
 * Implements the {@link HasId} interface to ensure each order has a unique identifier.
 */
public class Order implements HasId {

    private Integer id;
    private Service service;
    private double totalKm;
    private Client client;
    private Driver driver;
    private Company company;
    private Date datetime;

    /**
     * Constructs an Order object with the specified details.
     *
     * @param id       The unique identifier for the order.
     * @param totalKm The total distance in kilometers for the order.
     * @param service The service associated with the order.
     * @param client  The client who made the order.
     * @param driver  The driver assigned to the order.
     * @param company The company that owns the order.
     * @param datetime The date and time when the order was made.
     */
    public Order(Integer id, double totalKm, Service service, Client client, Driver driver, Company company, Date datetime) {
        this.id = id;
        this.totalKm = totalKm;
        this.service = service;
        this.client = client;
        this.driver = driver;
        this.company = company;
        this.datetime = datetime;
    }

    /**
     * Retrieves the unique identifier for the order.
     *
     * @return The ID of the order.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the order.
     *
     * @param id The ID to set for the order.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the service associated with the order.
     *
     * @return The service for this order.
     */
    public Service getService() {
        return service;
    }

    /**
     * Sets the service associated with the order.
     *
     * @param service The service to set for the order.
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * Retrieves the total distance in kilometers for the order.
     *
     * @return The total kilometers for the order.
     */
    public double getTotalKm() {
        return totalKm;
    }

    /**
     * Sets the total distance in kilometers for the order.
     *
     * @param totalKm The total kilometers to set for the order.
     */
    public void setTotalKm(double totalKm) {
        this.totalKm = totalKm;
    }

    /**
     * Retrieves the client who made the order.
     *
     * @return The client associated with the order.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the client associated with the order.
     *
     * @param client The client to set for the order.
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Retrieves the driver assigned to the order.
     *
     * @return The driver for the order.
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Sets the driver assigned to the order.
     *
     * @param driver The driver to set for the order.
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the company that owns the order.
     *
     * @return The company associated with the order.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets the company that owns the order.
     *
     * @param company The company to set for the order.
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Retrieves the date and time when the order was made.
     *
     * @return The datetime of the order.
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * Sets the date and time when the order was made.
     *
     * @param datetime The datetime to set for the order.
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * Returns a string representation of the Order object in the format:
     * "id,totalKm,service,client,driver,company,datetime".
     *
     * @return A string representation of the Order instance.
     */
    @Override
    public String toString() {
        return id + "," + totalKm + "," + service + "," + client + "," + driver + "," + company + "," + datetime;
    }

    /**
     * Parses a string representation of an Order object and returns a new Order instance.
     * The expected string format is:
     * "id,totalKm,service,client,driver,company,datetime".
     *
     * @param line The string representation of the Order object.
     * @return A new Order object created from the string data.
     * @throws ParseException If there is an error parsing the date value.
     * @throws NumberFormatException If the ID or totalKm is not a valid number.
     */
    public static Order parse(String line) throws ParseException {
        String[] fields = line.split(",");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return new Order(
                Integer.parseInt(fields[0]),
                Double.parseDouble(fields[1]),
                Service.parse(fields[2]),
                Client.parse(fields[3]),
                Driver.parse(fields[4]),
                Company.parse(fields[5]),
                dateFormat.parse(fields[6])
        );
    }
}
