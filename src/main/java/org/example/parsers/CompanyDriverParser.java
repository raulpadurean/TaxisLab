package org.example.parsers;

import org.example.exceptions.DatabaseException;
import org.example.models.Company;
import org.example.models.CompanyDriver;
import org.example.models.Driver;
import org.example.repositories.IRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class CompanyDriverParser {
    public static Function<ResultSet, CompanyDriver> createParser(
            IRepository<Driver> driverRepository,
            IRepository<Company> companyRepository) {

        return rs -> {
            try {
                CompanyDriver cd = new CompanyDriver();
                cd.setId(rs.getInt("id"));

                int driverId = rs.getInt("driver_id");
                if (!rs.wasNull()) {
                    cd.setDriver(driverRepository.read(driverId));
                }

                int companyId = rs.getInt("company_id");
                if (!rs.wasNull()) {
                    cd.setCompany(companyRepository.read(companyId));
                }

                return cd;
            } catch (SQLException e) {
                throw new DatabaseException("Failed to parse CompanyDriver", e);
            }
        };
    }
}
