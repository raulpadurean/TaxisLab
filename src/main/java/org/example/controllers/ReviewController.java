package org.example.controllers;

import org.example.models.Review;
import org.example.models.Driver;
import org.example.models.Company;
import org.example.services.ReviewService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public void addReview(Integer id,int companyId, int driverId, int clientId, int rating, String description) {
        Review review = new Review(id,companyId, driverId, clientId, rating, description); // ID is set by repository
        reviewService.addReview(review);
    }

    public Review getReview(int id) {
        return reviewService.getReview(id);
    }

    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    public void updateReview(Review review) {
        reviewService.updateReview(review);
    }

    public void deleteReview(Integer reviewId) {
        reviewService.deleteReview(reviewId);

    }


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
