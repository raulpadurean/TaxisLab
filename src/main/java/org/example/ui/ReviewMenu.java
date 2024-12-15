package org.example.ui;

import org.example.controllers.ReviewController;
import org.example.models.Client;
import org.example.models.Company;
import org.example.models.Driver;
import org.example.models.Review;
import org.example.repositories.IRepository;
import org.example.services.ReviewService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReviewMenu {
    /**
     * Launches the menu for managing Reviews.
     * Users can add, view, update, and delete reviews, and find the best-rated driver.
     *
     * Functionalities:
     * - Add a review with details such as review ID, client ID, driver ID,
     *   company ID, rating, and description.
     * - View all existing reviews.
     * - Update a review by providing an ID and new details.
     * - Delete a review by its ID.
     * - Find the best-rated driver for a specific company by analyzing reviews.
     * - Exit the menu.
     */

    public static void show(IRepository<Review> reviewRepo,
                            IRepository<Driver> driverRepo,
                            IRepository<Company> companyRepo,
                            IRepository<Client> clientRepo) {
        ReviewService reviewService = new ReviewService(reviewRepo, driverRepo, companyRepo, clientRepo);
        ReviewController reviewController = new ReviewController(reviewService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Review
                    2. View Reviews
                    3. Update Review
                    4. Delete Review
                    5. Find Best Rated Driver
                    6. Favourite Driver By Client
                    7. Exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter Review details (reviewId, clientId, driverId, companyId, rating, description):");

                        System.out.print("Enter review ID: ");
                        int reviewId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter client ID: ");
                        int clientId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter company ID: ");
                        int companyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter rating: ");
                        int rating = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter description: ");
                        String description = scanner.nextLine();

                        reviewController.addReview(reviewId, companyId, driverId, clientId, rating, description);
                        System.out.println("Review added successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid IDs and rating.");
                    }
                    break;

                case 2:
                    System.out.println("List of Reviews:");
                    reviewController.getAllReviews().forEach(System.out::println);
                    break;

                case 3:
                    try {
                        System.out.println("Enter Review ID to update:");
                        int idToUpdate = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter updated client ID: ");
                        int updatedClientId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter updated driver ID: ");
                        int updatedDriverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter updated company ID: ");
                        int updatedCompanyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter updated rating: ");
                        int updatedRating = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter updated description: ");
                        String updatedDescription = scanner.nextLine();

                        reviewController.updateReview(idToUpdate, updatedCompanyId, updatedDriverId, updatedClientId, updatedRating, updatedDescription);
                        System.out.println("Review updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid IDs and rating.");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter the ID of the Review to delete: ");
                        int idToDelete = Integer.parseInt(scanner.nextLine());
                        reviewController.deleteReview(idToDelete);
                        System.out.println("Review deleted successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid review ID.");
                    }
                    break;

                case 5:
                    try {
                        List<Driver> drivers = new ArrayList<>();
                        List<Review> reviews = new ArrayList<>();

                        // read CSV file and populate drivers and reviews
                        File file = new File("C:\\Users\\pc\\IdeaProjects\\TaxisLab\\data\\reviews.csv");
                        if (!file.exists()) {
                            System.out.println("File not found: " + file.getAbsolutePath());
                            return; // Exit if the file does not exist
                        }

                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String line;
                            br.readLine();
                            System.out.println("Started reading file...");

                            // Read and print each line to verify that the file is being read
                            int counter = 0;
                            while ((line = br.readLine()) != null) {
                                counter++;
                                System.out.println("Processing line " + counter + ": " + line);  // Debugging

                                String[] parts = line.split(";");
                                System.out.println("Parts: " + Arrays.toString(parts));  // Debugging

                                // Check if the line has 18 fields
                                if (parts.length != 18) {
                                    System.out.println("Skipping line due to incorrect number of fields: " + line);
                                    continue;
                                }

                                try {
                                    Integer reviewId = Integer.parseInt(parts[0]);
                                    int rating = Integer.parseInt(parts[1]);
                                    String description = parts[2];

                                    int companyId = Integer.parseInt(parts[3]);
                                    String companyName = parts[4];
                                    String companyEmail = parts[5];
                                    String companyAddress = parts[6];
                                    String companyPhone = parts[7];

                                    int driverId = Integer.parseInt(parts[8]);
                                    String driverName = parts[9];
                                    String driverEmail = parts[10];
                                    String driverAddress = parts[11];
                                    String driverPhone = parts[12];

                                    int clientId = Integer.parseInt(parts[13]);
                                    String clientName = parts[14];
                                    String clientEmail = parts[15];
                                    String clientAddress = parts[16];
                                    String clientPhone = parts[17];

                                    // Create Company and Client objects
                                    Company company = new Company(companyId, companyName, companyEmail, companyAddress, companyPhone);
                                    Client client = new Client(clientId, clientName, clientEmail, clientAddress, clientPhone);

                                    // Check if the company, driver, and client objects are created correctly
                                    System.out.println("Created Company: " + company);
                                    System.out.println("Created Client: " + client);

                                    // Add the driver if not already in the list
                                    Driver driver = new Driver(driverId, driverName, driverPhone, driverEmail, driverAddress);
                                    if (drivers.stream().noneMatch(d -> d.getId() == driverId)) {
                                        drivers.add(driver);
                                        System.out.println("Added Driver: " + driver);
                                    }

                                    // Create and add the review to the list
                                    Review review = new Review(reviewId, rating, description, company, driver, client);
                                    reviews.add(review);
                                    System.out.println("Added Review: " + review);

                                } catch (NumberFormatException e) {
                                    System.out.println("Error parsing line (skipping): " + line + " | Error: " + e.getMessage());
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Error reading the file: " + e.getMessage());
                        }

                        // print the number of drivers and reviews read
                        System.out.println("Number of drivers loaded: " + drivers.size());
                        System.out.println("Number of reviews loaded: " + reviews.size());

                        // find the best driver for company 2 (for example)
                        int companyIdToSearch = 2; // Example company ID to search
                        Map.Entry<Driver, Double> bestDriverEntry = ReviewController.findBestRatedDriverInCompany(companyIdToSearch, drivers, reviews);

                        // result
                        if (bestDriverEntry != null) {
                            System.out.println("Best Driver: " + bestDriverEntry.getKey());
                            System.out.println("Average Rating: " + bestDriverEntry.getValue());
                        } else {
                            System.out.println("No drivers found for the specified company.");
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        List<Driver> drivers = new ArrayList<>();
                        List<Review> reviews = new ArrayList<>();

                        // read CSV file and populate drivers and reviews
                        File file = new File("C:\\Users\\pc\\IdeaProjects\\TaxisLab\\data\\reviews.csv");
                        if (!file.exists()) {
                            System.out.println("File not found: " + file.getAbsolutePath());
                            return; // Exit if the file does not exist
                        }

                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String line;
                            br.readLine();
                            System.out.println("Started reading file...");

                            // Read and print each line to verify that the file is being read
                            int counter = 0;
                            while ((line = br.readLine()) != null) {
                                counter++;
                                System.out.println("Processing line " + counter + ": " + line);  // Debugging

                                String[] parts = line.split(";");
                                System.out.println("Parts: " + Arrays.toString(parts));  // Debugging

                                // Check if the line has 18 fields
                                if (parts.length != 18) {
                                    System.out.println("Skipping line due to incorrect number of fields: " + line);
                                    continue;
                                }

                                try {
                                    Integer reviewId = Integer.parseInt(parts[0]);
                                    int rating = Integer.parseInt(parts[1]);
                                    String description = parts[2];

                                    int companyId = Integer.parseInt(parts[3]);
                                    String companyName = parts[4];
                                    String companyEmail = parts[5];
                                    String companyAddress = parts[6];
                                    String companyPhone = parts[7];

                                    int driverId = Integer.parseInt(parts[8]);
                                    String driverName = parts[9];
                                    String driverEmail = parts[10];
                                    String driverAddress = parts[11];
                                    String driverPhone = parts[12];

                                    int clientId = Integer.parseInt(parts[13]);
                                    String clientName = parts[14];
                                    String clientEmail = parts[15];
                                    String clientAddress = parts[16];
                                    String clientPhone = parts[17];

                                    // Create Company and Client objects
                                    Company company = new Company(companyId, companyName, companyEmail, companyAddress, companyPhone);
                                    Client client = new Client(clientId, clientName, clientEmail, clientAddress, clientPhone);

                                    // Check if the company, driver, and client objects are created correctly
                                    System.out.println("Created Company: " + company);
                                    System.out.println("Created Client: " + client);

                                    // Add the driver if not already in the list
                                    Driver driver = new Driver(driverId, driverName, driverPhone, driverEmail, driverAddress);
                                    if (drivers.stream().noneMatch(d -> d.getId() == driverId)) {
                                        drivers.add(driver);
                                        System.out.println("Added Driver: " + driver);
                                    }

                                    // Create and add the review to the list
                                    Review review = new Review(reviewId, rating, description, company, driver, client);
                                    reviews.add(review);
                                    System.out.println("Added Review: " + review);

                                } catch (NumberFormatException e) {
                                    System.out.println("Error parsing line (skipping): " + line + " | Error: " + e.getMessage());
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Error reading the file: " + e.getMessage());
                        }

                        // print the number of drivers and reviews read
                        System.out.println("Number of drivers loaded: " + drivers.size());
                        System.out.println("Number of reviews loaded: " + reviews.size());
                        System.out.println();

                        // find the best driver for company 2 (for example)
                        //int clientId = 1;// Example client ID to search
                        System.out.println("Please provide the clients id...:");
                        int clientId = Integer.parseInt(scanner.nextLine());
                        Map.Entry<Driver, Double> bestDriverEntry = ReviewController.findFavouriteDriverByClient(clientId, drivers, reviews);

                        // result
                        if (bestDriverEntry != null) {
                            System.out.println("Favourite Driver: " + bestDriverEntry.getKey());
                            System.out.println("Average Rating: " + bestDriverEntry.getValue());
                        } else {
                            System.out.println("No drivers found for the specified client.");
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    break;

                case 7:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


}
