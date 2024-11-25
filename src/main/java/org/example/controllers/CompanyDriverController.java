package org.example.controllers;

import org.example.models.CompanyDriver;
import org.example.services.CompanyDriverService;

import java.util.List;

/**
 * Controller class responsible for handling CRUD operations for {@link CompanyDriver} objects.
 * The controller delegates the actual business logic to the {@link CompanyDriverService} class.
 * This class provides methods to add, retrieve, update, and delete company-driver associations.
 */
public class CompanyDriverController {

    private final CompanyDriverService companyDriverService;

    /**
     * Constructs a {@link CompanyDriverController} with the specified {@link CompanyDriverService}.
     * This service is used to delegate the business logic related to company-driver associations.
     *
     * @param companyDriverService The {@link CompanyDriverService} to delegate the business logic.
     */
    public CompanyDriverController(CompanyDriverService companyDriverService) {
        this.companyDriverService = companyDriverService;
    }

    /**
     * Adds a new {@link CompanyDriver} association between a driver and a company.
     * Delegates to the {@link CompanyDriverService} to perform the actual operation.
     *
     * @param driverId The ID of the driver to associate with the company.
     * @param companyId The ID of the company to associate with the driver.
     */
    public void addCompanyDriver(Integer driverId, int companyId) {
        companyDriverService.addCompanyDriver(driverId, companyId);
    }

    /**
     * Retrieves a {@link CompanyDriver} by its unique identifier.
     * Delegates to the {@link CompanyDriverService} to perform the actual retrieval.
     *
     * @param id The unique identifier of the company-driver association.
     * @return The {@link CompanyDriver} object with the specified ID, or {@code null} if not found.
     */
    public CompanyDriver getCompanyDriver(int id) {
        return companyDriverService.getCompanyDriver(id);
    }

    /**
     * Retrieves all {@link CompanyDriver} associations.
     * Delegates to the {@link CompanyDriverService} to perform the actual retrieval.
     *
     * @return A list of all {@link CompanyDriver} associations.
     */
    public List<CompanyDriver> getAllCompanyDrivers() {
        return companyDriverService.getAllCompanyDrivers();
    }

    /**
     * Updates the details of an existing {@link CompanyDriver} association.
     * Delegates to the {@link CompanyDriverService} to perform the actual update.
     *
     * @param id        The unique identifier of the company-driver association to update.
     * @param driverId  The new driver ID to associate with the company.
     * @param companyId The new company ID to associate with the driver.
     */
    public void updateCompanyDriver(int id, int driverId, int companyId) {
        companyDriverService.updateCompanyDriver(id, companyId, driverId);
    }

    /**
     * Deletes a {@link CompanyDriver} by its unique identifier.
     * Delegates to the {@link CompanyDriverService} to perform the actual deletion.
     *
     * @param companyDriverId The unique identifier of the company-driver association to delete.
     */
    public void deleteCompanyDriver(Integer companyDriverId) {
        companyDriverService.deleteCompanyDriver(companyDriverId);
    }
}
