package org.example.mappers;

import org.example.models.Company;

public class CompanyMapper implements EntityMapper<Company> {
    @Override
    public String toCSV(Company company) {
        return company.toString();
    }

    @Override
    public Company fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Company(
                Integer.parseInt(parts[0]),
                parts[1],
                Integer.parseInt(parts[2])
        );
    }
}
