package org.example.services;

import org.example.models.Company;
import org.example.repositories.IRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        return companyRepository.getAll();
    }

    public void updateCompany(Company company) {
        companyRepository.update(company);
    }

    public void deleteCompany(Integer companyId) {

        companyRepository.delete(companyId);
    }
    public List<Company> sortCompaniesByName() {
        return companyRepository.getAll().stream()
                .sorted(Comparator.comparing(Company::getName))
                .collect(Collectors.toList());
    }
}
