package org.example.services;

import org.example.models.CompanyDriver;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * A service class responsible for managing {@link CompanyDriver} entities.
 * Provides business logic to interact with the underlying repository for company-driver operations.
 *
 * <p>This class handles operations such as adding, retrieving, updating, and deleting company-driver relationships.</p>
 */
public class CompanyDriverService {

    private final IRepository<CompanyDriver> companyDriverRepository;

    /**
     * Constructs a {@link CompanyDriverService} with the specified repository.
     *
     * @param companyDriverRepository The repository to be used for storing and retrieving company-driver relationships.
     */
    public CompanyDriverService(IRepository<CompanyDriver> companyDriverRepository) {
        this.companyDriverRepository = companyDriverRepository;
    }

    /**
     * Adds a new company-driver relationship to the repository.
     *
     * <p>This method persists the provided {@link CompanyDriver} entity to the repository.</p>
     *
     * @param companyDriver The company-driver relationship to be added. Must not be {@code null}.
     */
    public void addCompanyDriver(CompanyDriver companyDriver) {
        companyDriverRepository.create(companyDriver);
    }

    /**
     * Retrieves a company-driver relationship by its unique ID.
     *
     * @param Id The unique identifier of the company-driver relationship to retrieve.
     * @return The company-driver relationship with the specified ID, or {@code null} if no such relationship exists.
     */
    public CompanyDriver getCompanyDriver(int Id) {
        return companyDriverRepository.read(Id);
    }

    /**
     * Retrieves all company-driver relationships from the repository.
     *
     * @return A list of all company-driver relationships in the repository.
     */
    public List<CompanyDriver> getAllCompanyDrivers() {
        return companyDriverRepository.getAll();
    }

    /**
     * Updates an existing company-driver relationship in the repository.
     *
     * <p>If the company-driver relationship already exists in the repository, it will be updated with the new values.</p>
     *
     * @param companyDriver The company-driver relationship to update. The relationship must not be {@code null}.
     */
    public void updateCompanyDriver(CompanyDriver companyDriver) {
        companyDriverRepository.update(companyDriver);
    }

    /**
     * Deletes a company-driver relationship from the repository.
     *
     * @param companyDriverId The unique ID of the company-driver relationship to be deleted.
     */
    public void deleteCompanyDriver(Integer companyDriverId) {
        companyDriverRepository.delete(companyDriverId);
    }
}
