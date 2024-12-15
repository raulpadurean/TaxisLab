package org.example.parsers;

import org.example.models.BasicService;
import org.example.models.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class BasicServiceParser {

    public static Function<ResultSet, BasicService> createParser() {
        return rs -> {
            try {
                BasicService basicService = new BasicService();
                basicService.setId(rs.getInt("id"));
                basicService.setName(rs.getString("name"));
                basicService.setPricePerKm(rs.getDouble("price_per_km"));
                basicService.setType(ServiceType.BASIC);
                return basicService;
            } catch (SQLException e) {
                throw new RuntimeException("Failed to parse BasicService", e);
            }
        };
    }
}