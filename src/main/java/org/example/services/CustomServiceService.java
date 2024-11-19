package org.example.services;

import org.example.models.CustomService;
import org.example.repositories.IRepository;

import java.util.List;

public class CustomServiceService {
    private final IRepository<CustomService> customServiceRepository;

    public CustomServiceService(IRepository<CustomService> serviceRepository) {
        this.customServiceRepository = serviceRepository;
    }

    public void addCustomService(CustomService service) {
        customServiceRepository.create(service);
    }

    public CustomService getCustomService(int id) {
        return customServiceRepository.read(id);
    }

    public List<CustomService> getAllCustomServices() {
        return customServiceRepository.getAll();
    }

    public void updateCustomService(CustomService service) {
        customServiceRepository.update(service);
    }

    public void deleteCustomService(Integer customServiceId) {

        customServiceRepository.delete(customServiceId);
    }
}
