package org.example.services;
import java.util.List;
import org.example.models.Car;
import org.example.repositories.IRepository;
public class CarService {
    private final IRepository<Car> carRepository;

    public CarService(IRepository<Car> carRepository){
        this.carRepository = carRepository;
    }

    public void addCar(Car car) {
        carRepository.create(car);
    }

    public Car getCar(int id) {
        return carRepository.read(id);
    }

    public List<Car> getAllCars(){
        return carRepository.getAll();
    }

    public void updateCar(Car car) {
        carRepository.update(car);
    }

    public void deleteCar(Integer carId) {

        carRepository.delete(carId);
    }

}
