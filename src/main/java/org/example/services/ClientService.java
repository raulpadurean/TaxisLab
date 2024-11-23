package org.example.services;

import org.example.models.Client;
import org.example.repositories.IRepository;

import java.util.List;

public class ClientService {
    private final IRepository<Client> clientRepository;

    public ClientService(IRepository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Add a new client with automatic ID generation
    public void addClient(String name, String email, String address, String phone) {
        // Generate a new ID for the client
        Integer clientId = clientRepository.readAll().size() + 1;

        // Create a new client object
        Client client = new Client(clientId, name, email, address, phone);

        // Save the client in the repository
        clientRepository.create(client);
    }

    public Client getClient(int id) {
        return clientRepository.read(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.readAll();
    }

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

    public void deleteClient(Integer clientId) {
        clientRepository.delete(clientId);
    }
}
