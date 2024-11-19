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


    public void addOrder(Service service, double totalKm, Client client, Driver driver, Company company, Date datetime) {
        Order order = new Order(service, totalKm, client, driver, company, datetime); // ID is set by repository

        orderService.addOrder(order);
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

    public void deleteOrder(Integer orderId) {
        orderService.deleteOrder(orderId);

    }
}
