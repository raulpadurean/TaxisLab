package org.example.parsers;

import org.example.exceptions.DatabaseException;
import org.example.models.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class DriverParser {
    public static Function<ResultSet, Driver> createParser() {
        return rs -> {
            try {
                Driver driver = new Driver();
                driver.setId(rs.getInt("id"));
                driver.setName(rs.getString("name"));
                driver.setEmail(rs.getString("email"));
                driver.setAddress(rs.getString("address"));
                driver.setPhone(rs.getString("phone"));
                return driver;
            } catch (SQLException e) {
                throw new DatabaseException("Failed to parse Driver", e);
            }
        };
    }
}
