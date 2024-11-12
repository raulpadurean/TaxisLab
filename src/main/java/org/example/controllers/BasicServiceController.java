package org.example.controllers;

import org.example.models.BasicService;
import org.example.services.BasicServiceService;

import java.util.List;

public class BasicServiceController {
    private final BasicServiceService basicServiceService;

    public BasicServiceController(BasicServiceService basicServiceService) {
        this.basicServiceService = basicServiceService;
    }

    public void addBasicService(int id, String name, double pricePerKm) {
        BasicService basicService = new BasicService(id, name, pricePerKm); // ID is set by repository
        basicServiceService.addBasicService(basicService);
    }

    public BasicService getBasicService(int id) {
        return basicServiceService.getBasicService(id);
    }

    public List<BasicService> getAllCompanies() {
        return basicServiceService.getAllCompanies();
    }

    public void updateBasicService(BasicService basicService) {
        basicServiceService.updateBasicService(basicService);
    }

    public void deleteBasicService(int id) {
        basicServiceService.deleteBasicService(id);
    }
}
