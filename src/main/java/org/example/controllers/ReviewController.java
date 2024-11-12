package org.example.controllers;

import org.example.models.Review;
import org.example.services.ReviewService;

import java.util.List;

public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public void addReview(int companyId, int driverId, int clientId, int rating, String description) {
        Review review = new Review(companyId, driverId, clientId, rating, description); // ID is set by repository
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

    public void deleteReview(int id) {
        reviewService.deleteReview(id);
    }
}
