package org.example.services;

import org.example.models.Order;
import org.example.repositories.IRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteOrder(Integer orderId) {
        orderRepository.delete(orderId);
    }

    public List<Order> filterOrdersByServiceType(String serviceType) {
        return orderRepository.getAll().stream()
                .filter(order -> order.getService().getServiceType().equalsIgnoreCase(serviceType))
                .collect(Collectors.toList());
    }

}

