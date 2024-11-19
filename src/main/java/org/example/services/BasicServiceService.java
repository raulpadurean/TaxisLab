package org.example.services;

import org.example.models.BasicService;
import org.example.repositories.IRepository;

import java.util.List;

public class BasicServiceService {
    private final IRepository<BasicService> serviceRepository;

    public BasicServiceService(IRepository<BasicService> serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void addBasicService(BasicService service) {
        serviceRepository.create(service);
    }

    public BasicService getBasicService(int id) {
        return serviceRepository.read(id);
    }

    public List<BasicService> getAllBasicServices() {
        return serviceRepository.getAll();
    }

    public void updateBasicService(BasicService service) {
        serviceRepository.update(service);
    }


    public void deleteBasicService(Integer basicServiceId) {

        serviceRepository.delete(basicServiceId);
    }
}