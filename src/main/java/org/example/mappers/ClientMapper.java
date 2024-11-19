package org.example.mappers;

import org.example.models.Client;



public class ClientMapper implements EntityMapper<Client>{


    /**
     * @param client
     * @return
     */
    @Override
    public String toCSV(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null.");
        }

        // Format Client fields into CSV
        return String.join(",",
                String.valueOf(client.getId()),
                client.getName(),
                client.getEmail(),
                client.getAddress(),
                client.getPhone()
        );
    }

    /**
     * @param csv
     * @return
     */
    @Override
    public Client fromCSV(String csv) {
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
            return new Client(
                    Integer.parseInt(parts[0].trim()),  // ID
                    parts[1].trim(),                   // Name
                    parts[2].trim(),                   // Email
                    parts[3].trim(),                   // Address
                    parts[4].trim()                    // Phone
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing Client ID: " + e.getMessage(), e);
        }
    }
}
