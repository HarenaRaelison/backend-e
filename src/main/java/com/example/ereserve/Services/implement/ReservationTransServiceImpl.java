package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.ReservationTrans;
import com.example.ereserve.Repository.ReservationTransRepository;
import com.example.ereserve.Services.Interface.ReservationTransService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationTransServiceImpl implements ReservationTransService {
    private final ReservationTransRepository reservationTransRepository;

    @Override
    public ReservationTrans reserveEvent(ReservationTrans reservationEvent) {
        return reservationTransRepository.save(reservationEvent);
    }

    @Override
    public ReservationTrans UpdateReserveEvent(Long idReservation, ReservationTrans reservationEvent) {
        return reservationTransRepository.findById(idReservation)
                .map(
                        p -> {
                            p.setTransportReservation(p.getTransportReservation());
                            p.setUser(p.getUser());
                            p.setMontantTotal(p.getMontantTotal());
                            p.setNombrePlacesReservees(p.getNombrePlacesReservees());
                            p.setStatus(p.isStatus());
                            return reservationTransRepository.save(p);
                        }
                ).orElseThrow(()->new RuntimeException("Update Error"));
    }

    @Override
    public String deleteReservation(Long idReservationTrans) {
        return "reservation deleted !";
    }
}
