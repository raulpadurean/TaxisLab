package org.example.controllers;

import org.example.models.Company;
import org.example.services.CompanyService;

import java.util.List;

/**
 * Controller class for managing Company entities.
 * Provides methods to handle operations such as adding, retrieving, updating,
 * and deleting companies. This class interacts with the {@link CompanyService}
 * to perform the required business logic.
 */
public class CompanyController {

    private final CompanyService companyService;

    /**
     * Constructs a new {@code CompanyController} with the specified service.
     *
     * @param companyService the service instance for handling company operations
     */
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * Adds a new Company.
     *
     * @param id      the unique identifier of the company
     * @param name    the name of the company
     * @param email   the email address of the company
     * @param address the physical address of the company
     * @param phone   the phone number of the company
     */
    public void addCompany(Integer id, String name, String email, String address, String phone) {
        Company company = new Company(id, name, email, address, phone);
        companyService.addCompany(company);
    }

    /**
     * Retrieves a Company by its ID.
     *
     * @param id the unique identifier of the company
     * @return the {@link Company} object with the specified ID, or {@code null} if not found
     */
    public Company getCompany(int id) {
        return companyService.getCompany(id);
    }

    /**
     * Retrieves all Companies.
     *
     * @return a list of all {@link Company} objects
     */
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    /**
     * Updates an existing Company using its attributes.
     *
     * @param id      the unique identifier of the company
     * @param name    the name of the company
     * @param email   the email address of the company
     * @param address the physical address of the company
     * @param phone   the phone number of the company
     */
    public void updateCompany(Integer id, String name, String email, String address, String phone) {
        Company company = new Company(id, name, email, address, phone);
        companyService.updateCompany(company);
    }

    /**
     * Deletes a Company by its ID.
     *
     * @param companyId the unique identifier of the company to be deleted
     */
    public void deleteCompany(Integer companyId) {
        companyService.deleteCompany(companyId);
    }
}
