package org.example.mappers;

import org.example.models.Driver;

public class DriverMapper implements EntityMapper<Driver> {

    @Override
    public String toCSV(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null.");
        }

        // Convert the `Driver` object into a CSV string
        return String.join(",",
                String.valueOf(driver.getId()),              // ID
                driver.getName() != null ? driver.getName() : "",    // Name
                driver.getEmail() != null ? driver.getEmail() : "",  // Email
                driver.getAddress() != null ? driver.getAddress() : "", // Address
                driver.getPhone() != null ? driver.getPhone() : ""   // Phone
        );
    }

    @Override
    public Driver fromCSV(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            throw new IllegalArgumentException("CSV string cannot be null or empty.");
        }

        // Split the CSV string into parts
        String[] parts = csv.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid CSV format. Expected 5 fields.");
        }

        try {
            int id = Integer.parseInt(parts[0].trim());           // ID
            String name = parts[1].trim();                       // Name
            String email = parts[2].trim();                      // Email
            String address = parts[3].trim();                    // Address
            String phone = parts[4].trim();                      // Phone

            // Create and return the Driver object
            return new Driver(id, name, email, address, phone);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing numeric fields: " + e.getMessage(), e);
        }
    }
}
