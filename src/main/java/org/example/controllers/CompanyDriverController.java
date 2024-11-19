package org.example.controllers;


import org.example.models.CompanyDriver;

import org.example.services.CompanyDriverService;

import java.util.List;

public class CompanyDriverController {

    private final CompanyDriverService companyDriverService;

    public CompanyDriverController(CompanyDriverService companyDriverService) {
        this.companyDriverService = companyDriverService;
    }


    public void addCompanyDriver(Integer driverId, int companyId) {
        CompanyDriver companyDriver = new CompanyDriver(driverId,companyId);
        companyDriverService.addCompanyDriver(companyDriver);
    }

    public CompanyDriver getCompanyDriver(int Id){
        return companyDriverService.getCompanyDriver(Id);
    }

    public List<CompanyDriver> getAllCompanyDrivers(){
        return companyDriverService.getAllCompanyDrivers();
    }

    public void updateCompanyDriver(Integer driverId, int companyId) {
        CompanyDriver companyDriver = new CompanyDriver(driverId,companyId);
        companyDriverService.updateCompanyDriver(companyDriver);
    }
    public void deleteCompanyDriver(Integer companyDriverId) {
        companyDriverService.deleteCompanyDriver(companyDriverId);

    }

}
