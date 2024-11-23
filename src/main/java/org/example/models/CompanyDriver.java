package org.example.models;

import java.security.cert.CertPath;

public class CompanyDriver implements HasId {
    private Integer id;
    private Driver driver;
    private Company company;

    public CompanyDriver(Integer id, Driver driver, Company company) {
        this.id = id;
        this.driver = driver;
        this.company = company;
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

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return driver + "," + company;
    }

    public CompanyDriver parse(String line) {
        String[] fields = line.split(",");
        return new CompanyDriver(
                Integer.parseInt(fields[0]),
                Driver.parse(fields[0]),
                Company.parse(fields[1])
        );
    }

    @Override
    public Integer getId() {
        return id;
    }
}
