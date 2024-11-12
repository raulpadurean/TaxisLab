package org.example.services;

import org.example.models.CustomService;
import org.example.repositories.IRepository;

import java.util.List;

public class CustomServiceService {
    private final IRepository<CustomService> serviceRepository;

    public CustomServiceService(IRepository<CustomService> serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void addCustomService(CustomService service) {
        serviceRepository.create(service);
    }

    public CustomService getCustomService(int id) {
        return serviceRepository.read(id);
    }

    public List<CustomService> getAllCustomServices() {
        return serviceRepository.getAll();
    }

    public void updateCustomService(CustomService service) {
        serviceRepository.update(service);
    }

    public void deleteCustomService(int id) {
        serviceRepository.delete(id);
    }
}
