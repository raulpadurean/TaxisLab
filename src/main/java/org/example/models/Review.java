package org.example.models;

/**
 * Represents a review provided by a client for a driver or company.
 *
 * <p>The {@code Review} class includes details about the company, driver, client,
 * the rating given, and a description of the review. This class implements {@link HasId},
 * ensuring each review has a unique identifier accessible via {@code getId()}.</p>
 */
public class Review implements HasId {

    private Integer id;
    private int companyId;
    private int driverId;
    private int clientId;
    private int rating;
    private String description;

    /**
     * Constructs a {@code Review} instance with the specified details.
     *
     * @param id          The unique identifier for the review.
     * @param companyId   The ID of the company associated with the review.
     * @param driverId    The ID of the driver being reviewed.
     * @param clientId    The ID of the client providing the review.
     * @param rating      The rating provided (e.g., 1-5 stars).
     * @param description A text description of the review.
     */
    public Review(Integer id, int companyId, int driverId, int clientId, int rating, String description) {
        this.id = id;
        this.companyId = companyId;
        this.driverId = driverId;
        this.clientId = clientId;
        this.rating = rating;
        this.description = description;
    }

    /**
     * Sets the unique identifier for this review.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of this review.
     *
     * @return The unique identifier.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Gets the ID of the company associated with this review.
     *
     * @return The company ID.
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * Sets the ID of the company associated with this review.
     *
     * @param companyId The company ID to set.
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * Gets the ID of the driver being reviewed.
     *
     * @return The driver ID.
     */
    public int getDriverId() {
        return driverId;
    }

    /**
     * Sets the ID of the driver being reviewed.
     *
     * @param driverId The driver ID to set.
     */
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    /**
     * Gets the ID of the client providing the review.
     *
     * @return The client ID.
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets the ID of the client providing the review.
     *
     * @param clientId The client ID to set.
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the rating provided in the review.
     *
     * @return The rating (e.g., 1-5 stars).
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating for this review.
     *
     * @param rating The rating to set.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets the text description of the review.
     *
     * @return The review description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the text description of the review.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the {@code Review}.
     *
     * @return A string describing the review.
     */
    @Override
    public String toString() {
        return "Review{" +
                "Id=" + id +
                ", companyId=" + companyId +
                ", driverId=" + driverId +
                ", clientId=" + clientId +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}
