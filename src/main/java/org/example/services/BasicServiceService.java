package org.example.services;

import org.example.models.BasicService;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * Service class for managing basic services in the taxi service application.
 * This class provides methods to add, retrieve, update, and delete basic services.
 */
public class BasicServiceService {

    private final IRepository<BasicService> serviceRepository;

    /**
     * Constructs a BasicServiceService with the specified repository.
     *
     * @param serviceRepository The repository that handles CRUD operations for BasicService objects.
     */
    public BasicServiceService(IRepository<BasicService> serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    /**
     * Adds a new basic service with automatic ID generation.
     * The ID is generated based on the current size of the repository.
     *
     * @param name        The name of the basic service.
     * @param pricePerKm  The price per kilometer for the basic service.
     */
    public void addBasicService(String name, double pricePerKm) {
        int id = serviceRepository.readAll().size() + 1; // Generate a new ID
        BasicService service = new BasicService(id, name, pricePerKm); // Create a new BasicService object
        serviceRepository.create(service); // Save the service to the repository
    }

    /**
     * Retrieves a basic service by its ID.
     *
     * @param id The ID of the basic service to retrieve.
     * @return The BasicService object with the specified ID.
     */
    public BasicService getBasicService(int id) {
        return serviceRepository.read(id);
    }

    /**
     * Retrieves all the basic services from the repository.
     *
     * @return A list of all BasicService objects.
     */
    public List<BasicService> getAllBasicServices() {
        return serviceRepository.readAll();
    }

    /**
     * Updates an existing basic service with the specified ID.
     *
     * @param id          The ID of the basic service to update.
     * @param name        The new name for the basic service.
     * @param pricePerKm  The new price per kilometer for the basic service.
     * @throws IllegalArgumentException If no basic service with the specified ID exists.
     */
    public void updateBasicService(int id, String name, double pricePerKm) {
        BasicService existingService = serviceRepository.read(id);
        if (existingService == null) {
            throw new IllegalArgumentException("Basic Service with ID " + id + " does not exist.");
        }

        // Create an updated BasicService object
        BasicService updatedService = new BasicService(id, name, pricePerKm);
        serviceRepository.update(updatedService);
    }

    /**
     * Deletes a basic service from the repository by its ID.
     *
     * @param basicServiceId The ID of the basic service to delete.
     */
    public void deleteBasicService(Integer basicServiceId) {
        serviceRepository.delete(basicServiceId);
    }
}
