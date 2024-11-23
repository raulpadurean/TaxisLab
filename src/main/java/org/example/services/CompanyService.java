package org.example.services;

import org.example.models.Company;
import org.example.repositories.IRepository;

import java.util.List;

public class CompanyService {
    private final IRepository<Company> companyRepository;

    public CompanyService(IRepository<Company> companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Add a company and generate an ID automatically
    public void addCompany(String name, String email, String address, String phone) {
        int id = companyRepository.readAll().size() + 1; // Generate ID based on current size
        Company company = new Company(id, name, email, address, phone); // Create a new company object
        companyRepository.create(company); // Save the company
    }

    public Company getCompany(int id) {
        return companyRepository.read(id);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.readAll();
    }

    public void updateCompany(Company company) {
        companyRepository.update(company);
    }

    public void deleteCompany(Integer companyId) {
        companyRepository.delete(companyId);
    }
}
