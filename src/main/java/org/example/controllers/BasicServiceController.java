package org.example.controllers;

import org.example.models.BasicService;
import org.example.services.BasicServiceService;

import java.util.List;

/**
 * Controller class for managing Basic Services.
 * Provides methods to handle operations such as adding, retrieving, updating,
 * and deleting basic services. This class interacts with the {@code BasicServiceService}
 * to perform the required business logic.
 */
public class BasicServiceController {

    private final BasicServiceService basicServiceService;

    /**
     * Constructor to initialize the controller with the given service.
     *
     * @param basicServiceService the service instance for handling basic service operations
     */
    public BasicServiceController(BasicServiceService basicServiceService) {
        this.basicServiceService = basicServiceService;
    }

    /**
     * Adds a new Basic Service.
     *
     * @param id          the ID of the basic service
     * @param name        the name of the basic service
     * @param pricePerKm  the price per kilometer for the basic service
     */
    public void addBasicService(int id, String name, double pricePerKm) {
        BasicService basicService = new BasicService(id, name, pricePerKm);
        basicServiceService.addBasicService(basicService);
    }

    /**
     * Retrieves a Basic Service by its ID.
     *
     * @param id the ID of the basic service
     * @return the {@code BasicService} object with the specified ID
     */
    public BasicService getBasicService(int id) {
        return basicServiceService.getBasicService(id);
    }

    /**
     * Retrieves all Basic Services.
     *
     * @return a list of all {@code BasicService} objects
     */
    public List<BasicService> getAllBasicServices() {
        return basicServiceService.getAllBasicServices();
    }

    /**
     * Updates an existing Basic Service.
     *
     * @param id          the ID of the basic service to update
     * @param name        the new name of the basic service
     * @param pricePerKm  the new price per kilometer for the basic service
     */
    public void updateBasicService(int id, String name, double pricePerKm) {
        BasicService basicService = new BasicService(id, name, pricePerKm);
        basicServiceService.updateBasicService(basicService);
    }

    /**
     * Deletes a Basic Service by its ID.
     *
     * @param basicServiceId the ID of the basic service to delete
     */
    public void deleteBasicService(Integer basicServiceId) {
        basicServiceService.deleteBasicService(basicServiceId);
    }
}
