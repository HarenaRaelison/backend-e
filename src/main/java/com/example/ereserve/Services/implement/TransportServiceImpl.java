package com.example.ereserve.Services.implement;

 // Assuming this is the service entity
import com.example.ereserve.Entity.ServiceReservation;
import com.example.ereserve.Entity.TransportReservation;
import com.example.ereserve.Repository.ServiceRepository;
import com.example.ereserve.Repository.TransportRepository;
import com.example.ereserve.Services.Interface.TransportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransportServiceImpl implements TransportService {
    private final TransportRepository transportRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public TransportReservation addTransport(TransportReservation transportReservation) {
        // Fetch service from serviceRepository
        Optional<ServiceReservation> serviceOptional = serviceRepository.findById(transportReservation.getServiceId().getId());

        if (serviceOptional.isPresent()) {
            // Set the service for the transport reservation
            transportReservation.setServiceId(serviceOptional.get());
            return transportRepository.save(transportReservation);
        } else {
            throw new RuntimeException("Service not found for the provided service ID");
        }
    }

    @Override
    public TransportReservation updateTransport(Long idTrans, TransportReservation transportReservation) {
        return transportRepository.findById(idTrans)
                .map(existingTransport -> {
                    // Update the existing transport with new values from the input transportReservation
                    existingTransport.setDateDepart(transportReservation.getDateDepart());
                    existingTransport.setHeureDepart(transportReservation.getHeureDepart());
                    existingTransport.setName(transportReservation.getName());
                    existingTransport.setCategorie(transportReservation.getCategorie());
                    existingTransport.setLieuArriver(transportReservation.getLieuArriver());
                    existingTransport.setPrix(transportReservation.getPrix());
                    existingTransport.setLieuDepart(transportReservation.getLieuDepart());
                    existingTransport.setNombrePlace(transportReservation.getNombrePlace());


                    return transportRepository.save(existingTransport);
                }).orElseThrow(() -> new RuntimeException("Transport not found for ID: " + idTrans));
    }

    @Override
    public String deleteTransport(Long idTrans) {
        if (transportRepository.existsById(idTrans)) {
            transportRepository.deleteById(idTrans);
            return "Deleted successfully";
        } else {
            throw new RuntimeException("Transport not found for ID: " + idTrans);
        }
    }

    @Override
    public List<TransportReservation> getAllTransports() {
        return transportRepository.findAll();
    }
}
