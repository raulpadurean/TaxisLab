package org.example.ui;

import org.example.controllers.CustomServiceController;
import org.example.models.CustomService;
import org.example.repositories.IRepository;
import org.example.services.CustomServiceService;

import java.util.Scanner;

public class CustomServiceMenu {

    /**
     * Launches the menu for managing Custom Services.
     * Users can add, view, update, and delete custom services.
     *
     * Functionalities:
     * - Add a custom service with details such as name, price per km, and extras.
     * - View all existing custom services.
     * - Update a custom service by providing an ID and new details.
     * - Delete a custom service by its ID.
     * - Exit the menu.
     */

    public static void show(IRepository<CustomService> customServiceRepo) {
        CustomServiceService customServiceService = new CustomServiceService(customServiceRepo);
        CustomServiceController customServiceController = new CustomServiceController(customServiceService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Custom Service
                    2. View Custom Services
                    3. Update Custom Service
                    4. Delete Custom Service
                    5. Exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter Custom Service details:");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Price Per Km: ");
                        double pricePerKm = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Extras: ");
                        String extras = scanner.nextLine();

                        customServiceController.addCustomService(name, pricePerKm, extras);
                        System.out.println("Custom Service added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error adding Custom Service: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("List of Custom Services:");
                    customServiceController.getAllCustomServices().forEach(System.out::println);
                    break;

                case 3:
                    try {
                        System.out.println("Enter Custom Service ID to update:");
                        int idToUpdate = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.println("Enter updated Custom Service details:");
                        System.out.print("Name: ");
                        String updatedName = scanner.nextLine();

                        System.out.print("Price Per Km: ");
                        double updatedPricePerKm = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Extras: ");
                        String updatedExtras = scanner.nextLine();

                        customServiceController.updateCustomService(idToUpdate, updatedName, updatedPricePerKm, updatedExtras);
                        System.out.println("Custom Service updated successfully.");
                    } catch (Exception e) {
                        System.out.println("Error updating Custom Service: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter the ID of the Custom Service to delete:");
                        int idToDelete = scanner.nextInt();
                        customServiceController.deleteCustomService(idToDelete);
                        System.out.println("Custom Service deleted successfully.");
                    } catch (Exception e) {
                        System.out.println("Error deleting Custom Service: " + e.getMessage());
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
