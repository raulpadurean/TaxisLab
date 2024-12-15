package org.example.ui;

import org.example.controllers.CompanyController;
import org.example.models.Company;
import org.example.repositories.IRepository;
import org.example.services.CompanyService;

import java.util.Scanner;

public class CompanyMenu {

    /**
     * Displays the menu for managing companies.
     * Provides options to add, view, update, and delete company records.
     */
    public static void show(IRepository<Company> companyRepo) {
        CompanyService companyService = new CompanyService(companyRepo);
        CompanyController companyController = new CompanyController(companyService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Company
                    2. View Companies
                    3. Update Company
                    4. Delete Company
                    5. Exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter company details:");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Address: ");
                        String address = scanner.nextLine();
                        System.out.print("Phone: ");
                        String phone = scanner.nextLine();

                        companyController.addCompany(name, email, address, phone);
                        System.out.println("Company added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error adding company: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("List of Companies:");
                        companyController.getAllCompanies().forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println("Error retrieving companies: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter the ID of the company to update: ");
                        int companyIdToUpdate = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.println("Enter updated company details:");
                        System.out.print("Name: ");
                        String updatedName = scanner.nextLine();
                        System.out.print("Email: ");
                        String updatedEmail = scanner.nextLine();
                        System.out.print("Address: ");
                        String updatedAddress = scanner.nextLine();
                        System.out.print("Phone: ");
                        String updatedPhone = scanner.nextLine();

                        companyController.updateCompany(companyIdToUpdate, updatedName, updatedEmail, updatedAddress, updatedPhone);
                        System.out.println("Company updated successfully.");
                    } catch (Exception e) {
                        System.out.println("Error updating company: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter the ID of the company to delete: ");
                        int companyIdToDelete = scanner.nextInt();

                        companyController.deleteCompany(companyIdToDelete);
                        System.out.println("Company deleted successfully.");
                    } catch (Exception e) {
                        System.out.println("Error deleting company: " + e.getMessage());
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
