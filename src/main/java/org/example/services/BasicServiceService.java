package org.example.services;

import org.example.models.BasicService;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * Service class for managing Basic Services.
 * Provides the business logic for adding, retrieving, updating, and deleting
 * {@link BasicService} objects. Interacts with a repository to handle data persistence.
 */
public class BasicServiceService {

    private final IRepository<BasicService> serviceRepository;

    /**
     * Constructs a new {@code BasicServiceService} with the specified repository.
     *
     * @param serviceRepository the repository used for data persistence of basic services
     */
    public BasicServiceService(IRepository<BasicService> serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    /**
     * Adds a new Basic Service to the repository.
     *
     * @param service the {@code BasicService} to be added
     */
    public void addBasicService(BasicService service) {
        serviceRepository.create(service);
    }

    /**
     * Retrieves a Basic Service by its ID.
     *
     * @param id the unique identifier of the basic service
     * @return the {@code BasicService} with the specified ID, or {@code null} if not found
     */
    public BasicService getBasicService(int id) {
        return serviceRepository.read(id);
    }

    /**
     * Retrieves all Basic Services from the repository.
     *
     * @return a list of all {@code BasicService} objects
     */
    public List<BasicService> getAllBasicServices() {
        return serviceRepository.getAll();
    }

    /**
     * Updates an existing Basic Service in the repository.
     *
     * @param service the {@code BasicService} object with updated details
     */
    public void updateBasicService(BasicService service) {
        serviceRepository.update(service);
    }

    /**
     * Deletes a Basic Service from the repository by its ID.
     *
     * @param basicServiceId the unique identifier of the basic service to be deleted
     */
    public void deleteBasicService(Integer basicServiceId) {
        serviceRepository.delete(basicServiceId);
    }
}
