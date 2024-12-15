package org.example.ui;

import org.example.controllers.CompanyDriverController;
import org.example.models.Company;
import org.example.models.CompanyDriver;
import org.example.models.Driver;
import org.example.repositories.IRepository;
import org.example.services.CompanyDriverService;

import java.util.Scanner;

public class CompanyDriverMenu {
    public static void show(IRepository<CompanyDriver> companyDriverRepo, IRepository<Driver> driverRepo, IRepository<Company> companyRepo) {
        CompanyDriverService companyDriverService = new CompanyDriverService(companyDriverRepo, driverRepo, companyRepo);
        CompanyDriverController companyDriverController = new CompanyDriverController(companyDriverService);
        Scanner scanner = new Scanner(System.in);


        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    Options:
                    1. Add Company Driver
                    2. View Company Drivers
                    3. Update Company Driver
                    4. Delete Company Driver
                    5. Exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter Company Driver details:");
                        System.out.print("Driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Company ID: ");
                        int companyId = Integer.parseInt(scanner.nextLine());

                        companyDriverController.addCompanyDriver(driverId, companyId);
                        System.out.println("Company Driver added successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid integers for IDs.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("List of Company Drivers:");
                        companyDriverController.getAllCompanyDrivers().forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println("Error retrieving Company Drivers: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.println("Enter the ID of the Company Driver to update:");
                        int idToUpdate = Integer.parseInt(scanner.nextLine());

                        System.out.print("New Driver ID: ");
                        int newDriverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("New Company ID: ");
                        int newCompanyId = Integer.parseInt(scanner.nextLine());

                        companyDriverController.updateCompanyDriver(idToUpdate, newDriverId, newCompanyId);
                        System.out.println("Company Driver updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid integers for IDs.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter the ID of the Company Driver to delete:");
                        int idToDelete = Integer.parseInt(scanner.nextLine());
                        companyDriverController.deleteCompanyDriver(idToDelete);
                        System.out.println("Company Driver deleted successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer for the ID.");
                    } catch (Exception e) {
                        System.out.println("Error deleting Company Driver: " + e.getMessage());
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
