package org.example.controllers;

import org.example.models.Client;
import org.example.services.ClientService;

import java.util.List;

public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void addClient(String name, String email, String address, String phone) {
        clientService.addClient(name, email, address, phone); // Delegate to service
    }

    public Client getClient(int id) {
        return clientService.getClient(id);
    }

    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    public void updateClient(int id, String name, String email, String address, String phone) {
        clientService.updateClient(id, name, email, address, phone);
    }

    public void deleteClient(Integer clientId) {
        clientService.deleteClient(clientId);
    }
}
