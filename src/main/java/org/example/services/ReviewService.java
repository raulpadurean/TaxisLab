package org.example.services;

import org.example.models.Review;
import org.example.repositories.IRepository;

import java.util.List;

public class ReviewService {
    private final IRepository<Review> reviewRepository;

    public ReviewService(IRepository<Review> reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void addReview(Review review) {
        reviewRepository.create(review);
    }

    public Review getReview(int id) {
        return reviewRepository.read(id);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.readAll();
    }

    public void updateReview(Review review) {
        reviewRepository.update(review);
    }

    public void deleteReview(Integer reviewId) {

        reviewRepository.delete(reviewId);
    }
}
