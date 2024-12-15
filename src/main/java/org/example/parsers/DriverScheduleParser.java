package org.example.parsers;

import org.example.exceptions.DatabaseException;
import org.example.models.Company;
import org.example.models.Driver;
import org.example.models.DriverSchedule;
import org.example.repositories.IRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.function.Function;

public class DriverScheduleParser {
    public static Function<ResultSet, DriverSchedule> createParser(
            IRepository<Driver> driverRepository,
            IRepository<Company> companyRepository) {

        return rs -> {
            try {
                DriverSchedule ds = new DriverSchedule();
                ds.setId(rs.getInt("id"));

                int driverId = rs.getInt("driver_id");
                int companyId = rs.getInt("company_id");

                ds.setCheckIn(rs.getTimestamp("check_in").toLocalDateTime());
                Timestamp checkOut = rs.getTimestamp("check_out");
                if (checkOut != null) {
                    ds.setCheckOut(checkOut.toLocalDateTime());
                }

                ds.setDriver(driverRepository.read(driverId));
                ds.setCompany(companyRepository.read(companyId));
                return ds;
            } catch (SQLException e) {
                throw new DatabaseException("Failed to parse DriverSchedule", e);
            }
        };
    }
}
