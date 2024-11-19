package org.example.controllers;

import org.example.models.Order;
import org.example.services.OrderService;

import java.util.Date;
import java.util.List;

/**
 * Controller class for managing Order entities.
 * This class provides methods to handle operations such as adding, retrieving,
 * updating, and deleting orders. It interacts with the {@link OrderService}
 * to perform the necessary business logic for orders.
 */
public class OrderController {

    private final OrderService orderService;

    /**
     * Constructs a new {@code OrderController} with the specified service.
     *
     * @param orderService the service instance for handling order-related operations
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Adds a new order with the specified details.
     *
     * @param serviceId  the ID of the service associated with the order
     * @param totalKm   the total kilometers of the order
     * @param clientId  the ID of the client placing the order
     * @param driverId  the ID of the driver assigned to the order
     * @param companyId the ID of the company handling the order
     * @param datetime  the date and time the order was placed
     */
    public void addOrder(Integer serviceId, double totalKm, int clientId, int driverId, int companyId, Date datetime) {
        Order order = new Order(serviceId, totalKm, clientId, driverId, companyId, datetime);
        orderService.addOrder(order);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the unique identifier of the order
     * @return the {@link Order} object with the specified ID, or {@code null} if not found
     */
    public Order getOrder(int id) {
        return orderService.getOrder(id);
    }

    /**
     * Retrieves all orders.
     *
     * @return a list of all {@link Order} objects
     */
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Updates an existing order with the specified details.
     *
     * @param serviceId  the updated ID of the service associated with the order
     * @param totalKm   the updated total kilometers of the order
     * @param clientId  the updated ID of the client placing the order
     * @param driverId  the updated ID of the driver assigned to the order
     * @param companyId the updated ID of the company handling the order
     * @param datetime  the updated date and time the order was placed
     */
    public void updateOrder(Integer serviceId, double totalKm, int clientId, int driverId, int companyId, Date datetime) {
        Order order = new Order(serviceId, totalKm, clientId, driverId, companyId, datetime);
        orderService.updateOrder(order);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the unique identifier of the order to be deleted
     */
    public void deleteOrder(Integer orderId) {
        orderService.deleteOrder(orderId);
    }
}
