package org.example.controllers;

import org.example.models.Car;
import org.example.services.CarService;

import java.util.List;

public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    public void addCar(String brand, String model, String plateNr, Integer driverId){

        carService.addCar(brand, model, plateNr, driverId);
    }

    public Car getCar(int id){
        return carService.getCar(id);
    }

    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    public void updateCar(Integer id,String brand,String model,String plateNr, Integer driverId){

        carService.updateCar(id, brand, model, plateNr, driverId);
    }

    public void deleteCar(Integer carId) {
        carService.deleteCar(carId);

    }
}
