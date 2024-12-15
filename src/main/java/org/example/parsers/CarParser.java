package org.example.parsers;

import org.example.exceptions.DatabaseException;
import org.example.models.Car;
import org.example.models.Driver;
import org.example.repositories.IRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class CarParser {
    public static Function<ResultSet, Car> createParser(IRepository<Driver> driverRepository) {
        return rs -> {
            try {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setPlateNr(rs.getString("plate_nr"));

                int driverId = rs.getInt("driver_id");
                if (!rs.wasNull()) {
                    car.setDriver(driverRepository.read(driverId));
                }
                return car;
            } catch (SQLException e) {
                throw new DatabaseException("Failed to parse Car", e);
            }
        };
    }
}