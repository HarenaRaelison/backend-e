package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.TransportReservation;
import com.example.ereserve.Repository.TransportRepository;
import com.example.ereserve.Services.Interface.TransportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransportServiceImpl implements TransportService {
    private final TransportRepository  transportRepository;
    @Override
    public TransportReservation addTransport(TransportReservation transportReservation) {
        return transportRepository.save(transportReservation);
    }

    @Override
    public TransportReservation updateTransport(Long idTrans, TransportReservation transportReservation) {
        return transportRepository.findById(idTrans)
                .map(
                        p -> {
                            p.setDateDepart(p.getDateDepart());
                            p.setHeureDepart(p.getHeureDepart());
                            p.setName(p.getName());
                            p.setImagePath(p.getImagePath());
                            p.setLieuArriver(p.getLieuArriver());
                            p.setPrix(p.getPrix());
                            p.setLieuDepart(p.getLieuDepart());
                            p.setNombrePlace(p.getNombrePlace());
                            p.setServiceReservation(p.getServiceReservation());

                            return transportRepository.save(p);
                        }
                ).orElseThrow(()->new RuntimeException("Update Error"));
    }

    @Override
    public String deleteTransport(Long idTrans) {
        transportRepository.deleteById(idTrans);
        return "Deleted succesfully";
    }
}
