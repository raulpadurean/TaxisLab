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

    public CompanyDriver getCompanyDriver(int Id) {
        return companyDriverRepository.read(Id);
    }

    public List<CompanyDriver> getAllCompanyDrivers() {
        return companyDriverRepository.readAll();
    }

    public void updateCompanyDriver(CompanyDriver companyDriver) {
        companyDriverRepository.update(companyDriver);
    }

    public void deleteCompanyDriver(Integer companyDriverId) {

        companyDriverRepository.delete(companyDriverId);
    }
}
