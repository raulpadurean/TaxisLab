package org.example.controllers;

import org.example.models.Client;
import org.example.services.ClientService;

import java.util.List;

/**
 * Controller class responsible for handling CRUD operations for {@link Client} objects.
 * The controller delegates the actual business logic to the {@link ClientService} class.
 * This class provides methods to add, retrieve, update, and delete clients.
 */
public class ClientController {

    private final ClientService clientService;

    /**
     * Constructs a {@link ClientController} with the specified {@link ClientService}.
     * This service is used to delegate the business logic related to clients.
     *
     * @param clientService The {@link ClientService} to delegate the business logic.
     */
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Adds a new {@link Client} with the specified details.
     * Delegates to the {@link ClientService} to perform the actual operation.
     *
     * @param name    The name of the client.
     * @param email   The email of the client.
     * @param address The address of the client.
     * @param phone   The phone number of the client.
     */
    public void addClient(String name, String email, String address, String phone) {
        clientService.addClient(name, email, address, phone); // Delegate to service
    }

    /**
     * Retrieves a {@link Client} by its unique identifier.
     * Delegates to the {@link ClientService} to perform the actual retrieval.
     *
     * @param id The unique identifier of the client.
     * @return The {@link Client} object with the specified ID, or {@code null} if not found.
     */
    public Client getClient(int id) {
        return clientService.getClient(id);
    }

    /**
     * Retrieves all {@link Client} objects.
     * Delegates to the {@link ClientService} to perform the actual retrieval.
     *
     * @return A list of all {@link Client} objects.
     */
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    /**
     * Updates the details of an existing {@link Client}.
     * Delegates to the {@link ClientService} to perform the actual update.
     *
     * @param id      The unique identifier of the client to update.
     * @param name    The new name of the client.
     * @param email   The new email of the client.
     * @param address The new address of the client.
     * @param phone   The new phone number of the client.
     */
    public void updateClient(int id, String name, String email, String address, String phone) {
        clientService.updateClient(id, name, email, address, phone);
    }

    /**
     * Deletes a {@link Client} by its unique identifier.
     * Delegates to the {@link ClientService} to perform the actual deletion.
     *
     * @param clientId The unique identifier of the client to delete.
     */
    public void deleteClient(Integer clientId) {
        clientService.deleteClient(clientId);
    }


    /**
     *  Sorting Clients by their Name
     */

    public List<Client> sortClientByName() {
        return clientService.sortClientByName();
    }

}
