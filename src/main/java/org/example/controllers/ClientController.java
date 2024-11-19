package org.example.controllers;

import org.example.models.Client;
import org.example.services.ClientService;

import java.util.List;

/**
 * Controller class for managing Client entities.
 * Provides methods to handle operations such as adding, retrieving, updating,
 * and deleting clients. This class interacts with the {@link ClientService} to perform
 * the required business logic.
 */
public class ClientController {

    private final ClientService clientService;

    /**
     * Constructs a new {@code ClientController} with the specified service.
     *
     * @param clientService the service instance for handling client operations
     */
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Adds a new Client.
     *
     * @param id      the unique identifier of the client
     * @param name    the name of the client
     * @param email   the email address of the client
     * @param address the physical address of the client
     * @param phone   the phone number of the client
     */
    public void addClient(Integer id, String name, String email, String address, String phone) {
        Client client = new Client(id, name, email, address, phone);
        clientService.addClient(client);
    }

    /**
     * Retrieves a Client by its ID.
     *
     * @param id the unique identifier of the client
     * @return the {@link Client} object with the specified ID, or {@code null} if not found
     */
    public Client getClient(int id) {
        return clientService.getClient(id);
    }

    /**
     * Retrieves all Clients.
     *
     * @return a list of all {@link Client} objects
     */
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    /**
     * Updates an existing Client using its attributes.
     *
     * @param id      the unique identifier of the client
     * @param name    the name of the client
     * @param email   the email address of the client
     * @param address the physical address of the client
     * @param phone   the phone number of the client
     */
    public void updateClient(Integer id, String name, String email, String address, String phone) {
        Client client = new Client(id, name, email, address, phone);
        clientService.updateClient(client);
    }

    /**
     * Deletes a Client by its ID.
     *
     * @param clientId the unique identifier of the client to be deleted
     */
    public void deleteClient(Integer clientId) {
        clientService.deleteClient(clientId);
    }
}
