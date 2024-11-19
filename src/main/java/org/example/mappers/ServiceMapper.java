package org.example.mappers;

import org.example.models.BasicService;
import org.example.models.CustomService;
import org.example.models.Service;

public class ServiceMapper implements EntityMapper<Service> {

    @Override
    public String toCSV(Service service) {
        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null.");
        }

        String baseCSV = String.join(",",
                String.valueOf(service.getId()),                // ID
                service.getName(),                              // Name
                String.valueOf(service.getPricePerKm()),        // Price/km
                service.getServiceType()                       // Service type
        );

        // Add subclass-specific fields
        if (service instanceof CustomService) {
            CustomService customService = (CustomService) service;
            return baseCSV + "," + customService.getExtras();  // Add extras for CustomService
        }

        // No additional fields for BasicService
        return baseCSV;
    }

    @Override
    public Service fromCSV(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            throw new IllegalArgumentException("CSV string cannot be null or empty.");
        }

        // Split the CSV string into parts
        String[] parts = csv.split(",", 5); // Expecting up to 5 fields
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid CSV format. Expected at least 4 fields.");
        }

        try {
            int id = Integer.parseInt(parts[0].trim());           // ID
            String name = parts[1].trim();                       // Name
            double pricePerKm = Double.parseDouble(parts[2].trim()); // Price/km
            String serviceType = parts[3].trim();                // Service type

            // Create the appropriate Service subclass
            if ("Custom Service".equalsIgnoreCase(serviceType)) {
                if (parts.length != 5) {
                    throw new IllegalArgumentException("Invalid CSV format for CustomService. Missing extras field.");
                }
                String extras = parts[4].trim();                 // Extras for CustomService
                return new CustomService(id, name, pricePerKm, extras);
            } else if ("Basic Service".equalsIgnoreCase(serviceType)) {
                return new BasicService(id, name, pricePerKm);
            } else {
                throw new IllegalArgumentException("Unsupported service type: " + serviceType);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing numeric fields: " + e.getMessage(), e);
        }
    }
}
