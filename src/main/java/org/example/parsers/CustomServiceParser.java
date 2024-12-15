package org.example.parsers;

import org.example.models.CustomService;
import org.example.models.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class CustomServiceParser {

    public static Function<ResultSet, CustomService> createParser() {
        return rs -> {
            try {
                CustomService customService = new CustomService();
                customService.setId(rs.getInt("id"));
                customService.setName(rs.getString("name"));
                customService.setPricePerKm(rs.getDouble("price_per_km"));
                customService.setType(ServiceType.CUSTOM);
                customService.setExtras(rs.getString("extras")); // Assuming "extras" is in the same ResultSet
                return customService;
            } catch (SQLException e) {
                throw new RuntimeException("Failed to parse CustomService", e);
            }
        };
    }
}