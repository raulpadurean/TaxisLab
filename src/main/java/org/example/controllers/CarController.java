package org.example.controllers;

import org.example.models.Car;
import org.example.services.CarService;

import java.util.List;

public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Add a car
    public void addCar(String brand, String model, String plateNr, Integer driverId) {
        carService.addCar(brand, model, plateNr, driverId); // Pass details to service
    }

    // Get a car by ID
    public Car getCar(int id) {
        return carService.getCar(id);
    }

    // Get all cars
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    // Update a car
    public void updateCar(Integer id, String brand, String model, String plateNr, Integer driverId) {
        carService.updateCar(id, brand, model, plateNr, driverId);
    }

    // Delete a car
    public void deleteCar(Integer carId) {
        carService.deleteCar(carId);
    }
}
