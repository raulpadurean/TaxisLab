package org.example.services;

import org.example.models.Company;
import org.example.repositories.IRepository;

import java.util.List;

public class CompanyService {
    private final IRepository<Company> companyRepository;

    public CompanyService(IRepository<Company> companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void addCompany(Company company) {
        companyRepository.create(company);
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
