package org.example.ui;

import org.example.controllers.OrderController;
import org.example.models.*;
import org.example.repositories.IRepository;
import org.example.services.OrderService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class OrderMenu {


    /**
     * Launches the menu for managing Orders.
     * Users can add, view, update, and delete orders.
     *
     * Functionalities:
     * - Add an order with details such as service ID, total km, client ID,
     *   driver ID, company ID, and date/time.
     * - View all existing orders.
     * - Update an order by providing an ID and new details.
     * - Delete an order by its ID.
     * - Exit the menu.
     */

    public static void show(IRepository<Order> orderRepo,
                            IRepository<Service> serviceRepo,
                            IRepository<Client> clientRepo,
                            IRepository<Driver> driverRepo,
                            IRepository<Company> companyRepo) {
        OrderService orderService = new OrderService(orderRepo, serviceRepo, clientRepo, driverRepo, companyRepo);
        OrderController orderController = new OrderController(orderService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Order
                    2. View Orders
                    3. Filter by Service Type (b/c)
                    4. Update Order
                    5. Delete Order
                    6. Exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter Order details:");

                        System.out.print("Service ID: ");
                        int serviceId = scanner.nextInt();

                        System.out.print("Total Km: ");
                        double totalKm = scanner.nextDouble();

                        System.out.print("Client ID: ");
                        int clientId = scanner.nextInt();

                        System.out.print("Driver ID: ");
                        int driverId = scanner.nextInt();

                        System.out.print("Company ID: ");
                        int companyId = scanner.nextInt();

                        System.out.print("Date and Time (dd-MM-yyyy HH:mm): ");
                        scanner.nextLine(); // Consume newline
                        String datetimeInput = scanner.nextLine();

                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime datetime = LocalDateTime.parse(datetimeInput, dateTimeFormatter);

                        orderController.addOrder(totalKm, serviceId, clientId, driverId, companyId, datetime);
                        System.out.println("Order added successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numbers.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use dd-MM-yyyy HH:mm.");
                    } catch (Exception e) {
                        System.out.println("Error adding order: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("List of Orders:");
                    orderController.getAllOrders().forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Choose Basic (b) or Custom (c):");
                    ServiceType serviceType = ServiceType.BASIC;
                    String type = scanner.nextLine();
                    if (type.equals("c")){serviceType=ServiceType.CUSTOM;}
                    orderController.filterOrdersByServiceType(serviceType).forEach(System.out::println);
                    break;

                case 4:
                    try {
                        System.out.print("Enter Order ID to update: ");
                        int orderIdToUpdate = scanner.nextInt();

                        System.out.println("Enter updated Order details:");

                        System.out.print("Service ID: ");
                        int serviceId = scanner.nextInt();

                        System.out.print("Total Km: ");
                        double totalKm = scanner.nextDouble();

                        System.out.print("Client ID: ");
                        int clientId = scanner.nextInt();

                        System.out.print("Driver ID: ");
                        int driverId = scanner.nextInt();

                        System.out.print("Company ID: ");
                        int companyId = scanner.nextInt();

                        System.out.print("Date and Time (dd-MM-yyyy HH:mm): ");
                        scanner.nextLine(); // Consume newline
                        String datetimeInput = scanner.nextLine();

                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime datetime = LocalDateTime.parse(datetimeInput, dateTimeFormatter);

                        orderController.updateOrder(orderIdToUpdate, totalKm, serviceId, clientId, driverId, companyId, datetime);
                        System.out.println("Order updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numbers.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use dd-MM-yyyy HH:mm.");
                    } catch (Exception e) {
                        System.out.println("Error updating order: " + e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        System.out.print("Enter the ID of the Order to delete: ");
                        int orderIdToDelete = scanner.nextInt();
                        orderController.deleteOrder(orderIdToDelete);
                        System.out.println("Order deleted successfully.");
                    } catch (Exception e) {
                        System.out.println("Error deleting order: " + e.getMessage());
                    }
                    break;

                case 6:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
