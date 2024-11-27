package org.example;

import org.example.controllers.*;
import org.example.models.*;
import org.example.repositories.IRepository;
import org.example.repositories.FileRepository;
import org.example.services.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

/**
 * Main class for the Taxi Service Management application.
 * This class initializes repositories and provides menus for managing various entities.
 */
public class Main {

    // Repositories for various entities in the application
    private static final IRepository<Car> carRepo = new FileRepository<>("data/cars.csv", Car.class);
    private static final IRepository<BasicService> basicServiceRepo = new FileRepository<>("data/basic_services.csv", BasicService.class);
    private static final IRepository<Client> clientRepo = new FileRepository<>("data/clients.csv", Client.class);
    private static final IRepository<Company> companyRepo = new FileRepository<>("data/companies.csv", Company.class);
    private static final IRepository<CompanyDriver> companyDriverRepo = new FileRepository<>("data/company_drivers.csv", CompanyDriver.class);
    private static final IRepository<CustomService> customServiceRepo = new FileRepository<>("data/custom_services.csv", CustomService.class);
    private static final IRepository<Driver> driverRepo = new FileRepository<>("data/drivers.csv", Driver.class);
    private static final IRepository<DriverSchedule> driverScheduleRepo = new FileRepository<>("data/driver_schedules.csv", DriverSchedule.class);
    private static final IRepository<Order> orderRepo = new FileRepository<>("data/orders.csv", Order.class);
    private static final IRepository<Review> reviewRepo = new FileRepository<>("data/reviews.csv", Review.class);
    private static final IRepository<Service> serviceRepo = new FileRepository<>("data/services.csv", Service.class);

    /**
     * The main entry point for the application.
     * Displays the main menu and provides access to various entity management menus.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Welcome to Taxi Service Management");
            System.out.println("""
                
                Options:
                
                0. Exit
                1. Company
                2. Driver
                3. Company Drivers
                4. Driver Schedule
                5. Car
                6. Client
                7. Basic Service
                8. Custom Service
                9. Order
                10. Rating
                
                Enter option: >>>
                """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 0:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;
                case 1:
                    companyMenu();
                    break;
                case 2:
                    driverMenu();
                    break;
                case 3:
                    companyDriversMenu();
                    break;
                case 4:
                    driverScheduleMenu();
                    break;
                case 5:
                    carMenu();
                    break;
                case 6:
                    clientMenu();
                    break;
                case 7:
                    basicServiceMenu();
                    break;
                case 8:
                    customServiceMenu();
                    break;
                case 9:
                    orderMenu();
                    break;
                case 10:
                    reviewMenu();
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }

    }

    /**
     * Displays the menu for managing companies.
     * Provides options to add, view, update, and delete company records.
     */

    public static void companyMenu() {
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
    /**
     * Menu for managing company driver assignments.
     * Provides options to add, view, update, and delete company-driver associations.
     */

    public static void driverMenu() {
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

    /**
     * Menu for managing company driver assignments.
     * Provides options to add, view, update, and delete company-driver associations.
     */

    public static void companyDriversMenu() {
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

    /**
     * Menu for managing driver schedules.
     * Provides options to add, view, update, and delete driver schedules.
     */

    public static void driverScheduleMenu() {
        DriverScheduleService driverScheduleService = new DriverScheduleService(driverScheduleRepo, driverRepo, companyRepo);
        DriverScheduleController driverScheduleController = new DriverScheduleController(driverScheduleService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Driver Schedule
                    2. View Driver Schedules
                    3. Update Driver Schedule
                    4. Delete Driver Schedule
                    5. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter Driver Schedule details (driverId, companyId, checkIn [yyyy-MM-dd], checkout [yyyy-MM-dd]):");

                        System.out.print("Driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Company ID: ");
                        int companyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Check-In Date (yyyy-MM-dd): ");
                        String checkInDateStr = scanner.nextLine();
                        Date checkIn = new SimpleDateFormat("yyyy-MM-dd").parse(checkInDateStr);

                        System.out.print("Check-Out Date (yyyy-MM-dd): ");
                        String checkOutDateStr = scanner.nextLine();
                        Date checkOut = new SimpleDateFormat("yyyy-MM-dd").parse(checkOutDateStr);

                        driverScheduleController.addDriverSchedule(driverId, companyId, checkIn, checkOut);
                        System.out.println("Driver Schedule added successfully.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    } catch (Exception e) {
                        System.out.println("Error adding Driver Schedule: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("List of Driver Schedules:");
                    driverScheduleController.getAllDriverSchedules().forEach(System.out::println);
                    break;

                case 3:
                    try {
                        System.out.println("Enter the ID of the Driver Schedule to update:");
                        int scheduleId = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter updated Driver Schedule details (driverId, companyId, checkIn [yyyy-MM-dd], checkout [yyyy-MM-dd]):");

                        System.out.print("Driver ID: ");
                        int updatedDriverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Company ID: ");
                        int updatedCompanyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Check-In Date (yyyy-MM-dd): ");
                        String updatedCheckInDateStr = scanner.nextLine();
                        Date updatedCheckIn = new SimpleDateFormat("yyyy-MM-dd").parse(updatedCheckInDateStr);

                        System.out.print("Check-Out Date (yyyy-MM-dd): ");
                        String updatedCheckOutDateStr = scanner.nextLine();
                        Date updatedCheckOut = new SimpleDateFormat("yyyy-MM-dd").parse(updatedCheckOutDateStr);

                        driverScheduleController.updateDriverSchedule(scheduleId, updatedDriverId, updatedCompanyId, updatedCheckIn, updatedCheckOut);
                        System.out.println("Driver Schedule updated successfully.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    } catch (Exception e) {
                        System.out.println("Error updating Driver Schedule: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter the ID of the Driver Schedule to delete:");
                        int scheduleIdToDelete = Integer.parseInt(scanner.nextLine());
                        driverScheduleController.deleteDriverSchedule(scheduleIdToDelete);
                        System.out.println("Driver Schedule deleted successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid ID.");
                    } catch (Exception e) {
                        System.out.println("Error deleting Driver Schedule: " + e.getMessage());
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

    /**
     * Displays the Car Management menu and processes user input.
     *
     * Options include:
     * - Add a car.
     * - View all cars.
     * - Retrieve a car by ID.
     * - Update a car.
     * - Delete a car.
     * - Exit the menu.
     */

    public static void carMenu() {
        CarService carService = new CarService(carRepo, driverRepo);
        CarController carController = new CarController(carService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Car
                    2. View Cars
                    3. Get Car by ID
                    4. Update Car
                    5. Delete Car
                    6. Filter Car By Brand
                    7. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter car details (brand, model, plate number, driver ID):");
                        System.out.print("Brand: ");
                        String brand = scanner.nextLine();

                        System.out.print("Model: ");
                        String model = scanner.nextLine();

                        System.out.print("Plate Number: ");
                        String plateNr = scanner.nextLine();

                        System.out.print("Driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine());

                        carController.addCar(brand, model, plateNr, driverId); // No ID needed
                        System.out.println("Car added successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numeric values for driver ID.");
                    } catch (Exception e) {
                        System.out.println("Error adding car: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("List of Cars:");
                    carController.getAllCars().forEach(System.out::println);
                    break;

                case 3:
                    try {
                        System.out.println("Enter the ID of the car to retrieve:");
                        int carId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Car Details: " + carController.getCar(carId));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid numeric ID.");
                    } catch (Exception e) {
                        System.out.println("Error retrieving car: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter updated car details (ID, brand, model, plate number, driver ID):");

                        System.out.print("ID: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Brand: ");
                        String brand = scanner.nextLine();

                        System.out.print("Model: ");
                        String model = scanner.nextLine();

                        System.out.print("Plate Number: ");
                        String plateNr = scanner.nextLine();

                        System.out.print("Driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine());

                        carController.updateCar(id, brand, model, plateNr, driverId);
                        System.out.println("Car updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numeric values.");
                    } catch (Exception e) {
                        System.out.println("Error updating car: " + e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        System.out.println("Enter the ID of the car to delete:");
                        int carId = Integer.parseInt(scanner.nextLine());
                        carController.deleteCar(carId);
                        System.out.println("Car deleted successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid numeric ID.");
                    } catch (Exception e) {
                        System.out.println("Error deleting car: " + e.getMessage());
                    }
                    break;

                case 6:

                    String filePath = "C:\\Users\\pc\\IdeaProjects\\TaxisLab\\data\\cars.csv"; // Path to the CSV file

                    System.out.println("Give the name of the brand: ... ");

                    String filterBrand = scanner.nextLine(); // Brand to filter by



                    try {
                        // Load cars from the file
                        carController.loadCarsFromFile(filePath);

                        // Filter cars by brand
                        List<Car> filteredCars = carController.filterCarsByBrand(filterBrand);

                        // Display filtered cars
                        System.out.println("Filtered cars:");
                        filteredCars.forEach(System.out::println);


                    } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                    }


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

    /**
     * Displays the Client Management menu and processes user input.
     *
     * Options include:
     * - Add a client.
     * - View all clients.
     * - Retrieve a client by ID.
     * - Update a client.
     * - Delete a client.
     * - Exit the menu.
     */

    public static void clientMenu() {
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
                    6. Exit
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
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


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

    public static void basicServiceMenu() {
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

    public static void customServiceMenu() {
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

    public static void orderMenu() {
        OrderService orderService = new OrderService(orderRepo, serviceRepo, clientRepo, driverRepo, companyRepo);
        OrderController orderController = new OrderController(orderService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Order
                    2. View Orders
                    3. Update Order
                    4. Delete Order
                    5. Exit
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
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        Date datetime = dateFormat.parse(datetimeInput);

                        orderController.addOrder(totalKm, serviceId, clientId, driverId, companyId, datetime);
                        System.out.println("Order added successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numbers.");
                    } catch (ParseException e) {
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
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        Date datetime = dateFormat.parse(datetimeInput);

                        orderController.updateOrder(orderIdToUpdate, totalKm, serviceId, clientId, driverId, companyId, datetime);
                        System.out.println("Order updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numbers.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use dd-MM-yyyy HH:mm.");
                    } catch (Exception e) {
                        System.out.println("Error updating order: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter the ID of the Order to delete: ");
                        int orderIdToDelete = scanner.nextInt();
                        orderController.deleteOrder(orderIdToDelete);
                        System.out.println("Order deleted successfully.");
                    } catch (Exception e) {
                        System.out.println("Error deleting order: " + e.getMessage());
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

    /**
     * Launches the menu for managing Reviews.
     * Users can add, view, update, and delete reviews, and find the best-rated driver.
     *
     * Functionalities:
     * - Add a review with details such as review ID, client ID, driver ID,
     *   company ID, rating, and description.
     * - View all existing reviews.
     * - Update a review by providing an ID and new details.
     * - Delete a review by its ID.
     * - Find the best-rated driver for a specific company by analyzing reviews.
     * - Exit the menu.
     */

    public static void reviewMenu() {
        ReviewService reviewService = new ReviewService(reviewRepo, driverRepo, companyRepo, clientRepo);
        ReviewController reviewController = new ReviewController(reviewService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Review
                    2. View Reviews
                    3. Update Review
                    4. Delete Review
                    5. Find Best Rated Driver
                    6. Favourite Driver By Client
                    7. Exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter Review details (reviewId, clientId, driverId, companyId, rating, description):");

                        System.out.print("Enter review ID: ");
                        int reviewId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter client ID: ");
                        int clientId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter company ID: ");
                        int companyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter rating: ");
                        int rating = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter description: ");
                        String description = scanner.nextLine();

                        reviewController.addReview(reviewId, companyId, driverId, clientId, rating, description);
                        System.out.println("Review added successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid IDs and rating.");
                    }
                    break;

                case 2:
                    System.out.println("List of Reviews:");
                    reviewController.getAllReviews().forEach(System.out::println);
                    break;

                case 3:
                    try {
                        System.out.println("Enter Review ID to update:");
                        int idToUpdate = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter updated client ID: ");
                        int updatedClientId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter updated driver ID: ");
                        int updatedDriverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter updated company ID: ");
                        int updatedCompanyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter updated rating: ");
                        int updatedRating = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter updated description: ");
                        String updatedDescription = scanner.nextLine();

                        reviewController.updateReview(idToUpdate, updatedCompanyId, updatedDriverId, updatedClientId, updatedRating, updatedDescription);
                        System.out.println("Review updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid IDs and rating.");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter the ID of the Review to delete: ");
                        int idToDelete = Integer.parseInt(scanner.nextLine());
                        reviewController.deleteReview(idToDelete);
                        System.out.println("Review deleted successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid review ID.");
                    }
                    break;

                case 5:
                    try {
                        List<Driver> drivers = new ArrayList<>();
                        List<Review> reviews = new ArrayList<>();

                        // read CSV file and populate drivers and reviews
                        File file = new File("C:\\Users\\pc\\IdeaProjects\\TaxisLab\\data\\reviews.csv");
                        if (!file.exists()) {
                            System.out.println("File not found: " + file.getAbsolutePath());
                            return; // Exit if the file does not exist
                        }

                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String line;
                            br.readLine();
                            System.out.println("Started reading file...");

                            // Read and print each line to verify that the file is being read
                            int counter = 0;
                            while ((line = br.readLine()) != null) {
                                counter++;
                                System.out.println("Processing line " + counter + ": " + line);  // Debugging

                                String[] parts = line.split(";");
                                System.out.println("Parts: " + Arrays.toString(parts));  // Debugging

                                // Check if the line has 18 fields
                                if (parts.length != 18) {
                                    System.out.println("Skipping line due to incorrect number of fields: " + line);
                                    continue;
                                }

                                try {
                                    Integer reviewId = Integer.parseInt(parts[0]);
                                    int rating = Integer.parseInt(parts[1]);
                                    String description = parts[2];

                                    int companyId = Integer.parseInt(parts[3]);
                                    String companyName = parts[4];
                                    String companyEmail = parts[5];
                                    String companyAddress = parts[6];
                                    String companyPhone = parts[7];

                                    int driverId = Integer.parseInt(parts[8]);
                                    String driverName = parts[9];
                                    String driverEmail = parts[10];
                                    String driverAddress = parts[11];
                                    String driverPhone = parts[12];

                                    int clientId = Integer.parseInt(parts[13]);
                                    String clientName = parts[14];
                                    String clientEmail = parts[15];
                                    String clientAddress = parts[16];
                                    String clientPhone = parts[17];

                                    // Create Company and Client objects
                                    Company company = new Company(companyId, companyName, companyEmail, companyAddress, companyPhone);
                                    Client client = new Client(clientId, clientName, clientEmail, clientAddress, clientPhone);

                                    // Check if the company, driver, and client objects are created correctly
                                    System.out.println("Created Company: " + company);
                                    System.out.println("Created Client: " + client);

                                    // Add the driver if not already in the list
                                    Driver driver = new Driver(driverId, driverName, driverPhone, driverEmail, driverAddress);
                                    if (drivers.stream().noneMatch(d -> d.getId() == driverId)) {
                                        drivers.add(driver);
                                        System.out.println("Added Driver: " + driver);
                                    }

                                    // Create and add the review to the list
                                    Review review = new Review(reviewId, rating, description, company, driver, client);
                                    reviews.add(review);
                                    System.out.println("Added Review: " + review);

                                } catch (NumberFormatException e) {
                                    System.out.println("Error parsing line (skipping): " + line + " | Error: " + e.getMessage());
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Error reading the file: " + e.getMessage());
                        }

                        // print the number of drivers and reviews read
                        System.out.println("Number of drivers loaded: " + drivers.size());
                        System.out.println("Number of reviews loaded: " + reviews.size());

                        // find the best driver for company 2 (for example)
                        int companyIdToSearch = 2; // Example company ID to search
                        Map.Entry<Driver, Double> bestDriverEntry = ReviewController.findBestRatedDriverInCompany(companyIdToSearch, drivers, reviews);

                        // result
                        if (bestDriverEntry != null) {
                            System.out.println("Best Driver: " + bestDriverEntry.getKey());
                            System.out.println("Average Rating: " + bestDriverEntry.getValue());
                        } else {
                            System.out.println("No drivers found for the specified company.");
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        List<Driver> drivers = new ArrayList<>();
                        List<Review> reviews = new ArrayList<>();

                        // read CSV file and populate drivers and reviews
                        File file = new File("C:\\Users\\pc\\IdeaProjects\\TaxisLab\\data\\reviews.csv");
                        if (!file.exists()) {
                            System.out.println("File not found: " + file.getAbsolutePath());
                            return; // Exit if the file does not exist
                        }

                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String line;
                            br.readLine();
                            System.out.println("Started reading file...");

                            // Read and print each line to verify that the file is being read
                            int counter = 0;
                            while ((line = br.readLine()) != null) {
                                counter++;
                                System.out.println("Processing line " + counter + ": " + line);  // Debugging

                                String[] parts = line.split(";");
                                System.out.println("Parts: " + Arrays.toString(parts));  // Debugging

                                // Check if the line has 18 fields
                                if (parts.length != 18) {
                                    System.out.println("Skipping line due to incorrect number of fields: " + line);
                                    continue;
                                }

                                try {
                                    Integer reviewId = Integer.parseInt(parts[0]);
                                    int rating = Integer.parseInt(parts[1]);
                                    String description = parts[2];

                                    int companyId = Integer.parseInt(parts[3]);
                                    String companyName = parts[4];
                                    String companyEmail = parts[5];
                                    String companyAddress = parts[6];
                                    String companyPhone = parts[7];

                                    int driverId = Integer.parseInt(parts[8]);
                                    String driverName = parts[9];
                                    String driverEmail = parts[10];
                                    String driverAddress = parts[11];
                                    String driverPhone = parts[12];

                                    int clientId = Integer.parseInt(parts[13]);
                                    String clientName = parts[14];
                                    String clientEmail = parts[15];
                                    String clientAddress = parts[16];
                                    String clientPhone = parts[17];

                                    // Create Company and Client objects
                                    Company company = new Company(companyId, companyName, companyEmail, companyAddress, companyPhone);
                                    Client client = new Client(clientId, clientName, clientEmail, clientAddress, clientPhone);

                                    // Check if the company, driver, and client objects are created correctly
                                    System.out.println("Created Company: " + company);
                                    System.out.println("Created Client: " + client);

                                    // Add the driver if not already in the list
                                    Driver driver = new Driver(driverId, driverName, driverPhone, driverEmail, driverAddress);
                                    if (drivers.stream().noneMatch(d -> d.getId() == driverId)) {
                                        drivers.add(driver);
                                        System.out.println("Added Driver: " + driver);
                                    }

                                    // Create and add the review to the list
                                    Review review = new Review(reviewId, rating, description, company, driver, client);
                                    reviews.add(review);
                                    System.out.println("Added Review: " + review);

                                } catch (NumberFormatException e) {
                                    System.out.println("Error parsing line (skipping): " + line + " | Error: " + e.getMessage());
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Error reading the file: " + e.getMessage());
                        }

                        // print the number of drivers and reviews read
                        System.out.println("Number of drivers loaded: " + drivers.size());
                        System.out.println("Number of reviews loaded: " + reviews.size());
                        System.out.println();

                        // find the best driver for company 2 (for example)
                        //int clientId = 1;// Example client ID to search
                        System.out.println("Please provide the clients id...:");
                        int clientId = Integer.parseInt(scanner.nextLine());
                        Map.Entry<Driver, Double> bestDriverEntry = ReviewController.findFavouriteDriverByClient(clientId, drivers, reviews);

                        // result
                        if (bestDriverEntry != null) {
                            System.out.println("Favourite Driver: " + bestDriverEntry.getKey());
                            System.out.println("Average Rating: " + bestDriverEntry.getValue());
                        } else {
                            System.out.println("No drivers found for the specified client.");
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
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
