package org.example.models;

public class Review {

    private int companyId;
    private int driverId;
    private int clientId;
    private int rating;
    private String description;

    public Review(int companyId, int driverId, int clientId, int rating, String description) {
        this.companyId = companyId;
        this.driverId = driverId;
        this.clientId = clientId;
        this.rating = rating;
        this.description = description;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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
    public String toString() {
        return "Review{" +
                "companyId=" + companyId +
                ", driverId=" + driverId +
                ", clientId=" + clientId +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}