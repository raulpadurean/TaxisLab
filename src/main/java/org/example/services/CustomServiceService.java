package org.example.services;

import org.example.models.CustomService;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * A service class responsible for managing {@link CustomService} entities.
 * Provides business logic to interact with the underlying repository for custom service-related operations.
 *
 * <p>This class handles operations such as adding, retrieving, updating, and deleting custom services.</p>
 */
public class CustomServiceService {
    private final IRepository<CustomService> customServiceRepository;

    /**
     * Constructs a {@link CustomServiceService} with the specified repository.
     *
     * @param serviceRepository The repository to be used for storing and retrieving custom service entities.
     */
    public CustomServiceService(IRepository<CustomService> serviceRepository) {
        this.customServiceRepository = serviceRepository;
    }

    /**
     * Adds a new custom service to the repository.
     *
     * <p>This method persists the provided {@link CustomService} entity to the repository.</p>
     *
     * @param service The custom service to be added. Must not be {@code null}.
     */
    public void addCustomService(CustomService service) {
        customServiceRepository.create(service);
    }

    /**
     * Retrieves a custom service by its unique ID.
     *
     * @param id The unique identifier of the custom service to retrieve.
     * @return The custom service with the specified ID, or {@code null} if no such service exists.
     */
    public CustomService getCustomService(int id) {
        return customServiceRepository.read(id);
    }

    /**
     * Retrieves all custom services from the repository.
     *
     * @return A list of all custom services in the repository.
     */
    public List<CustomService> getAllCustomServices() {
        return customServiceRepository.getAll();
    }

    /**
     * Updates an existing custom service in the repository.
     *
     * <p>If the custom service already exists in the repository, it will be updated with the new values.</p>
     *
     * @param service The custom service to update. The service must not be {@code null}.
     */
    public void updateCustomService(CustomService service) {
        customServiceRepository.update(service);
    }

    /**
     * Deletes a custom service from the repository.
     *
     * @param customServiceId The unique ID of the custom service to be deleted.
     */
    public void deleteCustomService(Integer customServiceId) {
        customServiceRepository.delete(customServiceId);
    }
}
