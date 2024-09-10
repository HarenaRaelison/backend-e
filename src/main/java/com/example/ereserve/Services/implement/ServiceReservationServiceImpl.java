package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.ServiceReservation;
import com.example.ereserve.Repository.ServiceRepository;
import com.example.ereserve.Services.Interface.ServiceReservationService;
import org.springframework.stereotype.Service;

@Service
public class ServiceReservationServiceImpl implements ServiceReservationService {
public final ServiceRepository serviceRepository;

    public ServiceReservationServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceReservation addService(ServiceReservation serviceReservation) {
        return serviceRepository.save(serviceReservation);
    }

    @Override
    public String deleteService(Long idService) {
        serviceRepository.deleteById(idService);
        return "deleted succesfully";
    }
}
