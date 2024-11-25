package org.example.controllers;

import org.example.models.BasicService;
import org.example.services.BasicServiceService;

import java.util.List;

/**
 * Controller class responsible for handling CRUD operations for {@link BasicService} objects.
 * The controller delegates the actual business logic to the {@link BasicServiceService} class.
 * This class provides methods to add, retrieve, update, and delete basic services.
 */
public class BasicServiceController {

    private final BasicServiceService basicServiceService;

    /**
     * Constructs a {@link BasicServiceController} with the specified {@link BasicServiceService}.
     * This service is used to delegate the business logic related to basic services.
     *
     * @param basicServiceService The {@link BasicServiceService} to delegate the business logic.
     */
    public BasicServiceController(BasicServiceService basicServiceService) {
        this.basicServiceService = basicServiceService;
    }

    /**
     * Adds a new {@link BasicService} with the specified name and price per kilometer.
     * Delegates to the {@link BasicServiceService} to perform the actual operation.
     *
     * @param name        The name of the new basic service.
     * @param pricePerKm  The price per kilometer for the new basic service.
     */
    public void addBasicService(String name, double pricePerKm) {
        basicServiceService.addBasicService(name, pricePerKm); // Delegate to service
    }

    /**
     * Retrieves a {@link BasicService} by its unique identifier.
     * Delegates to the {@link BasicServiceService} to perform the actual retrieval.
     *
     * @param id The unique identifier of the basic service.
     * @return The {@link BasicService} object with the specified ID, or {@code null} if not found.
     */
    public BasicService getBasicService(int id) {
        return basicServiceService.getBasicService(id);
    }

    /**
     * Retrieves all {@link BasicService} objects.
     * Delegates to the {@link BasicServiceService} to perform the actual retrieval.
     *
     * @return A list of all {@link BasicService} objects.
     */
    public List<BasicService> getAllBasicServices() {
        return basicServiceService.getAllBasicServices();
    }

    /**
     * Updates the details of an existing {@link BasicService}.
     * Delegates to the {@link BasicServiceService} to perform the actual update.
     *
     * @param id          The unique identifier of the basic service to update.
     * @param name        The new name of the basic service.
     * @param pricePerKm  The new price per kilometer for the basic service.
     */
    public void updateBasicService(int id, String name, double pricePerKm) {
        basicServiceService.updateBasicService(id, name, pricePerKm);
    }

    /**
     * Deletes a {@link BasicService} by its unique identifier.
     * Delegates to the {@link BasicServiceService} to perform the actual deletion.
     *
     * @param basicServiceId The unique identifier of the basic service to delete.
     */
    public void deleteBasicService(Integer basicServiceId) {
        basicServiceService.deleteBasicService(basicServiceId);
    }
}
