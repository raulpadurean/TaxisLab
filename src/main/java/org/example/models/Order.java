package org.example.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements HasId {

    private Integer id;
    private Service service;
    private double totalKm;
    private Client client;
    private Driver driver;
    private Company company;
    private Date datetime;


    public Order(Integer id,  double totalKm, Service service, Client client, Driver driver, Company company, Date datetime) {

        this.id = id;
        this.totalKm = totalKm;
        this.service = service;
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
        return id + "," + totalKm + "," + service + "," + client + "," + driver + "," + company + "," + datetime;
    }

    public static Order parse(String line)  throws ParseException {
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
