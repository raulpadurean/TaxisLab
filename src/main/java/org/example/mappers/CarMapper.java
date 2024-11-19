package org.example.mappers;

import org.example.models.Car;
import org.example.models.Driver;

public class CarMapper implements EntityMapper<Car> {

    private final DriverMapper driverMapper = new DriverMapper(); // DriverMapper instance for helper methods

    @Override
    public String toCSV(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null.");
        }

        // Convert the `Car` object into a CSV string
        return String.join(",",
                String.valueOf(car.getId()),                              // Car ID
                car.getBrand() != null ? car.getBrand() : "",            // Brand
                car.getModel() != null ? car.getModel() : "",            // Model
                car.getPlateNr() != null ? car.getPlateNr() : "",        // Plate Number
                car.getDriver() != null ? driverMapper.toCSV(car.getDriver()) : "" // Use DriverMapper for Driver serialization
        );
    }

    @Override
    public Car fromCSV(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            throw new IllegalArgumentException("CSV string cannot be null or empty.");
        }

        // Split the CSV string into parts
        String[] parts = csv.split(",", 5); // Expecting 5 fields
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid CSV format. Expected 5 fields.");
        }

        try {
            int id = Integer.parseInt(parts[0].trim());            // Car ID
            String brand = parts[1].trim();                       // Brand
            String model = parts[2].trim();                       // Model
            String plateNr = parts[3].trim();                     // Plate Number
            Driver driver = parts[4].trim().isEmpty() ? null : driverMapper.fromCSV(parts[4].trim()); // Use DriverMapper for Driver deserialization

            return new Car(id, brand, model, plateNr, driver);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing numeric fields: " + e.getMessage(), e);
        }
    }
}
