package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private final String host;
    private final String port;
    private final String database;
    private final String username;
    private final String password;
    public Connection connection;

    private static MySQLConnection connectionInstance;
    private  MySQLConnection(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public static MySQLConnection getConnection(String host, String port, String database, String username, String password) {
        if (connectionInstance == null) {
            connectionInstance = new MySQLConnection(host, port, database, username, password);
        }
        return connectionInstance;
    }


    public void connect() {
        connection = null;
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }

    }

    public void disConnect() {
        // Don't forget to close the connection when done
        if (connection != null) {
            try {
                System.out.println("Closing database connection...");
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
