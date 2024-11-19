package org.example.models;

import java.util.Date;

public class Order implements HasId {

    private int id;
    private Service service;
    private double totalKm;
    private Client client;
    private Driver driver;
    private Company company;
    private Date datetime;


    public Order(int id,Service service, double totalKm, Client client, Driver driver, Company company, Date datetime) {

        this.id = id;
        this.service = service;
        this.totalKm = totalKm;
        this.client = client;
        this.driver = driver;
        this.company = company;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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
                "service=" + service +
                ", totalKm=" + totalKm +
                ", client=" + client +
                ", driver=" + driver +
                ", company=" + company +
                ", datetime=" + datetime +
                '}';
    }

}
