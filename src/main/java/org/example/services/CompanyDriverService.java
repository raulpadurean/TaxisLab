package org.example.services;

import org.example.models.CompanyDriver;
import org.example.repositories.IRepository;
import java.util.List;

public class CompanyDriverService {

    private final IRepository<CompanyDriver> companyDriverRepository;

    public CompanyDriverService(IRepository<CompanyDriver> companyDriverRepository) {
        this.companyDriverRepository = companyDriverRepository;
    }

    public void addCompanyDriver(CompanyDriver companyDriver) {
        companyDriverRepository.create(companyDriver);
    }

    public CompanyDriver getCompanyDriver(int id) {
        return companyDriverRepository.read(id);
    }

    public List<CompanyDriver> getAllCompanyDrivers() {
        return companyDriverRepository.getAll();
    }

    public void updateCompanyDriver(CompanyDriver companyDriver) {
        companyDriverRepository.update(companyDriver);
    }

    public void deleteCompanyDriver(int id) {
        companyDriverRepository.delete(id);
    }
}
