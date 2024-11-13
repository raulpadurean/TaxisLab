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
        Client client = new Client(0, name, email, address, phone);
        clientService.addClient(client);
    }

    public Client getClient(int id){
        return clientService.getClient(id);
    }

    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    public void updateClient(Client client){
        clientService.updateClient(client);
    }

    public  void deleteClient(int id){
        clientService.deleteClient(id);
    }
}
