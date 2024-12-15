package org.example.parsers;

import org.example.exceptions.DatabaseException;
import org.example.models.*;
import org.example.repositories.IRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class OrderParser {
    public static Function<ResultSet, Order> createParser(
            IRepository<Service> serviceRepository,
            IRepository<Client> clientRepository,
            IRepository<Driver> driverRepository,
            IRepository<Company> companyRepository) {

        return rs -> {
            try {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                int serviceId = rs.getInt("service_id");
                o.setTotalKm(rs.getDouble("total_km"));
                int clientId = rs.getInt("client_id");
                int driverId = rs.getInt("driver_id");
                int companyId = rs.getInt("company_id");
                o.setDatetime(rs.getTimestamp("datetime").toLocalDateTime());

                o.setService(serviceRepository.read(serviceId));
                o.setClient(clientRepository.read(clientId));
                o.setDriver(driverRepository.read(driverId));
                o.setCompany(companyRepository.read(companyId));
                return o;
            } catch (SQLException e) {
                throw new DatabaseException("Failed to parse Order", e);
            }
        };
    }
}