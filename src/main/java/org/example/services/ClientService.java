package org.example.services;

import org.example.models.Client;

import org.example.repositories.IRepository;

import java.util.List;

public class ClientService {
    private final IRepository<Client> clientRepository;

    public ClientService(IRepository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }


    public void addClient(Client client) {
        clientRepository.create(client);
    }

    public Client getClient(int id) {
        return clientRepository.read(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    public void updateClient(Client client) {
        clientRepository.update(client);
    }

    public void deleteClient(Integer clientId) {

        clientRepository.delete(clientId);
    }

}
