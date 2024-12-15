package org.example.ui;

import org.example.controllers.ClientController;
import org.example.models.Client;
import org.example.repositories.IRepository;
import org.example.services.ClientService;

import java.util.Scanner;

public class ClientMenu {

    /**
     * Displays the Client Management menu and processes user input.
     *
     * Options include:
     * - Add a client.
     * - View all clients.
     * - Retrieve a client by ID.
     * - Update a client.
     * - Delete a client.
     * - Sort Client By name
     * - Exit the menu.
     */

    public static void show(IRepository<Client> clientRepo) {
        ClientService clientService = new ClientService(clientRepo);
        ClientController clientController = new ClientController(clientService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Client
                    2. View Clients
                    3. Get Client by ID
                    4. Update Client
                    5. Delete Client
                    6. Sort Client By Name
                    7. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter client details (name, email, address, phone):");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Address: ");
                        String address = scanner.nextLine();

                        System.out.print("Phone: ");
                        String phone = scanner.nextLine();

                        clientController.addClient(name, email, address, phone); // ID is auto-generated
                        System.out.println("Client added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error adding client: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("List of Clients:");
                    clientController.getAllClients().forEach(System.out::println);
                    break;

                case 3:
                    try {
                        System.out.println("Enter the ID of the client to retrieve:");
                        int clientId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Client Details: " + clientController.getClient(clientId));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid numeric ID.");
                    } catch (Exception e) {
                        System.out.println("Error retrieving client: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter client ID to update:");
                        int clientIdToUpdate = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter updated client details (name, email, address, phone):");
                        System.out.print("Name: ");
                        String updatedName = scanner.nextLine();

                        System.out.print("Email: ");
                        String updatedEmail = scanner.nextLine();

                        System.out.print("Address: ");
                        String updatedAddress = scanner.nextLine();

                        System.out.print("Phone: ");
                        String updatedPhone = scanner.nextLine();

                        clientController.updateClient(clientIdToUpdate, updatedName, updatedEmail, updatedAddress, updatedPhone);
                        System.out.println("Client updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numeric values.");
                    } catch (Exception e) {
                        System.out.println("Error updating client: " + e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        System.out.println("Enter the ID of the client to delete:");
                        int clientIdToDelete = Integer.parseInt(scanner.nextLine());
                        clientController.deleteClient(clientIdToDelete);
                        System.out.println("Client deleted successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid numeric ID.");
                    } catch (Exception e) {
                        System.out.println("Error deleting client: " + e.getMessage());
                    }
                    break;

                case 6:


                    System.out.println("Sorted by name ");

                    clientController.sortClientByName().forEach(System.out::println);



                    break;

                case 7:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
