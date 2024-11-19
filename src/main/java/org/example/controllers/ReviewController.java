package org.example.controllers;

import org.example.models.Review;
import org.example.models.Driver;
import org.example.models.Company;
import org.example.services.ReviewService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for managing reviews.
 * This class provides methods to handle review-related operations such as adding, retrieving, updating,
 * deleting reviews, and calculating the best-rated driver within a company.
 */
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * Constructs a new {@code ReviewController} with the specified review service.
     *
     * @param reviewService the service instance for handling review-related operations
     */
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Adds a new review with the specified details.
     *
     * @param id         the unique identifier of the review (ID is set by repository)
     * @param companyId  the ID of the company being reviewed
     * @param driverId   the ID of the driver being reviewed
     * @param clientId   the ID of the client who gave the review
     * @param rating     the rating given in the review (1 to 5 scale)
     * @param description a description or feedback provided by the client
     */
    public void addReview(Integer id, int companyId, int driverId, int clientId, int rating, String description) {
        Review review = new Review(id, companyId, driverId, clientId, rating, description);
        reviewService.addReview(review);
    }

    /**
     * Retrieves a review by its ID.
     *
     * @param id the unique identifier of the review
     * @return the {@link Review} object with the specified ID, or {@code null} if not found
     */
    public Review getReview(int id) {
        return reviewService.getReview(id);
    }

    /**
     * Retrieves all reviews.
     *
     * @return a list of all {@link Review} objects
     */
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    /**
     * Updates an existing review with the specified details.
     *
     * @param id          the unique identifier of the review to be updated
     * @param companyId   the updated ID of the company being reviewed
     * @param driverId    the updated ID of the driver being reviewed
     * @param clientId    the updated ID of the client who gave the review
     * @param rating      the updated rating given in the review (1 to 5 scale)
     * @param description the updated description or feedback provided by the client
     */
    public void updateReview(Integer id, int companyId, int driverId, int clientId, int rating, String description) {
        Review review = new Review(id, companyId, driverId, clientId, rating, description);
        reviewService.updateReview(review);
    }

    /**
     * Deletes a review by its ID.
     *
     * @param reviewId the unique identifier of the review to be deleted
     */
    public void deleteReview(Integer reviewId) {
        reviewService.deleteReview(reviewId);
    }

    /**
     * Finds the best-rated driver in a specific company based on reviews.
     * The best-rated driver is determined by the average rating across all their reviews in the specified company.
     *
     * @param companyId the ID of the company whose drivers are to be evaluated
     * @param drivers   a list of all drivers in the system
     * @param reviews   a list of all reviews associated with drivers
     * @return a map entry containing the best-rated driver and their average rating, or {@code null} if no driver found
     */
    public static Map.Entry<Driver, Double> findBestRatedDriverInCompany(int companyId, List<Driver> drivers, List<Review> reviews) {
        Map<Driver, List<Integer>> driverRatings = new HashMap<>();

        // Get ratings for drivers in the specified company
        for (Review review : reviews) {
            if (review.getCompanyId() == companyId) {
                // Find the driver associated with the review
                for (Driver driver : drivers) {
                    if (driver.getId() == review.getDriverId()) {
                        driverRatings.computeIfAbsent(driver, k -> new ArrayList<>()).add(review.getRating());
                        break;
                    }
                }
            }
        }

        // Calculate the average rating for each driver
        Driver bestDriver = null;
        double bestAverage = -1;

        for (Map.Entry<Driver, List<Integer>> entry : driverRatings.entrySet()) {
            Driver driver = entry.getKey();
            List<Integer> ratings = entry.getValue();

            // Calculate average rating
            double average = ratings.stream().mapToInt(Integer::intValue).average().orElse(0);

            // Update best driver if this average is higher
            if (average > bestAverage) {
                bestDriver = driver;
                bestAverage = average;
            }
        }

        // Return the best driver and their rating
        if (bestDriver != null) {
            return Map.entry(bestDriver, bestAverage);
        } else {
            return null;
        }
    }
}
