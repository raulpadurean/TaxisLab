package org.example.controllers;

import org.example.models.Car;
import org.example.services.CarService;

import java.io.IOException;
import java.util.List;

/**
 * Controller class responsible for handling CRUD operations for {@link Car} objects.
 * The controller delegates the actual business logic to the {@link CarService} class.
 * This class provides methods to add, retrieve, update, and delete cars.
 */
public class CarController {

    private final CarService carService;

    /**
     * Constructs a {@link CarController} with the specified {@link CarService}.
     * This service is used to delegate the business logic related to cars.
     *
     * @param carService The {@link CarService} to delegate the business logic.
     */
    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Adds a new {@link Car} with the specified details.
     * Delegates to the {@link CarService} to perform the actual operation.
     *
     * @param brand     The brand of the car.
     * @param model     The model of the car.
     * @param plateNr   The plate number of the car.
     * @param driverId  The ID of the driver associated with the car.
     */
    public void addCar(String brand, String model, String plateNr, Integer driverId) {
        carService.addCar(brand, model, plateNr, driverId); // Pass details to service
    }

    /**
     * Retrieves a {@link Car} by its unique identifier.
     * Delegates to the {@link CarService} to perform the actual retrieval.
     *
     * @param id The unique identifier of the car.
     * @return The {@link Car} object with the specified ID, or {@code null} if not found.
     */
    public Car getCar(int id) {
        return carService.getCar(id);
    }

    /**
     * Retrieves all {@link Car} objects.
     * Delegates to the {@link CarService} to perform the actual retrieval.
     *
     * @return A list of all {@link Car} objects.
     */
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    /**
     * Updates the details of an existing {@link Car}.
     * Delegates to the {@link CarService} to perform the actual update.
     *
     * @param id        The unique identifier of the car to update.
     * @param brand     The new brand of the car.
     * @param model     The new model of the car.
     * @param plateNr   The new plate number for the car.
     * @param driverId  The new driver ID associated with the car.
     */
    public void updateCar(Integer id, String brand, String model, String plateNr, Integer driverId) {
        carService.updateCar(id, brand, model, plateNr, driverId);
    }

    /**
     * Deletes a {@link Car} by its unique identifier.
     * Delegates to the {@link CarService} to perform the actual deletion.
     *
     * @param carId The unique identifier of the car to delete.
     *
     *
     */
    public void deleteCar(Integer carId) {
        carService.deleteCar(carId);
    }

    /**
     * Loads cars from a CSV file using the CarService.
     * Each line in the file is parsed into a Car object.
     *
     * @param filePath The path to the CSV file containing car data.
     * @throws IOException If an error occurs while reading the file.
     */
    public void loadCarsFromFile(String filePath) throws IOException {
        carService.loadCarsFromFile(filePath);
    }

    /**
     * Filters the list of cars by the specified brand using the CarService.
     * The filtering is case-insensitive.
     *
     * @param brand The brand to filter cars by.
     * @return A list of cars that match the specified brand.
     */
    public List<Car> filterCarsByBrand(String brand) {
        return carService.filterCarsByBrand(brand);
    }

    /**
     * Retrieves the list of cars sorted by plate number from the CarService.
     * The sorting is performed in ascending order and is case-insensitive.
     *
     * @return A sorted list of cars by plate number.
     */
    public List<Car> getSortedCars() {
        return carService.getSortedCars();
    }

    /**
     * Sorts the list of cars by their plate number in ascending order using the CarService.
     * The comparison is case-insensitive to ensure uniform sorting.
     */
    public void sortCarsByPlateNr() {
        carService.sortCarsByPlateNr();
    }

}
