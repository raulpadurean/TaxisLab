package org.example.services;

import org.example.models.*;
import org.example.repositories.IRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing Order entities.
 * Provides functionality to add, retrieve, update, delete orders, and filter orders by service type.
 */
public class OrderService {

    private final IRepository<Order> orderRepository;
    private final IRepository<Service> serviceRepository;
    private final IRepository<Client> clientRepository;
    private final IRepository<Driver> driverRepository;
    private final IRepository<Company> companyRepository;

    /**
     * Constructs an OrderService with the specified repositories for managing Order, Service, Client, Driver, and Company objects.
     *
     * @param orderRepository   The repository for managing Order objects.
     * @param serviceRepository The repository for managing Service objects.
     * @param clientRepository  The repository for managing Client objects.
     * @param driverRepository  The repository for managing Driver objects.
     * @param companyRepository The repository for managing Company objects.
     */
    public OrderService(IRepository<Order> orderRepository, IRepository<Service> serviceRepository, IRepository<Client> clientRepository, IRepository<Driver> driverRepository, IRepository<Company> companyRepository) {
        this.orderRepository = orderRepository;
        this.serviceRepository = serviceRepository;
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
        this.companyRepository = companyRepository;
    }

    /**
     * Adds a new order with the provided details and generates a new unique ID for the order.
     * Validates the existence of related entities (Service, Client, Driver, Company) before creating the order.
     *
     * @param totalKm   The total kilometers for the order.
     * @param serviceId The ID of the associated service.
     * @param clientId  The ID of the associated client.
     * @param driverId  The ID of the associated driver.
     * @param companyId The ID of the associated company.
     * @param datetime  The datetime of the order.
     * @throws IllegalArgumentException If any related entity (Service, Client, Driver, Company) is not found.
     */
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

        int orderId = orderRepository.readAll().size() + 1; // Generate new order ID

        // Create and add the order
        Order order = new Order(orderId, totalKm, service, client, driver, company, datetime);
        orderRepository.create(order);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return The Order object with the specified ID, or null if not found.
     */
    public Order getOrder(int id) {
        return orderRepository.read(id);
    }

    /**
     * Retrieves all orders in the repository.
     *
     * @return A list of all Order objects in the repository.
     */
    public List<Order> getAllOrders() {
        return orderRepository.readAll();
    }

    /**
     * Updates an existing order with the provided details.
     * Validates that the order and related entities (Service, Client, Driver, Company) exist before updating.
     *
     * @param id        The ID of the order to update.
     * @param totalKm   The updated total kilometers for the order.
     * @param serviceId The updated ID of the associated service.
     * @param clientId  The updated ID of the associated client.
     * @param driverId  The updated ID of the associated driver.
     * @param companyId The updated ID of the associated company.
     * @param datetime  The updated datetime of the order.
     * @throws IllegalArgumentException If the order with the given ID or any related entity is not found.
     */
    public void updateOrder(int id, double totalKm, int serviceId, int clientId, int driverId, int companyId, Date datetime) {
        // Fetch the existing order
        Order existingOrder = orderRepository.read(id);
        if (existingOrder == null) {
            throw new IllegalArgumentException("Order with ID " + id + " does not exist.");
        }

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

        // Create updated order object
        Order updatedOrder = new Order(id, totalKm, service, client, driver, company, datetime);

        // Update the order in the repository
        orderRepository.update(updatedOrder);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId The ID of the order to delete.
     */
    public void deleteOrder(Integer orderId) {
        orderRepository.delete(orderId);
    }

    /**
     * Filters orders by the specified service type.
     *
     * @param serviceType The service type to filter orders by.
     * @return A list of orders that match the specified service type.
     */
    public List<Order> filterOrdersByServiceType(String serviceType) {
        return orderRepository.readAll().stream()
                .filter(order -> order.getService().getServiceType().equalsIgnoreCase(serviceType))
                .collect(Collectors.toList());
    }
}
