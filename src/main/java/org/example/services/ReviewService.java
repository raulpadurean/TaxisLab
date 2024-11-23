package org.example.services;

import org.example.models.Review;
import org.example.models.Driver;
import org.example.models.Company;
import org.example.models.Client;
import org.example.repositories.IRepository;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class ReviewService {
    private final IRepository<Review> reviewRepository;
    private final IRepository<Driver> driverRepository;
    private final IRepository<Company> companyRepository;
    private final IRepository<Client> clientRepository;

    public ReviewService(IRepository<Review> reviewRepository,
                         IRepository<Driver> driverRepository,
                         IRepository<Company> companyRepository,
                         IRepository<Client> clientRepository) {
        this.reviewRepository = reviewRepository;
        this.driverRepository = driverRepository;
        this.companyRepository = companyRepository;
        this.clientRepository = clientRepository;
    }

    public void addReview(Integer id, int companyId, int driverId, int clientId, int rating, String description) {
        // Fetch associated entities
        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver with ID " + driverId + " does not exist.");
        }

        Client client = clientRepository.read(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Client with ID " + clientId + " does not exist.");
        }

        // Create and save the review
        Review review = new Review(id, rating, description, company, driver, client);
        reviewRepository.create(review);
    }

    public Review getReview(int id) {
        return reviewRepository.read(id);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.readAll();
    }

    public void updateReview(Integer id, int companyId, int driverId, int clientId, int rating, String description) {
        // Fetch associated entities
        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver with ID " + driverId + " does not exist.");
        }

        Client client = clientRepository.read(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Client with ID " + clientId + " does not exist.");
        }

        // Create and update the review
        Review review = new Review(id, rating, description, company, driver, client);
        reviewRepository.update(review);
    }

    public void deleteReview(Integer reviewId) {
        reviewRepository.delete(reviewId);
    }

    public static Map.Entry<Driver, Double> findBestRatedDriverInCompany(int companyId, List<Driver> drivers, List<Review> reviews) {
        Map<Driver, List<Integer>> driverRatings = new HashMap<>();

        // Get ratings for drivers in the specified company
        for (Review review : reviews) {
            if (review.getCompany().getId() == companyId) {
                // Add the rating to the driver's list
                driverRatings.computeIfAbsent(review.getDriver(), k -> new ArrayList<>()).add(review.getRating());
            }
        }

        // Calculate the average rating for each driver
        Driver bestDriver = null;
        double bestAverage = -1;

        for (Map.Entry<Driver, List<Integer>> entry : driverRatings.entrySet()) {
            Driver driver = entry.getKey();
            List<Integer> ratings = entry.getValue();

            double average = ratings.stream().mapToInt(Integer::intValue).average().orElse(0);

            if (average > bestAverage) {
                bestDriver = driver;
                bestAverage = average;
            }
        }

        return bestDriver != null ? Map.entry(bestDriver, bestAverage) : null;
    }
}
