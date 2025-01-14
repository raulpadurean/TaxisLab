package org.example.services;

import org.example.models.Car;
import org.example.models.Driver;
import org.example.repositories.IRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing cars in the taxi service application.
 * Provides methods for adding, retrieving, updating, and deleting cars.
 */
public class CarService {

    private final IRepository<Car> carRepository;
    private final IRepository<Driver> driverRepository;

    /**
     * Constructs a CarService with the specified car and driver repositories.
     *
     * @param carRepository    The repository that handles CRUD operations for Car objects.
     * @param driverRepository The repository that handles CRUD operations for Driver objects.
     */
    public CarService(IRepository<Car> carRepository, IRepository<Driver> driverRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    /**
     * Adds a new car with automatic ID generation.
     * Validates that the driver with the specified ID exists before adding the car.
     *
     * @param brand    The brand of the car.
     * @param model    The model of the car.
     * @param plateNr  The license plate number of the car.
     * @param driverId The ID of the driver associated with the car.
     * @throws IllegalArgumentException If the driver with the specified ID does not exist.
     */
    public void addCar(String brand, String model, String plateNr, Integer driverId) {
        // Validate if the driver exists
        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver with ID " + driverId + " does not exist.");
        }

        // Generate a new ID for the car
        Integer carId = carRepository.readAll().size() + 1;

        // Create a new car object
        Car car = new Car(carId, brand, model, plateNr, driver);

        // Save the car in the repository
        carRepository.create(car);
    }

    /**
     * Retrieves a car by its ID.
     *
     * @param id The ID of the car to retrieve.
     * @return The Car object with the specified ID.
     */
    public Car getCar(int id) {
        return carRepository.read(id);
    }

    /**
     * Retrieves all cars from the repository.
     *
     * @return A list of all Car objects.
     */
    public List<Car> getAllCars() {
        return carRepository.readAll();
    }

    /**
     * Updates an existing car with the specified ID.
     * Validates that both the car and the driver with the specified IDs exist before updating.
     *
     * @param id       The ID of the car to update.
     * @param brand    The updated brand of the car.
     * @param model    The updated model of the car.
     * @param plateNr  The updated license plate number of the car.
     * @param driverId The ID of the updated driver associated with the car.
     * @throws IllegalArgumentException If the car or driver with the specified IDs do not exist.
     */
    public void updateCar(Integer id, String brand, String model, String plateNr, Integer driverId) {
        // Validate if the car exists
        Car existingCar = carRepository.read(id);
        if (existingCar == null) {
            throw new IllegalArgumentException("Car with ID " + id + " does not exist.");
        }

        // Validate if the driver exists
        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver with ID " + driverId + " does not exist.");
        }

        // Create the updated car object
        Car updatedCar = new Car(id, brand, model, plateNr, driver);

        // Update the car in the repository
        carRepository.update(updatedCar);
    }

    /**
     * Deletes a car by its ID.
     *
     * @param carId The ID of the car to delete.
     */
    public void deleteCar(Integer carId) {
        carRepository.delete(carId);
    }

    /**
     * The list of cars managed by this service.
     */
    private List<Car> cars = new ArrayList<>();

    /**
     * Loads cars from a CSV file and stores them in the service's list.
     * Each line in the file is expected to represent a car in a specific format.
     *
     * @param filePath The path to the CSV file containing car data.
     * @throws IOException If an error occurs while reading the file.
     */
    public void loadCarsFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    cars.add(Car.parse(line)); // Parse each line into a Car object
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid line: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Sorts the list of cars by their plate number in ascending order.
     * The comparison is case-insensitive to ensure uniform sorting.
     */
    public void sortCarsByPlateNr() {
        cars.sort(Comparator.comparing(Car::getPlateNr, String.CASE_INSENSITIVE_ORDER));
    }

    /**
     * Retrieves the list of cars after sorting. This method returns a copy
     * of the list to ensure the internal state of the service is not modified.
     *
     * @return A sorted list of cars by plate number.
     */
    public List<Car> getSortedCars() {
        return new ArrayList<>(cars); // Return a copy to preserve encapsulation
    }

    /**
     * Filters the list of cars by the specified brand.
     * The filtering is case-insensitive.
     *
     * @param brand The brand to filter cars by.
     * @return A list of cars that match the specified brand.
     */
    public List<Car> filterCarsByBrand(String brand) {
        return cars.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }
}
