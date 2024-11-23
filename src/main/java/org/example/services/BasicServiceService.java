package org.example.services;

import org.example.models.BasicService;
import org.example.repositories.IRepository;

import java.util.List;

public class BasicServiceService {
    private final IRepository<BasicService> serviceRepository;

    public BasicServiceService(IRepository<BasicService> serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // Add a basic service with automatic ID generation
    public void addBasicService(String name, double pricePerKm) {
        int id = serviceRepository.readAll().size() + 1; // Generate a new ID
        BasicService service = new BasicService(id, name, pricePerKm); // Create a new BasicService object
        serviceRepository.create(service); // Save the service
    }

    public BasicService getBasicService(int id) {
        return serviceRepository.read(id);
    }

    public List<BasicService> getAllBasicServices() {
        return serviceRepository.readAll();
    }

    public void updateBasicService(int id, String name, double pricePerKm) {
        BasicService existingService = serviceRepository.read(id);
        if (existingService == null) {
            throw new IllegalArgumentException("Basic Service with ID " + id + " does not exist.");
        }

        // Create an updated BasicService object
        BasicService updatedService = new BasicService(id, name, pricePerKm);
        serviceRepository.update(updatedService);
    }

    public void deleteBasicService(Integer basicServiceId) {
        serviceRepository.delete(basicServiceId);
    }
}
