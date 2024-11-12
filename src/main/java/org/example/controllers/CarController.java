package org.example.controllers;

import org.example.models.Car;
import org.example.services.CarService;

import java.util.List;


public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    public void addCar(String brand,String model,String plateNr,int driverId){
        Car car = new Car(0,brand,model,plateNr,driverId);
        carService.addCar(car);
    }

    public Car getCar(int id){
        return carService.getCar(id);
    }

    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    public void updateCar(Car car){
        carService.updateCar(car);
    }

    public void deleteCar(int id){
        carService.deleteCar(id);
    }
}
