package org.example.parsers;

import org.example.exceptions.DatabaseException;
import org.example.models.Client;
import org.example.models.Company;
import org.example.models.Driver;
import org.example.models.Review;
import org.example.repositories.IRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class ReviewParser {
    public static Function<ResultSet, Review> createParser(
            IRepository<Company> companyRepository,
            IRepository<Driver> driverRepository,
            IRepository<Client> clientRepository) {

        return rs -> {
            try {
                Review r = new Review();
                r.setId(rs.getInt("id"));
                r.setRating(rs.getInt("rating"));
                r.setDescription(rs.getString("description"));

                int companyId = rs.getInt("company_id");
                int driverId = rs.getInt("driver_id");
                int clientId = rs.getInt("client_id");

                r.setCompany(companyRepository.read(companyId));
                r.setDriver(driverRepository.read(driverId));
                r.setClient(clientRepository.read(clientId));

                return r;
            } catch (SQLException e) {
                throw new DatabaseException("Failed to parse Review", e);
            }
        };
    }
}