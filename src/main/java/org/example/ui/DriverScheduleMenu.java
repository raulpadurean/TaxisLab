package org.example.ui;

import org.example.controllers.DriverScheduleController;
import org.example.models.Company;
import org.example.models.Driver;
import org.example.models.DriverSchedule;
import org.example.repositories.IRepository;
import org.example.services.DriverScheduleService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DriverScheduleMenu {


    /**
     * Menu for managing driver schedules.
     * Provides options to add, view, update, and delete driver schedules.
     */

    public static void show(IRepository<DriverSchedule> driverScheduleRepo,
                            IRepository<Driver> driverRepo,
                            IRepository<Company> companyRepo) {
        DriverScheduleService driverScheduleService = new DriverScheduleService(driverScheduleRepo, driverRepo, companyRepo);
        DriverScheduleController driverScheduleController = new DriverScheduleController(driverScheduleService);
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    
                    Options:
                    1. Add Driver Schedule
                    2. View Driver Schedules
                    3. Update Driver Schedule
                    4. Delete Driver Schedule
                    5. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter Driver Schedule details (driverId, companyId, checkIn [yyyy-MM-dd], checkout [yyyy-MM-dd]):");

                        System.out.print("Driver ID: ");
                        int driverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Company ID: ");
                        int companyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Check-In Date (yyyy-MM-dd): ");
                        String checkInDateStr = scanner.nextLine();

                        System.out.print("Check-Out Date (yyyy-MM-dd): ");
                        String checkOutDateStr = scanner.nextLine();

                        LocalDate checkInDate = LocalDate.parse(checkInDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate checkOutDate = LocalDate.parse(checkOutDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        // Convert LocalDate to LocalDateTime (assuming check-in starts at 00:00 and check-out ends at 23:59)
                        LocalDateTime checkIn = checkInDate.atStartOfDay();
                        LocalDateTime checkOut = checkOutDate.atTime(23, 59);

                        driverScheduleController.addDriverSchedule(driverId, companyId, checkIn, checkOut);
                        System.out.println("Driver Schedule added successfully.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    } catch (Exception e) {
                        System.out.println("Error adding Driver Schedule: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("List of Driver Schedules:");
                    driverScheduleController.getAllDriverSchedules().forEach(System.out::println);
                    break;

                case 3:
                    try {
                        System.out.println("Enter the ID of the Driver Schedule to update:");
                        int scheduleId = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter updated Driver Schedule details (driverId, companyId, checkIn [yyyy-MM-dd], checkout [yyyy-MM-dd]):");

                        System.out.print("Driver ID: ");
                        int updatedDriverId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Company ID: ");
                        int updatedCompanyId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Check-In Date (yyyy-MM-dd): ");
                        String updatedCheckInDateStr = scanner.nextLine();

                        System.out.print("Check-Out Date (yyyy-MM-dd): ");
                        String updatedCheckOutDateStr = scanner.nextLine();

                        LocalDate checkInDate = LocalDate.parse(updatedCheckInDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate checkOutDate = LocalDate.parse(updatedCheckOutDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        // Convert LocalDate to LocalDateTime (assuming check-in starts at 00:00 and check-out ends at 23:59)
                        LocalDateTime updatedCheckIn = checkInDate.atStartOfDay();
                        LocalDateTime updatedCheckOut = checkOutDate.atTime(23, 59);

                        driverScheduleController.updateDriverSchedule(scheduleId, updatedDriverId, updatedCompanyId, updatedCheckIn, updatedCheckOut);
                        System.out.println("Driver Schedule updated successfully.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    } catch (Exception e) {
                        System.out.println("Error updating Driver Schedule: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter the ID of the Driver Schedule to delete:");
                        int scheduleIdToDelete = Integer.parseInt(scanner.nextLine());
                        driverScheduleController.deleteDriverSchedule(scheduleIdToDelete);
                        System.out.println("Driver Schedule deleted successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid ID.");
                    } catch (Exception e) {
                        System.out.println("Error deleting Driver Schedule: " + e.getMessage());
                    }
                    break;

                case 5:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
