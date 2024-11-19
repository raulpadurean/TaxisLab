package org.example.services;

import org.example.models.Client;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * A service class responsible for managing {@link Client} entities.
 * Provides business logic to interact with the underlying repository for client operations.
 *
 * <p>This class handles operations such as adding, retrieving, updating, and deleting clients.</p>
 */
public class ClientService {

    private final IRepository<Client> clientRepository;

    /**
     * Constructs a {@link ClientService} with the specified repository.
     *
     * @param clientRepository The repository to be used for storing and retrieving clients.
     */
    public ClientService(IRepository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Adds a new client to the repository.
     *
     * <p>This method persists the provided client entity to the repository.</p>
     *
     * @param client The client to be added. Must not be {@code null}.
     */
    public void addClient(Client client) {
        clientRepository.create(client);
    }

    /**
     * Retrieves a client by its unique ID.
     *
     * @param id The unique identifier of the client to retrieve.
     * @return The client with the specified ID, or {@code null} if no such client exists.
     */
    public Client getClient(int id) {
        return clientRepository.read(id);
    }

    /**
     * Retrieves all clients from the repository.
     *
     * @return A list of all clients in the repository.
     */
    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    /**
     * Updates an existing client in the repository.
     *
     * <p>If the client already exists in the repository, it will be updated with the new values.</p>
     *
     * @param client The client to update. The client must not be {@code null}.
     */
    public void updateClient(Client client) {
        clientRepository.update(client);
    }

    /**
     * Deletes a client from the repository.
     *
     * @param clientId The unique ID of the client to be deleted.
     */
    public void deleteClient(Integer clientId) {
        clientRepository.delete(clientId);
    }
}
