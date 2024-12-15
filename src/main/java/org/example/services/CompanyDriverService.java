package org.example.services;

import org.example.models.Company;
import org.example.models.CompanyDriver;
import org.example.models.Driver;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * Service class for managing the relationship between companies and drivers.
 * Provides methods to add, retrieve, update, and delete CompanyDriver objects.
 */
public class CompanyDriverService {

    private final IRepository<CompanyDriver> companyDriverRepository;
    private final IRepository<Driver> driverRepository;
    private final IRepository<Company> companyRepository;

    /**
     * Constructs a CompanyDriverService with the specified repositories.
     *
     * @param companyDriverRepository The repository for managing CompanyDriver objects.
     * @param driverRepository        The repository for managing Driver objects.
     * @param companyRepository       The repository for managing Company objects.
     */
    public CompanyDriverService(IRepository<CompanyDriver> companyDriverRepository,
                                IRepository<Driver> driverRepository,
                                IRepository<Company> companyRepository) {
        this.companyDriverRepository = companyDriverRepository;
        this.driverRepository = driverRepository;
        this.companyRepository = companyRepository;
    }

    /**
     * Adds a new CompanyDriver relationship.
     * Validates that both the driver and company exist before creating the relationship.
     *
     * @param driverId  The ID of the driver.
     * @param companyId The ID of the company.
     * @throws IllegalArgumentException If the driver or company does not exist.
     */
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

    /**
     * Retrieves a CompanyDriver by its ID.
     *
     * @param id The ID of the CompanyDriver.
     * @return The CompanyDriver object with the specified ID, or null if not found.
     */
    public CompanyDriver getCompanyDriver(int id) {
        return companyDriverRepository.read(id);
    }

    /**
     * Retrieves all CompanyDriver relationships.
     *
     * @return A list of all CompanyDriver objects.
     */
    public List<CompanyDriver> getAllCompanyDrivers() {
        return companyDriverRepository.readAll();
    }

    /**
     * Updates an existing CompanyDriver relationship.
     * Validates that the CompanyDriver, driver, and company exist before performing the update.
     *
     * @param id        The ID of the CompanyDriver to update.
     * @param driverId  The ID of the new driver.
     * @param companyId The ID of the new company.
     * @throws IllegalArgumentException If the CompanyDriver, driver, or company does not exist.
     */
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

    /**
     * Deletes a CompanyDriver relationship by its ID.
     *
     * @param companyDriverId The ID of the CompanyDriver to delete.
     */
    public void deleteCompanyDriver(Integer companyDriverId) {
        companyDriverRepository.delete(companyDriverId);
    }
}
