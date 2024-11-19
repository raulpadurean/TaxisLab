package org.example.services;

import org.example.models.Review;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * Service class for managing {@link Review} entities.
 * Provides business logic for interacting with the underlying repository to perform CRUD operations on review data.
 *
 * <p>This class handles operations such as adding, retrieving, updating, and deleting review records.</p>
 */
public class ReviewService {
    private final IRepository<Review> reviewRepository;

    /**
     * Constructs a {@link ReviewService} with the specified repository.
     *
     * @param reviewRepository The repository to be used for storing and retrieving review entities.
     */
    public ReviewService(IRepository<Review> reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Adds a new review to the repository.
     *
     * <p>This method persists the provided {@link Review} entity to the repository.</p>
     *
     * @param review The review to be added. Must not be {@code null}.
     */
    public void addReview(Review review) {
        reviewRepository.create(review);
    }

    /**
     * Retrieves a review by its unique ID.
     *
     * @param id The unique identifier of the review to retrieve.
     * @return The review with the specified ID, or {@code null} if no such review exists.
     */
    public Review getReview(int id) {
        return reviewRepository.read(id);
    }

    /**
     * Retrieves all reviews from the repository.
     *
     * @return A list of all reviews in the repository.
     */
    public List<Review> getAllReviews() {
        return reviewRepository.getAll();
    }

    /**
     * Updates an existing review in the repository.
     *
     * <p>If the review already exists in the repository, it will be updated with the new values.</p>
     *
     * @param review The review to update. The review must not be {@code null}.
     */
    public void updateReview(Review review) {
        reviewRepository.update(review);
    }

    /**
     * Deletes a review from the repository.
     *
     * @param reviewId The unique ID of the review to be deleted.
     */
    public void deleteReview(Integer reviewId) {
        reviewRepository.delete(reviewId);
    }
}
