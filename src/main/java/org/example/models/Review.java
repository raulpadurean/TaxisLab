package org.example.models;

/**
 * Represents a review given by a client for a service that involves a driver and a company.
 * The review includes a rating, a description, and references to the company, driver, and client associated with it.
 * Implements the {@link HasId} interface to ensure each review has a unique identifier.
 */
public class Review implements HasId {

    private Integer id;
    private int rating;
    private String description;
    private Company company;
    private Driver driver;
    private Client client;

    /**
     * Constructs a Review object with the specified details.
     *
     * @param id          The unique identifier for the review.
     * @param rating     The rating provided by the client (typically a number from 1 to 5).
     * @param description A description of the review provided by the client.
     * @param company    The company associated with the review.
     * @param driver     The driver associated with the review.
     * @param client     The client who provided the review.
     */
    public Review(Integer id, int rating, String description, Company company, Driver driver, Client client) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.company = company;
        this.driver = driver;
        this.client = client;
    }

    /**
     * Retrieves the company associated with the review.
     *
     * @return The company for the review.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets the company associated with the review.
     *
     * @param company The company to set for the review.
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Retrieves the driver associated with the review.
     *
     * @return The driver for the review.
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Sets the driver associated with the review.
     *
     * @param driver The driver to set for the review.
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the client who provided the review.
     *
     * @return The client who gave the review.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the client who provided the review.
     *
     * @param client The client to set for the review.
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Sets the unique identifier for the review.
     *
     * @param id The ID to set for the review.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the rating given by the client in the review.
     *
     * @return The rating of the review.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating for the review.
     *
     * @param rating The rating to set for the review.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Retrieves the description of the review.
     *
     * @return The description of the review.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the review.
     *
     * @param description The description to set for the review.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the unique identifier for the review.
     *
     * @return The ID of the review.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Returns a string representation of the Review object in the format:
     * "id;rating;description;company;driver;client".
     *
     * @return A string representation of the Review instance.
     */
    @Override
    public String toString() {
        return id + ";" + rating + ";" + description + ";" + company + ";" + driver + ";" + client;
    }

    /**
     * Parses a string representation of a Review object and returns a new Review instance.
     * The expected string format is:
     * "id;rating;description;company;driver;client".
     *
     * @param line The string representation of the Review object.
     * @return A new Review object created from the string data.
     * @throws IllegalArgumentException If the CSV line does not contain the expected number of fields.
     * @throws RuntimeException If there is an error parsing the fields into their respective objects.
     */
    public static Review parse(String line) {
        String[] fields = line.split(";");

        // Ensure the line has the correct number of fields
        if (fields.length != 18) {
            throw new IllegalArgumentException("CSV line does not have the expected number of fields. Found: " + fields.length);
        }

        try {
            // Parse the review details
            Integer reviewId = Integer.parseInt(fields[0]); // Review ID
            int rating = Integer.parseInt(fields[1]);      // Rating
            String description = fields[2];                 // Description

            // Parse the Company details
            Company company = new Company(
                    Integer.parseInt(fields[3]),  // ID
                    fields[4],                    // Name
                    fields[5],                    // Email
                    fields[6],                    // Address
                    fields[7]);                   // Phone

            // Parse the Driver details
            Driver driver = new Driver(
                    Integer.parseInt(fields[8]),  // ID
                    fields[9],                    // Name
                    fields[10],                    // Email
                    fields[11],                    // Phone
                    fields[12]                     // Address
            );

            // Parse the Client details
            Client client = new Client(
                    Integer.parseInt(fields[13]),  // ID
                    fields[14],                    // Name
                    fields[15],                    // Email
                    fields[16],                    // Phone
                    fields[17]                     // Address
            );

            // Return the new Review object
            return new Review(reviewId, rating, description, company, driver, client);

        } catch (Exception e) {
            System.err.println("Error parsing line: " + line);
            e.printStackTrace();
            throw new RuntimeException("Error parsing entity from CSV. Ensure the model has a valid static parse(String csv) method.", e);
        }
    }
}
