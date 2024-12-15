package org.example.ui;

import org.example.controllers.CarController;
import org.example.models.Car;
import org.example.models.Driver;
import org.example.repositories.IRepository;
import org.example.services.CarService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CarMenu {

    /**
     * Displays the Car Management menu and processes user input.
     *
     * Options include:
     * - Add a car.
     * - View all cars.
     * - Retrieve a car by ID.
     * - Update a car.
     * - Delete a car.
     * - Filter Car By Brand
     * - Sort Cars by Number Plate
     * - Exit the menu.
     */

    public static void show(IRepository<Car> carRepo, IRepository<Driver> driverRepo) {
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
                    7. Sort cars by plate number
                    8. Exit
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
                    String fileCars = "C:\\Users\\pc\\IdeaProjects\\TaxisLab\\data\\cars.csv"; // Path to the CSV file


                    try {
                        // Load cars from the file
                        carController.loadCarsFromFile(fileCars);

                        // Sort cars by plate number
                        carController.sortCarsByPlateNr();

                        // Display sorted cars
                        System.out.println("Cars sorted by plate number:");
                        carController.getSortedCars().forEach(System.out::println);

                    } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                    }


                    break;

                case 8:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
