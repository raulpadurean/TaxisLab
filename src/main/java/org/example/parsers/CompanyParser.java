package org.example.parsers;

import org.example.exceptions.DatabaseException;
import org.example.models.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class CompanyParser {
    public static Function<ResultSet, Company> createParser() {
        return rs -> {
            try {
                Company company = new Company();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setEmail(rs.getString("email"));
                company.setAddress(rs.getString("address"));
                company.setPhone(rs.getString("phone"));
                return company;
            } catch (SQLException e) {
                throw new DatabaseException("Failed to parse Company", e);
            }
        };
    }
}
