package org.example.services;

import org.example.models.Client;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * Service class for managing clients in the taxi service application.
 * Provides methods for adding, retrieving, updating, and deleting clients.
 */
public class ClientService {

    private final IRepository<Client> clientRepository;

    /**
     * Constructs a ClientService with the specified client repository.
     *
     * @param clientRepository The repository that handles CRUD operations for Client objects.
     */
    public ClientService(IRepository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Adds a new client with automatic ID generation.
     *
     * @param name    The name of the client.
     * @param email   The email address of the client.
     * @param address The physical address of the client.
     * @param phone   The phone number of the client.
     */
    public void addClient(String name, String email, String address, String phone) {
        // Generate a new ID for the client
        Integer clientId = clientRepository.readAll().size() + 1;

        // Create a new client object
        Client client = new Client(clientId, name, email, address, phone);

        // Save the client in the repository
        clientRepository.create(client);
    }

    /**
     * Retrieves a client by their ID.
     *
     * @param id The ID of the client to retrieve.
     * @return The Client object with the specified ID, or null if not found.
     */
    public Client getClient(int id) {
        return clientRepository.read(id);
    }

    /**
     * Retrieves all clients from the repository.
     *
     * @return A list of all Client objects.
     */
    public List<Client> getAllClients() {
        return clientRepository.readAll();
    }

    /**
     * Updates an existing client with the specified ID.
     * Validates that the client exists before performing the update.
     *
     * @param id      The ID of the client to update.
     * @param name    The updated name of the client.
     * @param email   The updated email address of the client.
     * @param address The updated physical address of the client.
     * @param phone   The updated phone number of the client.
     * @throws IllegalArgumentException If the client with the specified ID does not exist.
     */
    public void updateClient(int id, String name, String email, String address, String phone) {
        Client existingClient = clientRepository.read(id);
        if (existingClient == null) {
            throw new IllegalArgumentException("Client with ID " + id + " does not exist.");
        }

        // Create the updated client object
        Client updatedClient = new Client(id, name, email, address, phone);

        // Update the client in the repository
        clientRepository.update(updatedClient);
    }

    /**
     * Deletes a client by their ID.
     *
     * @param clientId The ID of the client to delete.
     */
    public void deleteClient(Integer clientId) {
        clientRepository.delete(clientId);
    }
}
