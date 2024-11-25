package org.example.controllers;

import org.example.models.CustomService;
import org.example.services.CustomServiceService;

import java.util.List;

/**
 * Controller class responsible for handling CRUD operations for {@link CustomService} objects.
 * This controller delegates the actual business logic to the {@link CustomServiceService} class.
 * It provides methods to add, retrieve, update, and delete custom services.
 */
public class CustomServiceController {
    private final CustomServiceService customServiceService;

    /**
     * Constructs a {@link CustomServiceController} with the specified {@link CustomServiceService}.
     * This service is used to delegate the business logic related to custom services.
     *
     * @param customServiceService The {@link CustomServiceService} to delegate the business logic.
     */
    public CustomServiceController(CustomServiceService customServiceService) {
        this.customServiceService = customServiceService;
    }

    /**
     * Adds a new {@link CustomService} with the provided details.
     * Delegates the actual operation to the {@link CustomServiceService}.
     *
     * @param name The name of the custom service.
     * @param pricePerKm The price per kilometer for the service.
     * @param extras Additional details or extras for the custom service.
     */
    public void addCustomService(String name, double pricePerKm, String extras) {
        customServiceService.addCustomService(name, pricePerKm, extras);
    }

    /**
     * Retrieves a {@link CustomService} by its unique identifier.
     * Delegates the actual retrieval to the {@link CustomServiceService}.
     *
     * @param id The unique identifier of the custom service.
     * @return The {@link CustomService} object with the specified ID, or {@code null} if not found.
     */
    public CustomService getCustomService(int id) {
        return customServiceService.getCustomService(id);
    }

    /**
     * Retrieves all {@link CustomService} objects.
     * Delegates the actual retrieval to the {@link CustomServiceService}.
     *
     * @return A list of all {@link CustomService} objects.
     */
    public List<CustomService> getAllCustomServices() {
        return customServiceService.getAllCustomServices();
    }

    /**
     * Updates the details of an existing {@link CustomService} with the provided information.
     * Delegates the actual update to the {@link CustomServiceService}.
     *
     * @param id The unique identifier of the custom service to update.
     * @param name The updated name of the custom service.
     * @param pricePerKm The updated price per kilometer for the service.
     * @param extras The updated extras or additional details for the custom service.
     */
    public void updateCustomService(int id, String name, double pricePerKm, String extras) {
        customServiceService.updateCustomService(id, name, pricePerKm, extras);
    }

    /**
     * Deletes a {@link CustomService} by its unique identifier.
     * Delegates the actual deletion to the {@link CustomServiceService}.
     *
     * @param customServiceId The unique identifier of the custom service to delete.
     */
    public void deleteCustomService(Integer customServiceId) {
        customServiceService.deleteCustomService(customServiceId);
    }
}
