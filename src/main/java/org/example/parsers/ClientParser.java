package org.example.parsers;

import org.example.exceptions.DatabaseException;
import org.example.models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class ClientParser {
    public static Function<ResultSet, Client> createParser() {
        return rs -> {
            try {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setEmail(rs.getString("email"));
                client.setAddress(rs.getString("address"));
                client.setPhone(rs.getString("phone"));
                return client;
            } catch (SQLException e) {
                throw new DatabaseException("Failed to parse Client", e);
            }
        };
    }
}
