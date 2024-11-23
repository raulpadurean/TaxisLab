package org.example.controllers;

import org.example.models.*;
import org.example.services.OrderService;

import java.util.Date;
import java.util.List;

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void addOrder(double totalKm, int serviceId, int clientId, int driverId, int companyId, Date datetime) {
        orderService.addOrder(totalKm, serviceId, clientId, driverId, companyId, datetime);
    }

    public Order getOrder(int id) {
        return orderService.getOrder(id);
    }

    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public void updateOrder(Order order) {
        orderService.updateOrder(order);
    }

    public void deleteOrder(int id) {
        orderService.deleteOrder(id);
    }
}
