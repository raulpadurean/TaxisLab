package org.example.services;

import org.example.models.CustomService;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * Service class for managing CustomService entities.
 * Provides functionality to add, retrieve, update, and delete custom services.
 */
public class CustomServiceService {

    private final IRepository<CustomService> customServiceRepository;

    /**
     * Constructs a CustomServiceService with the specified repository.
     *
     * @param customServiceRepository The repository for managing CustomService objects.
     */
    public CustomServiceService(IRepository<CustomService> customServiceRepository) {
        this.customServiceRepository = customServiceRepository;
    }

    /**
     * Adds a new custom service with the provided details.
     * Automatically generates a unique ID for the service.
     *
     * @param name       The name of the custom service.
     * @param pricePerKm The price per kilometer for the custom service.
     * @param extras     Additional features or details about the custom service.
     */
    public void addCustomService(String name, double pricePerKm, String extras) {
        int id = customServiceRepository.readAll().size() + 1; // Generate a new ID
        CustomService service = new CustomService(id, name, pricePerKm, extras); // Create a new CustomService object
        customServiceRepository.create(service); // Save the service
    }

    /**
     * Retrieves a custom service by its ID.
     *
     * @param id The ID of the custom service to retrieve.
     * @return The CustomService object with the specified ID, or null if not found.
     */
    public CustomService getCustomService(int id) {
        return customServiceRepository.read(id);
    }

    /**
     * Retrieves all custom services in the repository.
     *
     * @return A list of all CustomService objects.
     */
    public List<CustomService> getAllCustomServices() {
        return customServiceRepository.readAll();
    }

    /**
     * Updates an existing custom service with the provided details.
     *
     * @param id         The ID of the custom service to update.
     * @param name       The updated name of the custom service.
     * @param pricePerKm The updated price per kilometer for the custom service.
     * @param extras     The updated additional features or details about the custom service.
     * @throws IllegalArgumentException If the custom service with the specified ID does not exist.
     */
    public void updateCustomService(int id, String name, double pricePerKm, String extras) {
        CustomService existingService = customServiceRepository.read(id);
        if (existingService == null) {
            throw new IllegalArgumentException("Custom Service with ID " + id + " does not exist.");
        }

        // Create an updated CustomService object
        CustomService updatedService = new CustomService(id, name, pricePerKm, extras);
        customServiceRepository.update(updatedService);
    }

    /**
     * Deletes a custom service by its ID.
     *
     * @param customServiceId The ID of the custom service to delete.
     */
    public void deleteCustomService(Integer customServiceId) {
        customServiceRepository.delete(customServiceId);
    }
}
