package org.example.services;

import org.example.models.CustomService;
import org.example.repositories.IRepository;

import java.util.List;

public class CustomServiceService {
    private final IRepository<CustomService> customServiceRepository;

    public CustomServiceService(IRepository<CustomService> customServiceRepository) {
        this.customServiceRepository = customServiceRepository;
    }

    // Add a new custom service with automatic ID generation
    public void addCustomService(String name, double pricePerKm, String extras) {
        int id = customServiceRepository.readAll().size() + 1; // Generate a new ID
        CustomService service = new CustomService(id, name, pricePerKm, extras); // Create a new CustomService object
        customServiceRepository.create(service); // Save the service
    }

    public CustomService getCustomService(int id) {
        return customServiceRepository.read(id);
    }

    public List<CustomService> getAllCustomServices() {
        return customServiceRepository.readAll();
    }

    public void updateCustomService(int id, String name, double pricePerKm, String extras) {
        CustomService existingService = customServiceRepository.read(id);
        if (existingService == null) {
            throw new IllegalArgumentException("Custom Service with ID " + id + " does not exist.");
        }

        // Create an updated CustomService object
        CustomService updatedService = new CustomService(id, name, pricePerKm, extras);
        customServiceRepository.update(updatedService);
    }

    public void deleteCustomService(Integer customServiceId) {
        customServiceRepository.delete(customServiceId);
    }
}
