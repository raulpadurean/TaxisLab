package org.example.services;

import java.util.List;
import org.example.models.Car;
import org.example.models.Driver;
import org.example.repositories.IRepository;

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
}
