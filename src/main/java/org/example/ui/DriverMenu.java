package org.example.ui;

import org.example.controllers.DriverController;
import org.example.models.Driver;
import org.example.repositories.IRepository;
import org.example.services.DriverService;

import java.util.Scanner;


public class DriverMenu {

    /**
     * Menu for managing company driver assignments.
     * Provides options to add, view, update, and delete company-driver associations.
     */
    public static void show(IRepository<Driver> driverRepo) {
        DriverService driverService = new DriverService(driverRepo);
        DriverController driverController = new DriverController(driverService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Driver
                    2. View Drivers
                    3. Update Driver
                    4. Delete Driver
                    5. Exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter driver details:");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Address: ");
                        String address = scanner.nextLine();
                        System.out.print("Phone: ");
                        String phone = scanner.nextLine();
                        driverController.addDriver(name, email, address, phone); // ID is auto-generated
                        System.out.println("Driver added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error adding driver: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("List of Drivers:");
                        driverController.getAllDrivers().forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println("Error retrieving drivers: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter the ID of the driver to update: ");
                        int driverIdToUpdate = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.println("Enter updated driver details:");
                        System.out.print("Name: ");
                        String updatedName = scanner.nextLine();
                        System.out.print("Email: ");
                        String updatedEmail = scanner.nextLine();
                        System.out.print("Address: ");
                        String updatedAddress = scanner.nextLine();
                        System.out.print("Phone: ");
                        String updatedPhone = scanner.nextLine();

                        driverController.updateDriver(driverIdToUpdate, updatedName, updatedEmail, updatedAddress, updatedPhone);
                        System.out.println("Driver updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid ID.");
                    } catch (Exception e) {
                        System.out.println("Error updating driver: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter the ID of the driver to delete: ");
                        int driverIdToDelete = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        driverController.deleteDriver(driverIdToDelete);
                        System.out.println("Driver deleted successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid ID.");
                    } catch (Exception e) {
                        System.out.println("Error deleting driver: " + e.getMessage());
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
