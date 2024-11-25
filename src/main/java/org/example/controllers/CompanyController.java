package org.example.controllers;

import org.example.models.Company;
import org.example.services.CompanyService;

import java.util.List;

/**
 * Controller class responsible for handling CRUD operations for {@link Company} objects.
 * The controller delegates the actual business logic to the {@link CompanyService} class.
 * This class provides methods to add, retrieve, update, and delete companies.
 */
public class CompanyController {

    private final CompanyService companyService;

    /**
     * Constructs a {@link CompanyController} with the specified {@link CompanyService}.
     * This service is used to delegate the business logic related to companies.
     *
     * @param companyService The {@link CompanyService} to delegate the business logic.
     */
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * Adds a new {@link Company} with the specified details.
     * Delegates to the {@link CompanyService} to perform the actual operation.
     *
     * @param name    The name of the company.
     * @param email   The email of the company.
     * @param address The address of the company.
     * @param phone   The phone number of the company.
     */
    public void addCompany(String name, String email, String address, String phone) {
        companyService.addCompany(name, email, address, phone); // Delegate to service
    }

    /**
     * Retrieves a {@link Company} by its unique identifier.
     * Delegates to the {@link CompanyService} to perform the actual retrieval.
     *
     * @param id The unique identifier of the company.
     * @return The {@link Company} object with the specified ID, or {@code null} if not found.
     */
    public Company getCompany(int id) {
        return companyService.getCompany(id);
    }

    /**
     * Retrieves all {@link Company} objects.
     * Delegates to the {@link CompanyService} to perform the actual retrieval.
     *
     * @return A list of all {@link Company} objects.
     */
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    /**
     * Updates the details of an existing {@link Company}.
     * Delegates to the {@link CompanyService} to perform the actual update.
     *
     * @param id      The unique identifier of the company to update.
     * @param name    The new name of the company.
     * @param email   The new email of the company.
     * @param address The new address of the company.
     * @param phone   The new phone number of the company.
     */
    public void updateCompany(int id, String name, String email, String address, String phone) {
        companyService.updateCompany(id, name, email, address, phone);
    }

    /**
     * Deletes a {@link Company} by its unique identifier.
     * Delegates to the {@link CompanyService} to perform the actual deletion.
     *
     * @param companyId The unique identifier of the company to delete.
     */
    public void deleteCompany(Integer companyId) {
        companyService.deleteCompany(companyId);
    }
}
