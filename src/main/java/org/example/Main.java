package org.example;

import org.example.controllers.CompanyController;
import org.example.models.Company;
import org.example.repositories.IRepository;
import org.example.repositories.InMemoryRepository;
import org.example.services.CompanyService;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        IRepository<Company> companyRepo = new InMemoryRepository<>();
        CompanyService companyService = new CompanyService(companyRepo);
        CompanyController companyController = new CompanyController(companyService);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Taxi Service Management");

        while (true) {
            System.out.println("""
                    Options:
                    1. Add Company
                    2. View Companies
                    3. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter company details (name, email, address, phone):");
                    String name = scanner.nextLine();
                    String email = scanner.nextLine();
                    String address = scanner.nextLine();
                    String phone = scanner.nextLine();
                    companyController.addCompany(name, email, address, phone);
                    break;
                case 2:
                    System.out.println("List of Companies:");
                    companyController.getAllCompanies().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
