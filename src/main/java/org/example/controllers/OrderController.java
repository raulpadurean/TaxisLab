package org.example.controllers;

import org.example.models.Order;
import org.example.models.ServiceType;
import org.example.services.OrderService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller class responsible for managing {@link Order} objects.
 * This class provides methods to add, retrieve, update, delete, and filter orders, delegating
 * the actual business logic to the {@link OrderService} class.
 */
public class OrderController {

    private final OrderService orderService;

    /**
     * Constructs an {@link OrderController} with the specified {@link OrderService}.
     * This service is responsible for the business logic related to order operations.
     *
     * @param orderService The {@link OrderService} used to delegate business logic.
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Adds a new {@link Order} with the provided details.
     * Delegates the actual operation to the {@link OrderService}.
     *
     * @param totalKm The total kilometers for the order.
     * @param serviceId The ID of the service being used for the order.
     * @param clientId The ID of the client who placed the order.
     * @param driverId The ID of the driver assigned to the order.
     * @param companyId The ID of the company managing the order.
     * @param datetime The date and time when the order is placed.
     */
    public void addOrder(double totalKm, int serviceId, int clientId, int driverId, int companyId, LocalDateTime datetime) {
        orderService.addOrder(totalKm, serviceId, clientId, driverId, companyId, datetime);
    }

    /**
     * Retrieves an {@link Order} by its unique identifier.
     * Delegates the actual retrieval to the {@link OrderService}.
     *
     * @param id The unique identifier of the order.
     * @return The {@link Order} object with the specified ID, or {@code null} if not found.
     */
    public Order getOrder(int id) {
        return orderService.getOrder(id);
    }

    /**
     * Retrieves all {@link Order} objects.
     * Delegates the actual retrieval to the {@link OrderService}.
     *
     * @return A list of all {@link Order} objects.
     */
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Updates the details of an existing {@link Order} with the provided information.
     * Delegates the actual update to the {@link OrderService}.
     *
     * @param id The unique identifier of the order to update.
     * @param totalKm The updated total kilometers for the order.
     * @param serviceId The updated service ID.
     * @param clientId The updated client ID.
     * @param driverId The updated driver ID.
     * @param companyId The updated company ID.
     * @param datetime The updated date and time for the order.
     */
    public void updateOrder(int id, double totalKm, int serviceId, int clientId, int driverId, int companyId, LocalDateTime datetime) {
        orderService.updateOrder(id, totalKm, serviceId, clientId, driverId, companyId, datetime);
    }

    /**
     * Deletes an {@link Order} by its unique identifier.
     * Delegates the actual deletion to the {@link OrderService}.
     *
     * @param id The unique identifier of the order to delete.
     */
    public void deleteOrder(int id) {
        orderService.deleteOrder(id);
    }

    /**
     * Filters and retrieves orders based on the service type.
     * Delegates the actual filtering to the {@link OrderService}.
     *
     * @param type The type of the service used for the order (e.g., "Basic" or "Custom").
     * @return A list of orders matching the specified service type.
     */
    public List<Order> filterOrdersByServiceType(ServiceType type) {
        return orderService.filterOrdersByServiceType(type);
    }
}
