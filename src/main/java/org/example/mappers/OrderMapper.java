package org.example.mappers;

import org.example.models.Order;


public class OrderMapper implements EntityMapper<Order> {
    @Override
    public String toCSV(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        // Format Order fields into CSV
        return String.join(",",
                String.valueOf(order.getId()),
                order.getService() != null ? order.getService().toString() : "",
                String.valueOf(order.getTotalKm()),
                order.getClient(),
                order.getDriver(),
                order.getCompany(),
                order.getDatetime()
        );
    }

    @Override
    public Order fromCSV(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            throw new IllegalArgumentException("CSV string cannot be null or empty.");
        }

        // Split the CSV string into parts
        String[] parts = csv.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid CSV format. Expected 5 fields.");
        }

        try {
            // Create a new Order object from the CSV parts
            return new Order(
                    Integer.parseInt(parts[0].trim()),
                    parts[1].trim(),
                    parts[2].trim(),
                    parts[3].trim(),
                    parts[4].trim(),
                    parts[5].trim(),
                    parts[6].trim()
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing Order ID: " + e.getMessage(), e);
        }
    }
}

