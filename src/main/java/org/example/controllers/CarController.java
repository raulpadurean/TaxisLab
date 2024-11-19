package org.example.controllers;

import org.example.models.Car;
import org.example.services.CarService;

import java.util.List;

/**
 * Controller class for managing Car entities.
 * Provides methods to handle operations such as adding, retrieving, updating,
 * and deleting cars. This class interacts with the {@link CarService} to perform
 * the required business logic.
 */
public class CarController {

    private final CarService carService;

    /**
     * Constructs a new {@code CarController} with the specified service.
     *
     * @param carService the service instance for handling car operations
     */
    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Adds a new Car.
     *
     * @param id       the unique identifier of the car
     * @param brand    the brand of the car
     * @param model    the model of the car
     * @param plateNr  the number plate of the car
     * @param driverId the ID of the driver assigned to the car
     */
    public void addCar(Integer id, String brand, String model, String plateNr, int driverId) {
        Car car = new Car(id, brand, model, plateNr, driverId);
        carService.addCar(car);
    }

    /**
     * Retrieves a Car by its ID.
     *
     * @param id the unique identifier of the car
     * @return the {@link Car} object with the specified ID, or {@code null} if not found
     */
    public Car getCar(int id) {
        return carService.getCar(id);
    }

    /**
     * Retrieves all Cars.
     *
     * @return a list of all {@link Car} objects
     */
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    /**
     * Updates an existing Car using a {@link Car} object.
     *
     * @param car the {@code Car} object containing the updated details
     */
    public void updatesCar(Car car) {
        carService.updateCar(car);
    }

    /**
     * Updates an existing Car using its attributes.
     *
     * @param id       the unique identifier of the car
     * @param brand    the brand of the car
     * @param model    the model of the car
     * @param plateNr  the number plate of the car
     * @param driverId the ID of the driver assigned to the car
     */
    public void updateCar(Integer id, String brand, String model, String plateNr, int driverId) {
        Car car = new Car(id, brand, model, plateNr, driverId);
        carService.updateCar(car);
    }

    /**
     * Deletes a Car by its ID.
     *
     * @param carId the unique identifier of the car to be deleted
     */
    public void deleteCar(Integer carId) {
        carService.deleteCar(carId);
    }
}
