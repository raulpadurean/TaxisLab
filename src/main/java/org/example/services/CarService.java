package org.example.services;

import java.util.List;
import org.example.models.Car;
import org.example.repositories.IRepository;

/**
 * A service class responsible for managing {@link Car} objects.
 * Provides business logic to interact with the underlying repository for {@link Car} entities.
 *
 * <p>This class handles operations such as adding, retrieving, updating, and deleting cars.</p>
 */
public class CarService {

    private final IRepository<Car> carRepository;

    /**
     * Constructs a {@link CarService} with the specified repository.
     *
     * @param carRepository The repository to be used for storing and retrieving cars.
     */
    public CarService(IRepository<Car> carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Adds a new car to the repository.
     *
     * <p>This method persists the provided car entity to the repository.</p>
     *
     * @param car The car to be added. Must not be {@code null}.
     */
    public void addCar(Car car) {
        carRepository.create(car);
    }

    /**
     * Retrieves a car by its unique ID.
     *
     * @param id The unique identifier of the car to retrieve.
     * @return The car with the specified ID, or {@code null} if no such car exists.
     */
    public Car getCar(int id) {
        return carRepository.read(id);
    }

    /**
     * Retrieves all cars from the repository.
     *
     * @return A list of all cars in the repository.
     */
    public List<Car> getAllCars() {
        return carRepository.getAll();
    }

    /**
     * Updates an existing car in the repository.
     *
     * <p>If the car already exists in the repository, it will be updated with the new values.</p>
     *
     * @param car The car to update. The car must not be {@code null}.
     */
    public void updateCar(Car car) {
        carRepository.update(car);
    }

    /**
     * Deletes a car from the repository.
     *
     * @param carId The unique ID of the car to be deleted.
     */
    public void deleteCar(Integer carId) {
        carRepository.delete(carId);
    }
}
