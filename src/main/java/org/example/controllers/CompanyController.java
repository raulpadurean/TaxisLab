package org.example.controllers;

import org.example.models.Company;
import org.example.services.CompanyService;

import java.util.List;

public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Add a company using service logic for ID generation
    public void addCompany(String name, String email, String address, String phone) {
        companyService.addCompany(name, email, address, phone);
    }

    // Get a specific company by ID
    public Company getCompany(int id) {
        return companyService.getCompany(id);
    }

    // Get all companies
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    // Update a company's details
    public void updateCompany(int id, String name, String email, String address, String phone) {
        companyService.updateCompany(id, name, email, address, phone);
    }

    // Delete a company by ID
    public void deleteCompany(Integer companyId) {
        companyService.deleteCompany(companyId);
    }
}
