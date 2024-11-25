package org.example.services;

import org.example.models.Company;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * Service class for managing Company entities.
 * Provides functionality to add, retrieve, update, and delete companies.
 */
public class CompanyService {

    private final IRepository<Company> companyRepository;

    /**
     * Constructs a CompanyService with the specified company repository.
     *
     * @param companyRepository The repository for managing Company objects.
     */
    public CompanyService(IRepository<Company> companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Adds a new company with the provided details.
     * Automatically generates a unique ID for the company.
     *
     * @param name    The name of the company.
     * @param email   The email address of the company.
     * @param address The address of the company.
     * @param phone   The phone number of the company.
     */
    public void addCompany(String name, String email, String address, String phone) {
        int id = companyRepository.readAll().size() + 1; // Generate a unique ID
        Company company = new Company(id, name, email, address, phone); // Create company object
        companyRepository.create(company); // Save it in the repository
    }

    /**
     * Retrieves a company by its ID.
     *
     * @param id The ID of the company to retrieve.
     * @return The Company object with the specified ID, or null if not found.
     */
    public Company getCompany(int id) {
        return companyRepository.read(id);
    }

    /**
     * Retrieves all companies in the repository.
     *
     * @return A list of all Company objects.
     */
    public List<Company> getAllCompanies() {
        return companyRepository.readAll();
    }

    /**
     * Updates an existing company's details.
     *
     * @param id      The ID of the company to update.
     * @param name    The updated name of the company.
     * @param email   The updated email address of the company.
     * @param address The updated address of the company.
     * @param phone   The updated phone number of the company.
     * @throws IllegalArgumentException If the company with the specified ID does not exist.
     */
    public void updateCompany(int id, String name, String email, String address, String phone) {
        Company existingCompany = companyRepository.read(id);
        if (existingCompany == null) {
            throw new IllegalArgumentException("Company with ID " + id + " does not exist.");
        }

        Company updatedCompany = new Company(id, name, email, address, phone); // Create updated object
        companyRepository.update(updatedCompany); // Update repository
    }

    /**
     * Deletes a company by its ID.
     *
     * @param companyId The ID of the company to delete.
     * @throws IllegalArgumentException If the company with the specified ID does not exist.
     */
    public void deleteCompany(Integer companyId) {
        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        companyRepository.delete(companyId); // Remove from repository
    }
}
