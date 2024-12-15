package org.example.ui;

import org.example.controllers.BasicServiceController;
import org.example.models.BasicService;
import org.example.repositories.IRepository;
import org.example.services.BasicServiceService;

import java.util.Scanner;

public class BasicServiceMenu {

    /**
     * Displays the Basic Service Management menu and processes user input.
     *
     * Options include:
     * - Add a basic service.
     * - View all basic services.
     * - Update a basic service.
     * - Delete a basic service.
     * - Exit the menu.
     */

    public static void show(IRepository<BasicService> basicServiceRepo) {
        BasicServiceService basicServiceService = new BasicServiceService(basicServiceRepo);
        BasicServiceController basicServiceController = new BasicServiceController(basicServiceService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Basic Service
                    2. View Basic Services
                    3. Update Basic Service
                    4. Delete Basic Service
                    5. Exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter Basic Service details (name, pricePerKm):");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Price Per Km: ");
                        double pricePerKm = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline

                        basicServiceController.addBasicService(name, pricePerKm); // ID is auto-generated
                        System.out.println("Basic Service added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error adding Basic Service: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("List of Basic Services:");
                    basicServiceController.getAllBasicServices().forEach(System.out::println);
                    break;

                case 3:
                    try {
                        System.out.println("Enter Basic Service ID to update:");
                        int idToUpdate = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.println("Enter updated Basic Service details (name, pricePerKm):");
                        System.out.print("Updated Name: ");
                        String updatedName = scanner.nextLine();

                        System.out.print("Updated Price Per Km: ");
                        double updatedPricePerKm = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline

                        basicServiceController.updateBasicService(idToUpdate, updatedName, updatedPricePerKm);
                        System.out.println("Basic Service updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numeric values.");
                    } catch (Exception e) {
                        System.out.println("Error updating Basic Service: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter the ID of the Basic Service to delete:");
                        int idToDelete = scanner.nextInt();
                        basicServiceController.deleteBasicService(idToDelete);
                        System.out.println("Basic Service deleted successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid ID.");
                    } catch (Exception e) {
                        System.out.println("Error deleting Basic Service: " + e.getMessage());
                    }
                    break;

                case 5:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
