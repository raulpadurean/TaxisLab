package org.example.controllers;

import org.example.models.CustomService;
import org.example.services.CustomServiceService;

import java.util.List;

/**
 * Controller class for managing CustomService entities.
 * This class provides methods to handle operations such as adding, retrieving,
 * updating, and deleting custom services. It interacts with the
 * {@link CustomServiceService} to perform the required business logic.
 */
public class CustomServiceController {

    private final CustomServiceService customServiceService;

    /**
     * Constructs a new {@code CustomServiceController} with the specified service.
     *
     * @param customServiceService the service instance for handling custom service operations
     */
    public CustomServiceController(CustomServiceService customServiceService) {
        this.customServiceService = customServiceService;
    }

    /**
     * Adds a new custom service with the specified details.
     *
     * @param id         the unique identifier of the custom service
     * @param name       the name of the custom service
     * @param pricePerKm the price per kilometer for the custom service
     * @param extras     additional features or details for the custom service
     */
    public void addCustomService(Integer id, String name, double pricePerKm, String extras) {
        CustomService customService = new CustomService(id, name, pricePerKm, extras);
        customServiceService.addCustomService(customService);
    }

    /**
     * Retrieves a custom service by its ID.
     *
     * @param id the unique identifier of the custom service
     * @return the {@link CustomService} object with the specified ID, or {@code null} if not found
     */
    public CustomService getCustomService(int id) {
        return customServiceService.getCustomService(id);
    }

    /**
     * Retrieves all custom services.
     *
     * @return a list of all {@link CustomService} objects
     */
    public List<CustomService> getAllCustomServices() {
        return customServiceService.getAllCustomServices();
    }

    /**
     * Updates an existing custom service with the specified details.
     *
     * @param id         the unique identifier of the custom service
     * @param name       the updated name of the custom service
     * @param pricePerKm the updated price per kilometer for the custom service
     * @param extras     the updated additional features or details for the custom service
     */
    public void updateCustomService(Integer id, String name, double pricePerKm, String extras) {
        CustomService customService = new CustomService(id, name, pricePerKm, extras);
        customServiceService.updateCustomService(customService);
    }

    /**
     * Deletes a custom service by its ID.
     *
     * @param customServiceId the unique identifier of the custom service to be deleted
     */
    public void deleteCustomService(Integer customServiceId) {
        customServiceService.deleteCustomService(customServiceId);
    }
}
