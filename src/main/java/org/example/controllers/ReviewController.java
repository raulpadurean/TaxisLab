package org.example.controllers;

import org.example.models.Review;
import org.example.models.Driver;
import org.example.models.Company;
import org.example.models.Client;
import org.example.services.ReviewService;

import java.util.List;
import java.util.Map;

/**
 * Controller class responsible for managing {@link Review} objects.
 * This class provides methods to add, retrieve, update, delete reviews,
 * and to find the best rated driver in a company, delegating the
 * actual business logic to the {@link ReviewService} class.
 */
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * Constructs a {@link ReviewController} with the specified {@link ReviewService}.
     * This service is responsible for the business logic related to review operations.
     *
     * @param reviewService The {@link ReviewService} used to delegate business logic.
     */
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Adds a new {@link Review} with the provided details.
     * Delegates the actual operation to the {@link ReviewService}.
     *
     * @param id The unique identifier for the review.
     * @param companyId The ID of the company being reviewed.
     * @param driverId The ID of the driver being reviewed.
     * @param clientId The ID of the client providing the review.
     * @param rating The rating provided in the review (e.g., 1 to 5).
     * @param description The description of the review.
     */
    public void addReview(Integer id, int companyId, int driverId, int clientId, int rating, String description) {
        reviewService.addReview(id, companyId, driverId, clientId, rating, description);
    }

    /**
     * Retrieves a {@link Review} by its unique identifier.
     * Delegates the actual retrieval to the {@link ReviewService}.
     *
     * @param id The unique identifier of the review.
     * @return The {@link Review} object with the specified ID, or {@code null} if not found.
     */
    public Review getReview(int id) {
        return reviewService.getReview(id);
    }

    /**
     * Retrieves all {@link Review} objects.
     * Delegates the actual retrieval to the {@link ReviewService}.
     *
     * @return A list of all {@link Review} objects.
     */
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    /**
     * Updates an existing {@link Review} with the provided details.
     * Delegates the actual update to the {@link ReviewService}.
     *
     * @param id The unique identifier of the review to update.
     * @param companyId The updated company ID.
     * @param driverId The updated driver ID.
     * @param clientId The updated client ID.
     * @param rating The updated rating for the review.
     * @param description The updated description of the review.
     */
    public void updateReview(Integer id, int companyId, int driverId, int clientId, int rating, String description) {
        reviewService.updateReview(id, companyId, driverId, clientId, rating, description);
    }

    /**
     * Deletes a {@link Review} by its unique identifier.
     * Delegates the actual deletion to the {@link ReviewService}.
     *
     * @param reviewId The unique identifier of the review to delete.
     */
    public void deleteReview(Integer reviewId) {
        reviewService.deleteReview(reviewId);
    }

    /**
     * Finds the best-rated driver in a specific company by evaluating the reviews.
     * Delegates the actual operation to the {@link ReviewService}.
     *
     * @param companyId The ID of the company whose drivers are being evaluated.
     * @param drivers A list of {@link Driver} objects to be evaluated.
     * @param reviews A list of {@link Review} objects to be considered for rating.
     * @return A {@link Map.Entry} containing the best-rated {@link Driver} and their rating.
     */
    public static Map.Entry<Driver, Double> findBestRatedDriverInCompany(int companyId, List<Driver> drivers, List<Review> reviews) {
        return ReviewService.findBestRatedDriverInCompany(companyId, drivers, reviews);
    }
}
