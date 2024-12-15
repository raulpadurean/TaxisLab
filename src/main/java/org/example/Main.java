package org.example;

import org.example.models.*;
import org.example.parsers.*;
import org.example.repositories.DbRepository;
import org.example.repositories.IRepository;
import org.example.repositories.FileRepository;
import org.example.repositories.InMemoryRepository;
import org.example.ui.*;

import java.sql.ResultSet;
import java.util.*;
import java.util.function.Function;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

/**
 * Main class for the Taxi Service Management application.
 * This class initializes repositories and provides menus for managing various entities.
 */
public class Main {

    private static IRepository<Car> carRepo;
    private static IRepository<BasicService> basicServiceRepo;
    private static IRepository<Client> clientRepo;
    private static IRepository<Company> companyRepo;
    private static IRepository<CompanyDriver> companyDriverRepo;
    private static IRepository<CustomService> customServiceRepo;
    private static IRepository<Driver> driverRepo;
    private static IRepository<DriverSchedule> driverScheduleRepo;
    private static IRepository<Order> orderRepo;
    private static IRepository<Review> reviewRepo;
    private static IRepository<Service> serviceRepo;

    //  Database variables
    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String database = "taxis_lab";
    private static final String username = "root";
    private static final String password = "";
    private static MySQLConnection dbConnection = null;

    //  Repo type
    private static RepoType REPO_TYPE;

    /**
     * The main entry point for the application.
     * Displays the main menu and provides access to various entity management menus.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        REPO_TYPE = RepoType.DATABASE;
        boolean issetRepo = setRepos(REPO_TYPE);

        if (!issetRepo) {
            System.out.println("Invalid repo type provided.\nExiting app...");
            System.exit(1);
        }
        System.out.println("Repo type set to " + REPO_TYPE + ".");

        showHomeMenu();

    }

    private static void setDBConnection() {
        if (REPO_TYPE.toString().equals("DATABASE")) {
            System.out.println("Connecting to Database...");
            dbConnection = MySQLConnection.getConnection(host, port, database, username, password);
            dbConnection.connect();
            System.out.println("Connection to Database successful.");
        }
    }

    public static void showHomeMenu() {
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
            scanner.nextLine();

            switch (choice) {
                case 0 -> {
                    isRunning = false;
                    dbConnection.disConnect();
                    System.out.println("Exiting...");
                }
                case 1->  CompanyMenu.show(companyRepo);
                case 2 ->  DriverMenu.show(driverRepo);
                case 3 -> CompanyDriverMenu.show(companyDriverRepo, driverRepo, companyRepo);
                case 4 -> DriverScheduleMenu.show(driverScheduleRepo, driverRepo, companyRepo);
                case 5 -> CarMenu.show(carRepo, driverRepo);
                case 6 -> ClientMenu.show(clientRepo);
                case 7 -> BasicServiceMenu.show(basicServiceRepo);
                case 8 -> CustomServiceMenu.show(customServiceRepo);
                case 9 -> OrderMenu.show(orderRepo, serviceRepo, clientRepo, driverRepo, companyRepo);
                case 10 -> ReviewMenu.show(reviewRepo,driverRepo, companyRepo, clientRepo);
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static boolean setRepos(RepoType REPO_TYPE) {

        if (REPO_TYPE.equals(RepoType.IN_MEMORY)) {
            carRepo = new InMemoryRepository<>();
            basicServiceRepo = new InMemoryRepository<>();
            clientRepo = new InMemoryRepository<>();
            companyRepo = new InMemoryRepository<>();
            companyDriverRepo = new InMemoryRepository<>();
            customServiceRepo = new InMemoryRepository<>();
            driverRepo = new InMemoryRepository<>();
            driverScheduleRepo = new InMemoryRepository<>();
            orderRepo = new InMemoryRepository<>();
            reviewRepo = new InMemoryRepository<>();
            serviceRepo = new InMemoryRepository<>();
            return true;
        }
        if (REPO_TYPE.equals(RepoType.FILE)) {
            carRepo = new FileRepository<>("data/cars.csv", Car.class);
            basicServiceRepo = new FileRepository<>("data/basic_services.csv", BasicService.class);
            clientRepo = new FileRepository<>("data/clients.csv", Client.class);
            companyRepo = new FileRepository<>("data/companies.csv", Company.class);
            companyDriverRepo = new FileRepository<>("data/company_drivers.csv", CompanyDriver.class);
            customServiceRepo = new FileRepository<>("data/custom_services.csv", CustomService.class);
            driverRepo = new FileRepository<>("data/drivers.csv", Driver.class);
            driverScheduleRepo = new FileRepository<>("data/driver_schedules.csv", DriverSchedule.class);
            orderRepo = new FileRepository<>("data/orders.csv", Order.class);
            reviewRepo = new FileRepository<>("data/reviews.csv", Review.class);
            serviceRepo = new FileRepository<>("data/services.csv", Service.class);
            return true;
        }
        if (REPO_TYPE.equals(RepoType.DATABASE)) {

            setDBConnection();

            Function<ResultSet, Driver> driverParser = DriverParser.createParser();
            Function<ResultSet, Company> companyParser = CompanyParser.createParser();
            Function<ResultSet, Client> clientParser = ClientParser.createParser();

            driverRepo = new DbRepository<>(dbConnection.connection, "drivers", driverParser);
            clientRepo = new DbRepository<>(dbConnection.connection, "clients", clientParser);
            companyRepo = new DbRepository<>(dbConnection.connection, "companies", companyParser);


            Function<ResultSet, Car> carParser = CarParser.createParser(driverRepo);
            carRepo = new DbRepository<>(dbConnection.connection, "cars", carParser);


            Function<ResultSet, CompanyDriver> companyDriverParser = CompanyDriverParser.createParser(driverRepo, companyRepo);
            Function<ResultSet, DriverSchedule> driverScheduleParser = DriverScheduleParser.createParser(driverRepo, companyRepo);

            Function<ResultSet, BasicService> basicServiceParser = BasicServiceParser.createParser();
            Function<ResultSet, CustomService> customServiceParser = CustomServiceParser.createParser();
            Function<ResultSet, Service> serviceParser = ServiceParser.createParser(dbConnection.connection);

            Function<ResultSet, Order> orderParser = OrderParser.createParser(serviceRepo, clientRepo, driverRepo, companyRepo);
            Function<ResultSet, Review> reviewParser = ReviewParser.createParser(companyRepo, driverRepo, clientRepo);

            basicServiceRepo = new DbRepository<>(dbConnection.connection, "basic_services", basicServiceParser);
            companyDriverRepo = new DbRepository<>(dbConnection.connection, "company_drivers", companyDriverParser);
            customServiceRepo = new DbRepository<>(dbConnection.connection, "custom_services", customServiceParser);
            driverScheduleRepo = new DbRepository<>(dbConnection.connection, "driver_schedules", driverScheduleParser);
            orderRepo = new DbRepository<>(dbConnection.connection, "orders", orderParser);
            reviewRepo = new DbRepository<>(dbConnection.connection, "reviews", reviewParser);
            serviceRepo = new DbRepository<>(dbConnection.connection, "services", serviceParser);

            return true;
        }
        return false;
    }

}
