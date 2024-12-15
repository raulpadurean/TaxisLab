package org.example.parsers;

import org.example.exceptions.DatabaseException;
import org.example.models.BasicService;
import org.example.models.CustomService;
import org.example.models.Service;
import org.example.models.ServiceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class ServiceParser {
    public static Function<ResultSet, Service> createParser(Connection connection) {
        return rs -> {
            try {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double pricePerKm = rs.getDouble("price_per_km");
                String typeStr = rs.getString("type");
                ServiceType type = ServiceType.valueOf(typeStr.toUpperCase());

                switch (type) {
                    case BASIC:
                        BasicService bs = new BasicService();
                        bs.setId(id);
                        bs.setName(name);
                        bs.setPricePerKm(pricePerKm);
                        bs.setType(ServiceType.BASIC);
                        return bs;
                    case CUSTOM:
                        CustomService cs = new CustomService();
                        cs.setId(id);
                        cs.setName(name);
                        cs.setPricePerKm(pricePerKm);
                        cs.setType(ServiceType.CUSTOM);
                        cs.setExtras(fetchExtrasForCustomService(connection, id));
                        return cs;
                    default:
                        throw new DatabaseException("Unknown service type: " + typeStr, new Exception("Serive error"));
                }
            } catch (SQLException e) {
                throw new DatabaseException("Failed to parse Service", e);
            }
        };
    }

    private static String fetchExtrasForCustomService(Connection conn, int serviceId) {
        String sql = "SELECT extras FROM custom_service WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, serviceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("extras");
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch extras for CustomService with id " + serviceId, e);
        }
    }
}
