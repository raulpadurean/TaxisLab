package org.example.services;

import org.example.models.Order;
import org.example.repositories.IRepository;

import java.util.List;

/**
 * Service class for managing {@link Order} entities.
 * Provides business logic for interacting with the underlying repository to perform CRUD operations on order data.
 *
 * <p>This class handles operations such as adding, retrieving, updating, and deleting order records.</p>
 */
public class OrderService {

    private final IRepository<Order> orderRepository;

    /**
     * Constructs an {@link OrderService} with the specified repository.
     *
     * @param orderRepository The repository to be used for storing and retrieving order entities.
     */
    public OrderService(IRepository<Order> orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Adds a new order to the repository.
     *
     * <p>This method persists the provided {@link Order} entity to the repository.</p>
     *
     * @param order The order to be added. Must not be {@code null}.
     */
    public void addOrder(Order order) {
        orderRepository.create(order);
    }

    /**
     * Retrieves an order by its unique ID.
     *
     * @param id The unique identifier of the order to retrieve.
     * @return The order with the specified ID, or {@code null} if no such order exists.
     */
    public Order getOrder(int id) {
        return orderRepository.read(id);
    }

    /**
     * Retrieves all orders from the repository.
     *
     * @return A list of all orders in the repository.
     */
    public List<Order> getAllOrders() {
        return orderRepository.getAll();
    }

    /**
     * Updates an existing order in the repository.
     *
     * <p>If the order already exists in the repository, it will be updated with the new values.</p>
     *
     * @param order The order to update. The order must not be {@code null}.
     */
    public void updateOrder(Order order) {
        orderRepository.update(order);
    }

    /**
     * Deletes an order from the repository.
     *
     * @param orderId The unique ID of the order to be deleted.
     */
    public void deleteOrder(Integer orderId) {
        orderRepository.delete(orderId);
    }
}
