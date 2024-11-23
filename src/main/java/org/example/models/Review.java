package org.example.models;

public class Review implements HasId {

    private Integer id;
    private int rating;
    private String description;
    private Company company;
    private Driver driver;
    private Client client;

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

    public Review(Integer id, int rating, String description, Company company, Driver driver, Client client) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.company = company;
        this.driver = driver;
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
        return id + "," + rating + "," + description + "," + company + "," + driver + "," + client;
    }

    public static Review parse(String line) {
        String[] fields = line.split(",");
        return new Review(
                Integer.parseInt(fields[0]),
                Integer.parseInt(fields[1]),
                fields[2],
                Company.parse(fields[3]),
                Driver.parse(fields[4]),
                Client.parse(fields[5])
        );
    }
}
