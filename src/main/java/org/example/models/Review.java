package org.example.models;

public class Review implements HasId {

    private Integer id;
    private int rating;
    private String description;
    private Company company;
    private Driver driver;
    private Client client;

    public Review(Integer id, int rating, String description, Company company, Driver driver, Client client) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.company = company;
        this.driver = driver;
        this.client = client;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ";" + rating + ";" + description + ";" + company + ";" + driver + ";" + client;
    }

    public static Review parse(String line) {
        String[] fields = line.split(";");
        if (fields.length != 18) {
            throw new IllegalArgumentException("CSV line does not have the expected number of fields. Found: " + fields.length);
        }

        try {
            // Parse the review
            Integer reviewId = Integer.parseInt(fields[0]); // Review ID
            int rating = Integer.parseInt(fields[1]);      // Rating
            String description = fields[2];                 // Description

            // Parse the Company
            Company company = new Company(
                    Integer.parseInt(fields[3]),  // ID
                    fields[4],                    // Name
                    fields[5],                    // Email
                    fields[6],                    // Address
                    fields[7]);                   // Phone

            // Parse the Driver
            Driver driver = new Driver(
                    Integer.parseInt(fields[8]),  // ID
                    fields[9],                    // Name
                    fields[10],                    // Email
                    fields[11],                    // Phone
                    fields[12]                     // Address
            );

            // Parse the Client
            Client client = new Client(
                    Integer.parseInt(fields[13]),  // ID
                    fields[14],                    // Name
                    fields[15],                    // Email
                    fields[16],                    // Phone
                    fields[17]                     // Address
            );

            // Return the Review object
            return new Review(reviewId, rating, description, company, driver, client);
        } catch (Exception e) {
            System.err.println("Error parsing line: " + line);
            e.printStackTrace();
            throw new RuntimeException("Error parsing entity from CSV. Ensure the model has a valid static parse(String csv) method.", e);
        }
    }
}
