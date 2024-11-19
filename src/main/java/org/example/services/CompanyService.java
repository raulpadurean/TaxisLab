package org.example.services;

import org.example.models.Company;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * A service class responsible for managing {@link Company} entities.
 * Provides business logic to interact with the underlying repository for company-related operations.
 *
 * <p>This class handles operations such as adding, retrieving, updating, and deleting companies.</p>
 */
public class CompanyService {
    private final IRepository<Company> companyRepository;

    /**
     * Constructs a {@link CompanyService} with the specified repository.
     *
     * @param companyRepository The repository to be used for storing and retrieving company entities.
     */
    public CompanyService(IRepository<Company> companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Adds a new company to the repository.
     *
     * <p>This method persists the provided {@link Company} entity to the repository.</p>
     *
     * @param company The company to be added. Must not be {@code null}.
     */
    public void addCompany(Company company) {
        companyRepository.create(company);
    }

    /**
     * Retrieves a company by its unique ID.
     *
     * @param id The unique identifier of the company to retrieve.
     * @return The company with the specified ID, or {@code null} if no such company exists.
     */
    public Company getCompany(int id) {
        return companyRepository.read(id);
    }

    /**
     * Retrieves all companies from the repository.
     *
     * @return A list of all companies in the repository.
     */
    public List<Company> getAllCompanies() {
        return companyRepository.getAll();
    }

    /**
     * Updates an existing company in the repository.
     *
     * <p>If the company already exists in the repository, it will be updated with the new values.</p>
     *
     * @param company The company to update. The company must not be {@code null}.
     */
    public void updateCompany(Company company) {
        companyRepository.update(company);
    }

    /**
     * Deletes a company from the repository.
     *
     * @param companyId The unique ID of the company to be deleted.
     */
    public void deleteCompany(Integer companyId) {
        companyRepository.delete(companyId);
    }
}
