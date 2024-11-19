package org.example.models;

import java.util.Date;

public class Order {

    private int serviceId;
    private double totalKm;
    private Client client;
    private Driver driver;
    private Company company;
    private Date datetime;

    public Order(int serviceId, double totalKm, Client client, Driver driver, Company company, Date datetime) {
        this.serviceId = serviceId;
        this.totalKm = totalKm;
        this.client = client;
        this.driver = driver;
        this.company = company;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
                ", client=" + client +
                ", driver=" + driver +
                ", company=" + company +
                ", datetime=" + datetime +
                '}';
    }
}
