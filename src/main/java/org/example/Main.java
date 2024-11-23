package org.example;

import org.example.controllers.*;
import org.example.models.*;
import org.example.repositories.IRepository;
import org.example.repositories.FileRepository;
import org.example.services.*;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
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


    public static void main(String[] args) throws ParseException {


        Scanner scanner = new Scanner(System.in);
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
        while (true) {
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
                    basicSerivceMenu();
                    break;
                case 8:
                    customSerivceMenu();
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

    public static void companyMenu() {
        CompanyService companyService = new CompanyService(companyRepo);
        CompanyController companyController = new CompanyController(companyService);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    Options:
                    1. Add Company
                    2. View Companies
                    3. Delete Company
                    5. Exit
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
                    companyController.addCompany(name, email, address, phone); // No need to pass ID
                    break;
                case 2:
                    System.out.println("List of Companies:");
                    companyController.getAllCompanies().forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Enter the ID of the company to delete:");
                    int companyId = scanner.nextInt();
                    companyController.deleteCompany(companyId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }


    public static void companyDriversMenu() {
        CompanyDriverService companyDriverService = new CompanyDriverService(companyDriverRepo, driverRepo, companyRepo);
        CompanyDriverController companyDriverController = new CompanyDriverController(companyDriverService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Company Driver
                    2. View Company Driver
                    3. Delete Company Driver
                    
                    5. Exit
                    
                    
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Company Driver details (driver id, company id):");

                    Integer driverId = Integer.parseInt(scanner.nextLine());
                    int companyId = Integer.parseInt(scanner.nextLine());

                    companyDriverController.addCompanyDriver(driverId, companyId);
                    break;
                case 2:
                    System.out.println("List of Company Drivers:");
                    companyDriverController.getAllCompanyDrivers().forEach(System.out::println);
                    break;
                case 3:

                    companyDriverController.deleteCompanyDriver(readId(scanner));
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void driverMenu() {
        DriverService driverService = new DriverService(driverRepo);
        DriverController driverController = new DriverController(driverService);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    Options:
                    1. Add Driver
                    2. View Drivers
                    3. Delete Driver
                    5. Exit
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
                    driverController.addDriver(name, email, address, phone); // No ID needed
                    break;
                case 2:
                    System.out.println("List of Drivers:");
                    driverController.getAllDrivers().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Enter the ID of the driver to delete:");
                    int driverId = scanner.nextInt();
                    driverController.deleteDriver(driverId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }


    public static void driverScheduleMenu() throws ParseException {
        DriverScheduleService driverScheduleService = new DriverScheduleService(driverScheduleRepo, driverRepo, companyRepo);
        DriverScheduleController driverScheduleController = new DriverScheduleController(driverScheduleService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add DriverSchedule
                    2. View DriverSchedule
                    3. Delete DriverSchedule
                    5. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter driver schedule (id,driverId, companyId, checkIn, checkout):");
                    int id = Integer.parseInt(scanner.nextLine());
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

                    driverScheduleController.addDriverSchedule(id, driverId, companyId, checkIn, checkOut);

                    break;


                case 2:
                    System.out.println("List of DriverSchedules:");
                    driverScheduleController.getAllDriverSchedules().forEach(System.out::println);
                    break;

                case 3:
                    driverScheduleController.deleteDriverSchedule(readId(scanner));
                    break;


                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void carMenu() {
        CarService carService = new CarService(carRepo, driverRepo);
        CarController carController = new CarController(carService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Car
                    2. View Car
                    3. Delete Car
                    4. Update
                    5. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Car details (id ,brand, model, number plate, driver id):");

                    String brand = scanner.nextLine();
                    String model = scanner.nextLine();
                    String plateNr = scanner.nextLine();
                    int driverId = Integer.parseInt(scanner.nextLine());
                    carController.addCar(brand, model, plateNr, driverId);
                    break;
                case 2:
                    System.out.println("List of Cars:");
                    carController.getAllCars().forEach(System.out::println);
                    break;
                case 3:

                    carController.deleteCar(readCarId(scanner));
                    break;


                case 4:
                    System.out.println("Enter Car details (id ,brand, model, number plate, driver id):");

                    Integer id = Integer.parseInt(scanner.nextLine());
                    brand = scanner.nextLine();
                    model = scanner.nextLine();
                    plateNr = scanner.nextLine();
                    driverId = Integer.parseInt(scanner.nextLine());

                    carController.updateCar(id, brand, model, plateNr, driverId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void clientMenu() {
        ClientService clientService = new ClientService(clientRepo);
        ClientController clientController = new ClientController(clientService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Client
                    2. View Client
                    3. Delete Client
                    
                    5. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter client details (id, name, email, address, phone):");
                    int id = Integer.parseInt(scanner.nextLine());
                    String name = scanner.nextLine();
                    String email = scanner.nextLine();
                    String address = scanner.nextLine();
                    String phone = scanner.nextLine();
                    clientController.addClient(id, name, email, address, phone);
                    break;
                case 2:
                    System.out.println("List of Clients:");
                    clientController.getAllClients().forEach(System.out::println);
                    break;
                case 3:

                    clientController.deleteClient(readId(scanner));
                    break;


                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void basicSerivceMenu() {
        BasicServiceService basicServiceService = new BasicServiceService(basicServiceRepo);
        BasicServiceController basicServiceController = new BasicServiceController(basicServiceService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Basic Service
                    2. View Basic Serivce
                    3. Delete Basic Service
                    5. Exit
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
                    basicServiceController.deleteBasicService(readBasicServiceId(scanner));
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option");
            }

        }

    }

    public static void customSerivceMenu() {
        CustomServiceService customServiceService = new CustomServiceService(customServiceRepo);
        CustomServiceController customServiceController = new CustomServiceController(customServiceService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Custom Service
                    2. View Custom Service
                    3. Delete Custom Service
                    
                    4. Exit
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

                    customServiceController.deleteCustomService(readId(scanner));
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option");
            }

        }


    }

    public static void orderMenu() {
        OrderService orderService = new OrderService(orderRepo, serviceRepo, clientRepo, driverRepo, companyRepo);
        OrderController orderController = new OrderController(orderService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Order
                    2. View Order
                    3. Delete Order
                    5. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Order details (serviceId, totalKm, clientId, driverId, companyId, datetime):");
                    try {
                        System.out.print("Enter service ID: ");
                        Integer serviceId = Integer.parseInt(scanner.nextLine());

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

                    // scanner.close();
                    break;
                case 2:
                    System.out.println("List of Companies:");
                    orderController.getAllOrders().forEach(System.out::println);
                    break;
                case 3:

                    orderController.deleteOrder(readId(scanner));
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void reviewMenu() {
        ReviewService reviewService = new ReviewService(reviewRepo, driverRepo, companyRepo, clientRepo);
        ReviewController reviewController = new ReviewController(reviewService);
        Scanner scanner = new Scanner(System.in);

// Example: Initialize Company objects
        Company company300 = new Company(109, "Pritax", "pritax@a.com", "Observator", "9942");
        Company company1 = new Company(101, "Company A", "email@a.com", "Address A", "123456");
        Company company2 = new Company(102, "Company B", "email@b.com", "Address B", "789012");

// Example: Initialize Driver objects
        Driver driver1 = new Driver(201, "Driver One", "one@drivers.com", "Address D1", "123456");
        Driver driver2 = new Driver(202, "Driver Two", "two@drivers.com", "Address D2", "789012");
        Driver driver3 = new Driver(203, "Driver Three", "three@drivers.com", "Address D3", "345678");

// Example: Initialize Client objects
        Client client1 = new Client(301, "Client A", "clientA@a.com", "Address C1", "987654");
        Client client2 = new Client(302, "Client B", "clientB@b.com", "Address C2", "543210");
        Client client3 = new Client(303, "Client C", "clientC@c.com", "Address C3", "678901");

// Updated Review list
        List<Review> reviews = Arrays.asList(
                new Review(1, 4, "Good service.", company1, driver1, client1),
                new Review(2, 5, "Excellent driver.", company1, driver1, client2),
                new Review(3, 3, "Average service.", company2, driver3, client3),
                new Review(4, 4, "Reliable driver.", company1, driver2, client1),
                new Review(9, 10, "Exceptional service.", company300, driver3, client2),
                new Review(5, 3, "Decent experience.", company1, driver1, client3)
        );


        while (true) {
            System.out.println("""
                    Options:
                    1. Add Review
                    2. View Reviews
                    3. Delete Review
                    5. Exit
                    6. Calculator
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Review details (reviewId ,clientId, driverId, companyId, rating, description):");
                    try {
                        System.out.print("Enter review ID: ");
                        Integer id = Integer.parseInt(scanner.nextLine());

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
                        reviewController.addReview(id, companyId, driverId, clientId, rating, description);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer input.");
                    }
                    break;

                case 2:
                    System.out.println("List of Reviews:");
                    reviewController.getAllReviews().forEach(System.out::println);
                    break;

                case 3:

                    reviewController.deleteReview(readId(scanner));
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();  // Close scanner only when exiting the loop
                    return;

                case 6:

                    // Find the best-rated driver for company
                    System.out.println("Enter Company Id");
//                    int companyId = Integer.parseInt(scanner.nextLine());
////                    Map.Entry<Driver, Double> result = reviewController.findBestRatedDriverInCompany(companyId, drivers, reviews);
////
//                    if (result != null) {
//                        System.out.printf("The best-rated driver for company " + companyId +
//                                " is " + result.getKey().getName() +
//                                " with an average rating of " + result.getValue() + "%n");
//                    } else {
//                        System.out.printf("No valid drivers found for company " + companyId + "%n");
//                    }
//                    break;

                default:
                    System.out.println("Invalid option");
            }
        }
    }


    private static int readCarId(Scanner scanner) {
        System.out.print("Enter car ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int readId(Scanner scanner) {
        System.out.print("Enter the ID of the object you want to delete: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int readBasicServiceId(Scanner scanner) {
        System.out.print("Enter Basic Service ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

}
