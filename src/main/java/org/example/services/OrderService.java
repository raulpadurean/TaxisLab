package org.example.services;

import org.example.models.Order;
import org.example.repositories.IRepository;

import java.util.List;

public class OrderService {

    private final IRepository<Order> orderRepository;

    public OrderService(IRepository<Order> orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order) {
        orderRepository.create(order);
    }

    public Order getOrder(int id) {
        return orderRepository.read(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAll();
    }

    public void updateOrder(Order order) {
        orderRepository.update(order);
    }

    public void deleteOrder(int id) {
        orderRepository.delete(id);
    }

}

