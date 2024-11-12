package org.example.controllers;

import org.example.models.CustomService;
import org.example.services.CustomServiceService;

import java.util.List;

public class CustomServiceController {
    private final CustomServiceService customServiceService;

    public CustomServiceController(CustomServiceService customServiceService) {
        this.customServiceService = customServiceService;
    }

    public void addCustomService(int id, String name, double pricePerKm, String extras) {
        CustomService customService = new CustomService(id, name, pricePerKm, extras); // ID is set by repository
        customServiceService.addCustomService(customService);
    }

    public CustomService getCustomService(int id) {
        return customServiceService.getCustomService(id);
    }

    public List<CustomService> getAllCustomServices() {
        return customServiceService.getAllCustomServices();
    }

    public void updateCustomService(CustomService customService) {
        customServiceService.updateCustomService(customService);
    }

    public void deleteCustomService(int id) {
        customServiceService.deleteCustomService(id);
    }
}
