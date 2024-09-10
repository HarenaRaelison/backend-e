package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.ReservationEvent;
import com.example.ereserve.Repository.ReservationEventRepository;
import com.example.ereserve.Services.Interface.ReservationEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationEventServiceImpl implements ReservationEventService {

    private final ReservationEventRepository reservationEventRepository;
    @Override
    public ReservationEvent reserveEvent(ReservationEvent reservationEvent) {
        return reservationEventRepository.save(reservationEvent);
    }

    @Override
    public ReservationEvent UpdateReserveEvent(Long idReservation, ReservationEvent reservationEvent) {
        return reservationEventRepository.findById(idReservation)
                .map(
                        p -> {
                            p.setUser(p.getUser());
                            p.setEvenementReservation(p.getEvenementReservation());
                            p.setStatus(p.isStatus());
                            p.setEvenementReservation(p.getEvenementReservation());
                            p.setMontantTotal(p.getMontantTotal());
                            p.setNombrePlacesReservees(p.getNombrePlacesReservees());
                            return reservationEventRepository.save(p);
                        }
                ).orElseThrow(()->new RuntimeException("Update Error"));
    }

    @Override
    public String deleteReservation(Long idReservation) {
        reservationEventRepository.deleteById(idReservation);
        return "deleted succesfully !";
    }
}
