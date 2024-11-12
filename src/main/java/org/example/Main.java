package org.example;

import org.example.controllers.BasicServiceController;
import org.example.controllers.CompanyController;
import org.example.controllers.OrderController;
import org.example.models.BasicService;
import org.example.models.Company;
import org.example.models.Order;
import org.example.repositories.IRepository;
import org.example.repositories.InMemoryRepository;
import org.example.services.BasicServiceService;
import org.example.services.CompanyService;
import org.example.services.OrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Taxi Service Management");
        while (true) {
            System.out.println("""
                    Options:
                    0. Exit
                    1. Company
                    2. Order
                    
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    return;
                case 1:
                    companyMenu();
                    break;
                case 2:
                    orderMenu();
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }

    }

    public static void companyMenu() {
        IRepository<Company> companyRepo = new InMemoryRepository<>();
        CompanyService companyService = new CompanyService(companyRepo);
        CompanyController companyController = new CompanyController(companyService);
        Scanner scanner = new Scanner(System.in);
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
    public static void orderMenu() {
        IRepository<Order> orderRepo = new InMemoryRepository<>();
        OrderService orderService = new OrderService(orderRepo);
        OrderController orderController = new OrderController(orderService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Order
                    2. View Order
                    3. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Order details (serviceId, totalKm, clientId, driverId, companyId, datetime):");
                    try {
                        System.out.print("Enter service ID: ");
                        int serviceId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter total Km: ");
                        int totalKm = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter client ID: ");
                        int clientId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter company ID: ");
                        int companyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter date and time (dd-MM-yyyy HH:mm): ");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        Date datetime = dateFormat.parse(scanner.nextLine());

                        orderController.addOrder(serviceId, totalKm, clientId, driverId, companyId, datetime);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer input.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format.");
                    }

                    scanner.close();
                    break;
                case 2:
                    System.out.println("List of Companies:");
                    orderController.getAllCompanies().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
    public static void basicSerivceMenu() {
        IRepository<BasicService> basicServiceRepo = new InMemoryRepository<>();
        BasicServiceService basicServiceService = new BasicServiceService(basicServiceRepo);
        BasicServiceController basicServiceController = new BasicServiceController(basicServiceService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Basic Service
                    2. View Basic Serivce
                    3. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Basic Service details (id, name, pricePerKm):");
                    try {
                        System.out.print("Enter service ID: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter service name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter price per Km: ");
                        int pricePerKm = Integer.parseInt(scanner.nextLine());

                        // Create a BasicService object with the entered details
                        BasicService basicService = new BasicService(id, name, pricePerKm);
                        basicServiceService.addBasicService(basicService);  // Pass the object

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer input.");
                    }
                    break;

                case 2:
                    System.out.println("List of Basic Services:");
                    basicServiceController.getAllBasicServices().forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option");
            }

        }


    }
}
