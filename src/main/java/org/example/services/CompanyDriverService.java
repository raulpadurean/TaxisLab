package org.example.services;

import org.example.models.CompanyDriver;
import org.example.models.Driver;
import org.example.models.Company;
import org.example.repositories.IRepository;

import java.util.List;

public class CompanyDriverService {

    private final IRepository<CompanyDriver> companyDriverRepository;
    private final IRepository<Driver> driverRepository;
    private final IRepository<Company> companyRepository;

    public CompanyDriverService(IRepository<CompanyDriver> companyDriverRepository,
                                IRepository<Driver> driverRepository,
                                IRepository<Company> companyRepository) {
        this.companyDriverRepository = companyDriverRepository;
        this.driverRepository = driverRepository;
        this.companyRepository = companyRepository;
    }

    public void addCompanyDriver(int driverId, int companyId) {
        // Validate if the driver exists
        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver with ID " + driverId + " does not exist.");
        }

        // Validate if the company exists
        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        // Create a new CompanyDriver instance
        Integer id = companyDriverRepository.readAll().size() + 1;
        CompanyDriver companyDriver = new CompanyDriver(id, company, driver);

        // Save the CompanyDriver instance
        companyDriverRepository.create(companyDriver);
    }

    public CompanyDriver getCompanyDriver(int id) {
        return companyDriverRepository.read(id);
    }

    public List<CompanyDriver> getAllCompanyDrivers() {
        return companyDriverRepository.readAll();
    }

    public void updateCompanyDriver(int id, int driverId, int companyId) {
        // Validate if the CompanyDriver exists
        CompanyDriver existingCompanyDriver = companyDriverRepository.read(id);
        if (existingCompanyDriver == null) {
            throw new IllegalArgumentException("CompanyDriver with ID " + id + " does not exist.");
        }

        // Validate if the driver exists
        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver with ID " + driverId + " does not exist.");
        }

        // Validate if the company exists
        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        // Update the CompanyDriver
        CompanyDriver updatedCompanyDriver = new CompanyDriver(id, company, driver);
        companyDriverRepository.update(updatedCompanyDriver);
    }

    public void deleteCompanyDriver(Integer companyDriverId) {
        companyDriverRepository.delete(companyDriverId);
    }
}
