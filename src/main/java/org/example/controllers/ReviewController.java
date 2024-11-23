package org.example.controllers;

import org.example.models.Review;
import org.example.models.Driver;
import org.example.models.Company;
import org.example.models.Client;
import org.example.services.ReviewService;

import java.util.List;
import java.util.Map;

public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public void addReview(Integer id, int companyId, int driverId, int clientId, int rating, String description) {
        reviewService.addReview(id, companyId, driverId, clientId, rating, description);
    }

    public Review getReview(int id) {
        return reviewService.getReview(id);
    }

    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    public void updateReview(Integer id, int companyId, int driverId, int clientId, int rating, String description) {
        reviewService.updateReview(id, companyId, driverId, clientId, rating, description);
    }

    public void deleteReview(Integer reviewId) {
        reviewService.deleteReview(reviewId);
    }

    public static Map.Entry<Driver, Double> findBestRatedDriverInCompany(int companyId, List<Driver> drivers, List<Review> reviews) {
        return ReviewService.findBestRatedDriverInCompany(companyId, drivers, reviews);
    }
}
