package org.example.controllers;

import org.example.models.BasicService;
import org.example.services.BasicServiceService;

import java.util.List;

public class BasicServiceController {
    private final BasicServiceService basicServiceService;

    public BasicServiceController(BasicServiceService basicServiceService) {
        this.basicServiceService = basicServiceService;
    }

    public void addBasicService(String name, double pricePerKm) {
        basicServiceService.addBasicService(name, pricePerKm); // Delegate to service
    }

    public BasicService getBasicService(int id) {
        return basicServiceService.getBasicService(id);
    }

    public List<BasicService> getAllBasicServices() {
        return basicServiceService.getAllBasicServices();
    }

    public void updateBasicService(int id, String name, double pricePerKm) {
        basicServiceService.updateBasicService(id, name, pricePerKm);
    }

    public void deleteBasicService(Integer basicServiceId) {
        basicServiceService.deleteBasicService(basicServiceId);
    }
}
