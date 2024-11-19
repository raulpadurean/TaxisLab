package org.example.controllers;

import org.example.models.CompanyDriver;
import org.example.services.CompanyDriverService;

import java.util.List;

/**
 * Controller class for managing CompanyDriver entities.
 * This class provides methods to handle operations such as adding, retrieving,
 * updating, and deleting company-driver associations. It interacts with the
 * {@link CompanyDriverService} to perform the required business logic.
 */
public class CompanyDriverController {

    private final CompanyDriverService companyDriverService;

    /**
     * Constructs a new {@code CompanyDriverController} with the specified service.
     *
     * @param companyDriverService the service instance for handling company-driver operations
     */
    public CompanyDriverController(CompanyDriverService companyDriverService) {
        this.companyDriverService = companyDriverService;
    }

    /**
     * Adds a new association between a driver and a company.
     *
     * @param driverId  the unique identifier of the driver
     * @param companyId the unique identifier of the company
     */
    public void addCompanyDriver(Integer driverId, int companyId) {
        CompanyDriver companyDriver = new CompanyDriver(driverId, companyId);
        companyDriverService.addCompanyDriver(companyDriver);
    }

    /**
     * Retrieves a CompanyDriver association by its ID.
     *
     * @param id the unique identifier of the company-driver association
     * @return the {@link CompanyDriver} object with the specified ID, or {@code null} if not found
     */
    public CompanyDriver getCompanyDriver(int id) {
        return companyDriverService.getCompanyDriver(id);
    }

    /**
     * Retrieves all CompanyDriver associations.
     *
     * @return a list of all {@link CompanyDriver} objects
     */
    public List<CompanyDriver> getAllCompanyDrivers() {
        return companyDriverService.getAllCompanyDrivers();
    }

    /**
     * Updates an existing association between a driver and a company.
     *
     * @param driverId  the unique identifier of the driver
     * @param companyId the unique identifier of the company
     */
    public void updateCompanyDriver(Integer driverId, int companyId) {
        CompanyDriver companyDriver = new CompanyDriver(driverId, companyId);
        companyDriverService.updateCompanyDriver(companyDriver);
    }

    /**
     * Deletes a CompanyDriver association by its ID.
     *
     * @param companyDriverId the unique identifier of the company-driver association to be deleted
     */
    public void deleteCompanyDriver(Integer companyDriverId) {
        companyDriverService.deleteCompanyDriver(companyDriverId);
    }
}
