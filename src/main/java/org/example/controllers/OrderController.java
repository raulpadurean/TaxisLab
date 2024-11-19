package org.example.controllers;

import org.example.models.Order;
import org.example.services.OrderService;

import java.util.Date;
import java.util.List;

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void addOrder(Integer serviceId, double totalKm, int clientId, int driverId, int companyId, Date datetime) {
        Order order = new Order(serviceId, totalKm, clientId, driverId, companyId, datetime); // ID is set by repository
        orderService.addOrder(order);
    }

    public Order getOrder(int id) {
        return orderService.getOrder(id);
    }

    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public void updateOrder(Integer serviceId, double totalKm, int clientId, int driverId, int companyId, Date datetime) {
        Order order = new Order(serviceId, totalKm, clientId, driverId, companyId, datetime); // ID is set by repository
        orderService.updateOrder(order);
    }
    public void deleteOrder(Integer orderId) {
        orderService.deleteOrder(orderId);

    }
}
