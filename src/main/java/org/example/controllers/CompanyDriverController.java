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
        companyDriverService.addCompanyDriver(driverId, companyId);
    }

    public CompanyDriver getCompanyDriver(int id){
        return companyDriverService.getCompanyDriver(id);
    }

    public List<CompanyDriver> getAllCompanyDrivers(){
        return companyDriverService.getAllCompanyDrivers();
    }

    public void updateCompanyDriver(int id, int driverId, int companyId){
        companyDriverService.updateCompanyDriver(id, companyId, driverId);
    }

    public void deleteCompanyDriver(Integer companyDriverId) {
        companyDriverService.deleteCompanyDriver(companyDriverId);

    }

}
