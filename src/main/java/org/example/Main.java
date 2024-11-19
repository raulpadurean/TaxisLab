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
import java.util.*;

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
                    1. Show Company
                    2. Show Order
                    3. Basic Service
                    4. Custom Service
                    5. Reviews
                    6. Show Car
                    7. Show Client
                    8. Show Driver
                    9. Show Driver Schedule
                    10. Show Company Drivers
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
                    companyDriversMenu();
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
                    3. Delete Car
                    4. Update
                    5. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Car details (id ,brand, model, number plate, driver id):");

                    Integer id = Integer.parseInt(scanner.nextLine());
                    String brand = scanner.nextLine();
                    String model = scanner.nextLine();
                    String plateNr = scanner.nextLine();
                    int driverId = Integer.parseInt(scanner.nextLine());
                    carController.addCar(id,brand, model , plateNr, driverId);
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

                    id = Integer.parseInt(scanner.nextLine());
                    brand = scanner.nextLine();
                    model = scanner.nextLine();
                    plateNr = scanner.nextLine();
                    driverId = Integer.parseInt(scanner.nextLine());

                    carController.updateCar(id,brand, model , plateNr, driverId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static int readCarId(Scanner scanner) {
        System.out.print("Enter car ID: ");
        return Integer.parseInt(scanner.nextLine());
    }


    public static void companyDriversMenu() {
        IRepository<CompanyDriver> companyDriverRepo = new InMemoryRepository<>();
        CompanyDriverService companyDriverService = new CompanyDriverService(companyDriverRepo);
        CompanyDriverController companyDriverController = new CompanyDriverController(companyDriverService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Company Driver
                    2. View Company Driver
                    3. Delete Company Driver
                    4. Update Company Driver
                    5. Exit
         

                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Company Driver details (driver id, company id):");

                    Integer driverId = Integer.parseInt(scanner.nextLine());
                    int companyId = Integer.parseInt(scanner.nextLine());

                    companyDriverController.addCompanyDriver(driverId,companyId);
                    break;
                case 2:
                    System.out.println("List of Company Drivers:");
                    companyDriverController.getAllCompanyDrivers().forEach(System.out::println);
                    break;
                case 3:

                    companyDriverController.deleteCompanyDriver(readId(scanner));
                    break;

                case 4:
                    System.out.println("Enter Company Driver details (driver id, company id):");

                         driverId = Integer.parseInt(scanner.nextLine());
                         companyId = Integer.parseInt(scanner.nextLine());

                    companyDriverController.updateCompanyDriver(driverId,companyId);
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
        IRepository<Client> clientRepo = new InMemoryRepository<>();
        ClientService clientService = new ClientService(clientRepo);
        ClientController clientController = new ClientController(clientService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Client
                    2. View Client
                    3. Delete Client
                    4. Update Client
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
                    clientController.addClient(id,name, email, address, phone);
                    break;
                case 2:
                    System.out.println("List of Clients:");
                    clientController.getAllClients().forEach(System.out::println);
                    break;
                case 3:

                    clientController.deleteClient(readId(scanner));
                    break;
                case 4:
                    System.out.println("Enter client details (id, name, email, address, phone):");
                     id = Integer.parseInt(scanner.nextLine());
                     name = scanner.nextLine();
                     email = scanner.nextLine();
                     address = scanner.nextLine();
                    phone = scanner.nextLine();
                    clientController.updateClient(id,name, email, address, phone);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
    private static int readId(Scanner scanner) {
        System.out.print("Enter the ID of the object you want to delete: ");
        return Integer.parseInt(scanner.nextLine());
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
                    3. Delete Driver
                    4. Update Driver
                    5. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter driver details (id, name, email, address, phone):");
                    int id = Integer.parseInt(scanner.nextLine());
                    String name = scanner.nextLine();
                    String email = scanner.nextLine();
                    String address = scanner.nextLine();
                    String phone = scanner.nextLine();
                    driverController.addDriver(id,name, email, address, phone);
                    break;
                case 2:
                    System.out.println("List of Drivers:");
                    driverController.getAllDrivers().forEach(System.out::println);
                    break;
                case 3:

                    driverController.deleteDriver(readId(scanner));
                    break;

                case 4:
                    System.out.println("Enter driver details (id, name, email, address, phone):");
                    id = Integer.parseInt(scanner.nextLine());
                    name = scanner.nextLine();
                    email = scanner.nextLine();
                    address = scanner.nextLine();
                    phone = scanner.nextLine();
                    driverController.updateDriver(id,name, email, address, phone);
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
        IRepository<DriverSchedule> driverScheduleRepo = new InMemoryRepository<>();
        DriverScheduleService driverScheduleService = new DriverScheduleService(driverScheduleRepo);
        DriverScheduleController driverScheduleController = new DriverScheduleController(driverScheduleService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add DriverSchedule
                    2. View DriverSchedule
                    3. Delete DriverSchedule
                    4. Update DriverSchedule
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

                    driverScheduleController.addDriverSchedule(id,driverId, companyId, checkIn, checkOut);

                    break;

                    case 2:
                    System.out.println("List of DriverSchedules:");
                    driverScheduleController.getAllDriverSchedules().forEach(System.out::println);
                    break;

                    case 3:
                        driverScheduleController.deleteDriverSchedule(readId(scanner));
                    break;

                    case 4:

                    System.out.println("Enter driver schedule (id,driverId, companyId, checkIn, checkout):");
                    id = Integer.parseInt(scanner.nextLine());
                    driverId = Integer.parseInt(scanner.nextLine());
                    companyId = Integer.parseInt(scanner.nextLine());

                    System.out.println("yyyy-MM-dd");
                     iDate = scanner.next();
                     formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                     checkIn = formatter1.parse(iDate);

                    System.out.println("yyyy-MM-dd");
                     oDate = scanner.next();
                     formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                     checkOut = formatter2.parse(oDate);

                    driverScheduleController.updateDriverSchedule(id,driverId, companyId, checkIn, checkOut);

                    break;

                    case 5:
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
                    3. Delete Companies
                    4. Update Companies
                    5. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter company details (id, name, email, address, phone):");
                    Integer id = Integer.parseInt(scanner.nextLine());
                    String name = scanner.nextLine();
                    String email = scanner.nextLine();
                    String address = scanner.nextLine();
                    String phone = scanner.nextLine();
                    companyController.addCompany(id,name, email, address, phone);
                    break;
                case 2:
                    System.out.println("List of Companies:");
                    companyController.getAllCompanies().forEach(System.out::println);
                    break;

                case 3:

                    companyController.deleteCompany(readId(scanner));
                    break;

                case 4:
                    System.out.println("Enter company details (id, name, email, address, phone):");
                     id = Integer.parseInt(scanner.nextLine());
                     name = scanner.nextLine();
                     email = scanner.nextLine();
                    address = scanner.nextLine();
                     phone = scanner.nextLine();
                    companyController.updateCompany(id,name, email, address, phone);
                    break;

                case 5:
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
                    3. Delete Order
                    4. Update Order
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

                case 4:
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

                        orderController.updateOrder(serviceId, totalKm, clientId, driverId, companyId, datetime);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer input.");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format.");
                    }

                    // scanner.close();
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
        IRepository<BasicService> basicServiceRepo = new InMemoryRepository<>();
        BasicServiceService basicServiceService = new BasicServiceService(basicServiceRepo);
        BasicServiceController basicServiceController = new BasicServiceController(basicServiceService);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Basic Service
                    2. View Basic Serivce
                    3. Delete Basic Service
                    4. Update Basic Service
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

                case 4:
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
                        basicServiceService.updateBasicService(basicService);  // Pass the object

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer input.");
                    }
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

    private static int readBasicServiceId(Scanner scanner) {
        System.out.print("Enter Basic Service ID: ");
        return Integer.parseInt(scanner.nextLine());
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
                    2. View Custom Service
                    3. Delete Custom Service
                    4. Update Custom Service
                    5. Exit
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
                        customServiceService.updateCustomService(customService);  // Pass the object

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer input.");
                    }
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

    public static void reviewMenu() {
        IRepository<Review> reviewRepo = new InMemoryRepository<>();
        ReviewService reviewService = new ReviewService(reviewRepo);
        ReviewController reviewController = new ReviewController(reviewService);
        Scanner scanner = new Scanner(System.in);

        Company company300 = new Company(109, "Pritax", "pritax@a.com", "Observator", "9942");
        Company company1 = new Company(101, "Company A", "email@a.com", "Address A", "123456");
        Company company2 = new Company(102, "Company B", "email@b.com", "Address B", "789012");

        Driver driver1 = new Driver(201, "Driver One", "one@drivers.com", "Address D1", "123456");
        Driver driver2 = new Driver(202, "Driver Two", "two@drivers.com", "Address D2", "789012");
        Driver driver3 = new Driver(203, "Driver Three", "three@drivers.com", "Address D3", "345678");

        List<Driver> drivers = Arrays.asList(driver1, driver2, driver3);

        List<Review> reviews = Arrays.asList(
                new Review(1, 101, 201, 301, 4, "Good service."),
                new Review(2, 101, 201, 302, 5, "Excellent driver."),
                new Review(3, 102, 203, 303, 3, "Average service."),
                new Review(4, 101, 202, 304, 4, "Reliable driver."),
                new Review(9, 109, 203, 304, 10, "Reliable driver."),
                new Review(5, 101, 201, 305, 3, "Decent experience.")
        );

        while (true) {
            System.out.println("""
                Options:
                1. Add Review
                2. View Reviews
                3. Delete Review
                5. Exit
                6. Calculator
                7. Update Review
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
                        reviewController.addReview(id,companyId, driverId, clientId, rating, description);
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
                    int companyId = Integer.parseInt(scanner.nextLine());
                    Map.Entry<Driver, Double> result = reviewController.findBestRatedDriverInCompany(companyId, drivers, reviews);

                if (result != null) {
                    System.out.printf("The best-rated driver for company " + companyId +
                                    " is " + result.getKey().getName() +
                                    " with an average rating of " + result.getValue() + "%n" );
                } else {
                    System.out.printf("No valid drivers found for company " + companyId + "%n" );
                }
                break;

                default:
                    System.out.println("Invalid option");

                case 7:
                    System.out.println("Enter Review details (reviewId ,clientId, driverId, companyId, rating, description):");
                    try {
                        System.out.print("Enter review ID: ");
                        Integer id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter client ID: ");
                        int clientId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter company ID: ");
                         companyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter rating: ");
                        int rating = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter description: ");
                        String description = scanner.nextLine();  // Keep description as a String

                        // Call the addReview method with the correct parameters
                        reviewController.updateReview(id,companyId, driverId, clientId, rating, description);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer input.");
                    }
                    break;
            }
        }
    }





}
