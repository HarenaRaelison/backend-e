package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.ServiceReservation;

public interface ServiceReservationService {
    ServiceReservation addService(ServiceReservation serviceReservation);
    String deleteService(Long idService);
    ServiceReservation getServiceByUserId(Long userId);
}
