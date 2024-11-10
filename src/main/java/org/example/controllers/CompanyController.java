package org.example.controllers;

import org.example.models.Company;
import org.example.services.CompanyService;

import java.util.List;

public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    public void addCompany(String name, String email, String address, String phone) {
        Company company = new Company(0, name, email, address, phone); // ID is set by repository
        companyService.addCompany(company);
    }

    public Company getCompany(int id) {
        return companyService.getCompany(id);
    }

    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    public void updateCompany(Company company) {
        companyService.updateCompany(company);
    }

    public void deleteCompany(int id) {
        companyService.deleteCompany(id);
    }
}
