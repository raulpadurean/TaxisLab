package org.example.services;

import java.util.List;
import org.example.models.Car;
import org.example.models.Driver;
import org.example.repositories.IRepository;

public class CarService {
    private final IRepository<Car> carRepository;
    private final IRepository<Driver> driverRepository;

    public CarService(IRepository<Car> carRepository, IRepository<Driver> driverRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    // Add a new car with automatic ID generation
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

    // Get a car by its ID
    public Car getCar(int id) {
        return carRepository.read(id);
    }

    // Get all cars
    public List<Car> getAllCars() {
        return carRepository.readAll();
    }

    // Update an existing car
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

    // Delete a car by its ID
    public void deleteCar(Integer carId) {
        carRepository.delete(carId);
    }
}
