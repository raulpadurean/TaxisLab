package org.example;

import org.example.controllers.*;
import org.example.models.*;
import org.example.repositories.IRepository;
import org.example.repositories.InMemoryRepository;
import org.example.services.*;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Taxi Service Management");
        while (true) {
            System.out.println("""
                    Options:
                    0. Exit
                    1. Company
                    2. Order
                    3. Basic Service
                    4. Custom Service
                    5. Rating
                    6. Car
                    7. Client
                    8. Driver
                    9. Driver Schedule
                    10. Company Drivers
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
                case 3:
                    basicSerivceMenu();
                    break;
                case 4:
                    customSerivceMenu();
                    break;
                case 5:
                    reviewMenu();
                    break;
                case 6:
                    carMenu();
                    break;
                case 7:
                    clientMenu();
                    break;
                case 8:
                    driverMenu();
                    break;
                case 9:
                    driverScheduleMenu();
                    break;
                case 10:
                    //CompanyDriversMenu();
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }

    }

    public static void carMenu() {
        IRepository<Car> carRepo = new InMemoryRepository<>();
        CarService carService = new CarService(carRepo);
        CarController carController = new CarController(carService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Car
                    2. View Car
                    3. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Car details (brand, model, number plate, driver id):");
                    String brand = scanner.nextLine();
                    String model = scanner.nextLine();
                    String plateNr = scanner.nextLine();

                    int driverId = Integer.parseInt(scanner.nextLine());
                    carController.addCar(brand, model , plateNr, driverId);
                    break;
                case 2:
                    System.out.println("List of Cars:");
                    carController.getAllCars().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void clientMenu() {
        IRepository<Client> clientRepo = new InMemoryRepository<>();
        ClientService clientService = new ClientService(clientRepo);
        ClientController clientController = new ClientController(clientService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Client
                    2. View Client
                    3. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter client details (name, email, address, phone):");
                    String name = scanner.nextLine();
                    String email = scanner.nextLine();
                    String address = scanner.nextLine();
                    String phone = scanner.nextLine();
                    clientController.addClient(name, email, address, phone);
                    break;
                case 2:
                    System.out.println("List of Clients:");
                    clientController.getAllClients().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void driverMenu() {
        IRepository<Driver> driverRepo = new InMemoryRepository<>();
        DriverService driverService = new DriverService(driverRepo);
        DriverController driverController = new DriverController(driverService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Driver
                    2. View Driver
                    3. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter driver details (name, email, address, phone):");
                    String name = scanner.nextLine();
                    String email = scanner.nextLine();
                    String address = scanner.nextLine();
                    String phone = scanner.nextLine();
                    driverController.addDriver(name, email, address, phone);
                    break;
                case 2:
                    System.out.println("List of Drivers:");
                    driverController.getAllDrivers().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void driverScheduleMenu() throws ParseException {
        IRepository<DriverSchedule> driverScheduleRepo = new InMemoryRepository<>();
        DriverScheduleService driverScheduleService = new DriverScheduleService(driverScheduleRepo);
        DriverScheduleController driverScheduleController = new DriverScheduleController(driverScheduleService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add DriverSchedule
                    2. View DriverSchedule
                    3. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter driver schedule (driverId, companyId, checkIn, checkout):");
                    int driverId = Integer.parseInt(scanner.nextLine());
                    int companyId = Integer.parseInt(scanner.nextLine());

                    System.out.println("yyyy-MM-dd");
                    String iDate = scanner.next();
                    DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                    Date checkIn = formatter1.parse(iDate);

                    System.out.println("yyyy-MM-dd");
                    String oDate = scanner.next();
                    DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date checkOut = formatter2.parse(oDate);

                    driverScheduleController.addDriverSchedule(driverId, companyId, checkIn, checkOut);

                    break;


                    case 2:
                    System.out.println("List of DriverSchedules:");
                    driverScheduleController.getAllDriverSchedules().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
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
                    orderController.getAllOrders().forEach(System.out::println);
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
    public static void customSerivceMenu() {
        IRepository<CustomService> customServiceRepo = new InMemoryRepository<>();
        CustomServiceService customServiceService = new CustomServiceService(customServiceRepo);
        CustomServiceController customServiceController = new CustomServiceController(customServiceService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Custom Service
                    2. View Custom Serivce
                    3. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Basic Service details (id, name, pricePerKm, extras):");
                    try {
                        System.out.print("Enter service ID: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter service name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter price per Km: ");
                        int pricePerKm = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter service extras: ");
                        String extras = scanner.nextLine();

                        // Create a BasicService object with the entered details
                        CustomService customService = new CustomService(id, name, pricePerKm, extras);
                        customServiceService.addCustomService(customService);  // Pass the object

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer input.");
                    }
                    break;

                case 2:
                    System.out.println("List of Basic Services:");
                    customServiceController.getAllCustomServices().forEach(System.out::println);
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

    public static void reviewMenu() {
        IRepository<Review> reviewRepo = new InMemoryRepository<>();
        ReviewService reviewService = new ReviewService(reviewRepo);
        ReviewController reviewController = new ReviewController(reviewService);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                Options:
                1. Add Review
                2. View Reviews
                3. Exit
                """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Review details (clientId, driverId, companyId, rating, description):");
                    try {
                        System.out.print("Enter client ID: ");
                        int clientId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter company ID: ");
                        int companyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter rating: ");
                        int rating = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter description: ");
                        String description = scanner.nextLine();  // Keep description as a String

                        // Call the addReview method with the correct parameters
                        reviewController.addReview(companyId, driverId, clientId, rating, description);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer input.");
                    }
                    break;

                case 2:
                    System.out.println("List of Reviews:");
                    reviewController.getAllReviews().forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();  // Close scanner only when exiting the loop
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }


}
