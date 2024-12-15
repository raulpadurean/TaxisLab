package org.example.services;

import org.example.models.Client;
import org.example.models.Company;
import org.example.models.Driver;
import org.example.models.Review;
import org.example.repositories.IRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for managing Review entities.
 * Provides functionality to add, retrieve, update, delete reviews, and find the best-rated driver in a company.
 */
public class ReviewService {

    private final IRepository<Review> reviewRepository;
    private final IRepository<Driver> driverRepository;
    private final IRepository<Company> companyRepository;
    private final IRepository<Client> clientRepository;

    /**
     * Constructs a ReviewService with the specified repositories for managing Review, Driver, Company, and Client objects.
     *
     * @param reviewRepository  The repository for managing Review objects.
     * @param driverRepository  The repository for managing Driver objects.
     * @param companyRepository The repository for managing Company objects.
     * @param clientRepository  The repository for managing Client objects.
     */
    public ReviewService(IRepository<Review> reviewRepository,
                         IRepository<Driver> driverRepository,
                         IRepository<Company> companyRepository,
                         IRepository<Client> clientRepository) {
        this.reviewRepository = reviewRepository;
        this.driverRepository = driverRepository;
        this.companyRepository = companyRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Adds a new review with the provided details and associates it with the given company, driver, and client.
     * Validates the existence of the company, driver, and client before creating the review.
     *
     * @param id          The ID of the review.
     * @param companyId   The ID of the associated company.
     * @param driverId    The ID of the associated driver.
     * @param clientId    The ID of the associated client.
     * @param rating      The rating for the review (typically an integer).
     * @param description A description of the review.
     * @throws IllegalArgumentException If any of the associated entities (Company, Driver, Client) are not found.
     */
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

    /**
     * Retrieves a review by its ID.
     *
     * @param id The ID of the review to retrieve.
     * @return The Review object with the specified ID, or null if not found.
     */
    public Review getReview(int id) {
        return reviewRepository.read(id);
    }

    /**
     * Retrieves all reviews in the repository.
     *
     * @return A list of all Review objects in the repository.
     */
    public List<Review> getAllReviews() {
        return reviewRepository.readAll();
    }

    /**
     * Updates an existing review with the provided details.
     * Validates that the review and its associated entities (Company, Driver, Client) exist before updating.
     *
     * @param id          The ID of the review to update.
     * @param companyId   The updated ID of the associated company.
     * @param driverId    The updated ID of the associated driver.
     * @param clientId    The updated ID of the associated client.
     * @param rating      The updated rating for the review.
     * @param description The updated description of the review.
     * @throws IllegalArgumentException If the review with the given ID or any related entity is not found.
     */
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

    /**
     * Deletes a review by its ID.
     *
     * @param reviewId The ID of the review to delete.
     */
    public void deleteReview(Integer reviewId) {
        reviewRepository.delete(reviewId);
    }

    /**
     * Finds the best-rated driver in a specific company by calculating the average rating of each driver.
     * The driver with the highest average rating is returned along with their average rating.
     *
     * @param companyId The ID of the company to find the best-rated driver in.
     * @param drivers   A list of all drivers.
     * @param reviews   A list of all reviews.
     * @return A Map.Entry containing the best-rated driver and their average rating.
     *         Returns null if no driver is found for the specified company.
     */
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

    /**
     * Finds the clients favourite driver by calculating the average rating of each driver.
     * The driver with the highest average rating is returned along with their average rating.
     *
     * @param clientId The ID of the client to find his favourite driver.
     * @param drivers   A list of all drivers.
     * @param reviews   A list of all reviews.
     * @return A Map.Entry containing the best-rated driver and their average rating.
     *         Returns null if no driver is found for the specified client.
     */

    public static Map.Entry<Driver, Double> findFavouriteDriverByClient(int clientId, List<Driver> drivers, List<Review> reviews) {
        Map<Driver, List<Integer>> driverRatings = new HashMap<>();

        // Gather ratings for drivers by the specified client
        for (Review review : reviews) {
            if (review.getClient().getId() == clientId) {
                // Add the rating to the driver's list
                driverRatings.computeIfAbsent(review.getDriver(), k -> new ArrayList<>()).add(review.getRating());
            }
        }

        // Calculate the average rating for each driver
        Driver favouriteDriver = null;
        double bestAverage = -1;

        for (Map.Entry<Driver, List<Integer>> entry : driverRatings.entrySet()) {
            Driver driver = entry.getKey();
            List<Integer> ratings = entry.getValue();

            double average = ratings.stream().mapToInt(Integer::intValue).average().orElse(0);

            if (average > bestAverage) {
                favouriteDriver = driver;
                bestAverage = average;
            }
        }

        return favouriteDriver != null ? Map.entry(favouriteDriver, bestAverage) : null;
    }

}
