package org.example.services;

import org.example.models.Company;
import org.example.repositories.IRepository;

import java.util.List;

public class CompanyService {
    private final IRepository<Company> companyRepository;

    public CompanyService(IRepository<Company> companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Add a new company with automatic ID generation
    public void addCompany(String name, String email, String address, String phone) {
        int id = companyRepository.readAll().size() + 1; // Generate a unique ID
        Company company = new Company(id, name, email, address, phone); // Create company object
        companyRepository.create(company); // Save it in the repository
    }

    // Get a company by ID
    public Company getCompany(int id) {
        return companyRepository.read(id);
    }

    // Get all companies
    public List<Company> getAllCompanies() {
        return companyRepository.readAll();
    }

    // Update a company by creating a new Company object and saving it
    public void updateCompany(int id, String name, String email, String address, String phone) {
        Company existingCompany = companyRepository.read(id);
        if (existingCompany == null) {
            throw new IllegalArgumentException("Company with ID " + id + " does not exist.");
        }

        Company updatedCompany = new Company(id, name, email, address, phone); // Create updated object
        companyRepository.update(updatedCompany); // Update repository
    }

    // Delete a company by ID
    public void deleteCompany(Integer companyId) {
        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        companyRepository.delete(companyId); // Remove from repository
    }
}
