package org.example.controllers;

import org.example.models.Car;
import org.example.services.CarService;

import java.util.List;


public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    public void addCar(Integer id,String brand,String model,String plateNr,int driverId){
        Car car = new Car(id,brand,model,plateNr,driverId);
        carService.addCar(car);
    }

    public Car getCar(int id){
        return carService.getCar(id);
    }

    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    public void updatesCar(Car car){
        carService.updateCar(car);
    }


    public void updateCar(Integer id,String brand,String model,String plateNr,int driverId){
        Car car = new Car(id,brand,model,plateNr,driverId);
        carService.updateCar(car);
    }

    public void deleteCar(Integer carId) {
        carService.deleteCar(carId);

    }
}
