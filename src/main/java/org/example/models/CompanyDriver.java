package org.example.models;

import java.security.cert.CertPath;

public class CompanyDriver {
    private Driver driver;
    private Company company;

    public CompanyDriver(Driver driver, Company company) {
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

    @Override
    public String toString() {
        return driver + "," + company;
    }

    public CompanyDriver parse(String line) {
        String[] fields = line.split(",");
        return new CompanyDriver(
                Driver.parse(fields[0]),
                Company.parse(fields[1])
        );
    }
}
