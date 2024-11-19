package org.example.mappers;

import org.example.models.Company;


public class CompanyMapper implements EntityMapper<Company> {
    @Override
    public String toCSV(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null.");
        }

        // Format Company fields into CSV
        return String.join(",",
                String.valueOf(company.getId()),
                company.getName(),
                company.getEmail(),
                company.getAddress(),
                company.getPhone()
        );
    }

    @Override
    public Company fromCSV(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            throw new IllegalArgumentException("CSV string cannot be null or empty.");
        }

        // Split the CSV string into parts
        String[] parts = csv.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid CSV format. Expected 5 fields.");
        }

        try {
            // Create a new Company object from the CSV parts
            return new Company(
                    Integer.parseInt(parts[0].trim()),  // ID
                    parts[1].trim(),                   // Name
                    parts[2].trim(),                   // Email
                    parts[3].trim(),                   // Address
                    parts[4].trim()                    // Phone
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing Company ID: " + e.getMessage(), e);
        }
    }
}

