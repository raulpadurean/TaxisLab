package org.example.models;

import java.util.Date;

public class Order {

    private int serviceId;
    private double totalKm;
    private int clientId;
    private int driverId;
    private int companyId;
    private Date datetime;

    public Order(int serviceId, double totalKm, int clientId, int driverId, int companyId, Date datetime) {
        this.serviceId = serviceId;
        this.totalKm = totalKm;
        this.clientId = clientId;
        this.driverId = driverId;
        this.companyId = companyId;
        this.datetime = datetime;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public double getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(double totalKm) {
        this.totalKm = totalKm;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

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
