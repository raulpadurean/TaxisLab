package org.example.controllers;

import org.example.models.CustomService;
import org.example.services.CustomServiceService;

import java.util.List;

public class CustomServiceController {
    private final CustomServiceService customServiceService;

    public CustomServiceController(CustomServiceService customServiceService) {
        this.customServiceService = customServiceService;
    }

    public void addCustomService(String name, double pricePerKm, String extras) {
        customServiceService.addCustomService(name, pricePerKm, extras); // Delegate to service
    }

    public CustomService getCustomService(int id) {
        return customServiceService.getCustomService(id);
    }

    public List<CustomService> getAllCustomServices() {
        return customServiceService.getAllCustomServices();
    }

    public void updateCustomService(int id, String name, double pricePerKm, String extras) {
        customServiceService.updateCustomService(id, name, pricePerKm, extras);
    }

    public void deleteCustomService(Integer customServiceId) {
        customServiceService.deleteCustomService(customServiceId);
    }
}
