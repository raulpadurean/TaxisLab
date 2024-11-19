package org.example.models;

import java.util.Date;

/**
 * Represents an order for a service made by a client.
 *
 * <p>An {@code Order} contains details such as the service ID, total kilometers traveled,
 * client ID, driver ID, company ID, and the date and time of the order.</p>
 *
 * <p>This class implements {@link HasId}, allowing each order to have a unique identifier
 * accessible via the {@code getId()} method.</p>
 */
public class Order implements HasId {

    private Integer serviceId;
    private double totalKm;
    private int clientId;
    private int driverId;
    private int companyId;
    private Date datetime;

    /**
     * Constructs an {@code Order} instance with the specified details.
     *
     * @param serviceId The ID of the service being ordered.
     * @param totalKm   The total kilometers traveled for the order.
     * @param clientId  The ID of the client placing the order.
     * @param driverId  The ID of the driver handling the order.
     * @param companyId The ID of the company associated with the order.
     * @param datetime  The date and time of the order.
     */
    public Order(Integer serviceId, double totalKm, int clientId, int driverId, int companyId, Date datetime) {
        this.serviceId = serviceId;
        this.totalKm = totalKm;
        this.clientId = clientId;
        this.driverId = driverId;
        this.companyId = companyId;
        this.datetime = datetime;
    }

    /**
     * Gets the ID of the service associated with this order.
     *
     * @return The service ID.
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * Sets the ID of the service associated with this order.
     *
     * @param serviceId The service ID to set.
     */
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * Gets the total kilometers traveled for this order.
     *
     * @return The total kilometers.
     */
    public double getTotalKm() {
        return totalKm;
    }

    /**
     * Sets the total kilometers traveled for this order.
     *
     * @param totalKm The total kilometers to set.
     */
    public void setTotalKm(double totalKm) {
        this.totalKm = totalKm;
    }

    /**
     * Gets the ID of the client who placed the order.
     *
     * @return The client ID.
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets the ID of the client who placed the order.
     *
     * @param clientId The client ID to set.
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the ID of the driver assigned to this order.
     *
     * @return The driver ID.
     */
    public int getDriverId() {
        return driverId;
    }

    /**
     * Sets the ID of the driver assigned to this order.
     *
     * @param driverId The driver ID to set.
     */
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    /**
     * Gets the ID of the company associated with this order.
     *
     * @return The company ID.
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * Sets the ID of the company associated with this order.
     *
     * @param companyId The company ID to set.
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * Gets the date and time of this order.
     *
     * @return The date and time.
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * Sets the date and time of this order.
     *
     * @param datetime The date and time to set.
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * Gets the unique identifier of the order, which corresponds to the service ID.
     *
     * @return The unique identifier (service ID).
     */

    public Integer getId() {
        return serviceId;
    }

    /**
     * Returns a string representation of the {@code Order}.
     *
     * @return A string describing the order.
     */
    @Override
    public String toString() {
        return "Order{" +
                "serviceId=" + serviceId +
                ", totalKm=" + totalKm +
                ", clientId=" + clientId +
                ", driverId=" + driverId +
                ", companyId=" + companyId +
                ", datetime=" + datetime +
                '}';
    }
}
