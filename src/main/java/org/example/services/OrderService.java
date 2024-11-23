package org.example.services;

import org.example.models.*;
import org.example.repositories.IRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private final IRepository<Order> orderRepository;
    private final IRepository<Service> serviceRepository;
    private final IRepository<Client> clientRepository;
    private final IRepository<Driver> driverRepository;
    private final IRepository<Company> companyRepository;

    public OrderService(IRepository<Order> orderRepository, IRepository<Service> serviceRepository, IRepository<Client> clientRepository, IRepository<Driver> driverRepository, IRepository<Company> companyRepository) {
        this.orderRepository = orderRepository;
        this.serviceRepository = serviceRepository;
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
        this.companyRepository = companyRepository;
    }

    public void addOrder(double totalKm, int serviceId, int clientId, int driverId, int companyId, Date datetime) {
        // Fetch related objects from repositories
        Service service = serviceRepository.read(serviceId);
        if (service == null) {
            throw new IllegalArgumentException("Service not found with ID: " + serviceId);
        }

        Client client = clientRepository.read(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Client not found with ID: " + clientId);
        }

        Driver driver = driverRepository.read(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Driver not found with ID: " + driverId);
        }

        Company company = companyRepository.read(companyId);
        if (company == null) {
            throw new IllegalArgumentException("Company not found with ID: " + companyId);
        }

        int orderId = orderRepository.readAll().size() + 1;

        // Create and add the order
        Order order = new Order(orderId, totalKm, service, client, driver, company, datetime);
        orderRepository.create(order);
    }


    public Order getOrder(int id) {
        return orderRepository.read(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.readAll();
    }

    public void updateOrder(Order order) {
        orderRepository.update(order);
    }

    public void deleteOrder(Integer orderId) {
        orderRepository.delete(orderId);
    }

    public List<Order> filterOrdersByServiceType(String serviceType) {
        return orderRepository.readAll().stream()
                .filter(order -> order.getService().getServiceType().equalsIgnoreCase(serviceType))
                .collect(Collectors.toList());
    }

}

