package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.EvenementReservation;
import com.example.ereserve.Repository.EvenementRepository;
import com.example.ereserve.Services.Interface.EvenementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EvenementServiceImpl implements EvenementService {
    private final EvenementRepository  evenementRepository;
    @Override
    public EvenementReservation addEvent(EvenementReservation evenementReservation) {
        return evenementRepository.save(evenementReservation);
    }

    @Override
    public EvenementReservation updateEvent(Long idEvent, EvenementReservation evenementReservation) {
        return evenementRepository.findById(idEvent)
                .map(
                        p -> {
                            p.setDate(p.getDate());
                            p.setDescription(p.getDescription());
                            p.setCategorieEvent(p.getCategorieEvent());
                            p.setImagePath(p.getImagePath());
                            p.setLieuDepart(p.getImagePath());
                            p.setServiceReservation(p.getServiceReservation());
                            p.setTitre(p.getTitre());
                            p.setTime(p.getTime());

                            return evenementRepository.save(p);
                        }
                ).orElseThrow(()->new RuntimeException("Update Error"));
    }

    @Override
    public String deleteEvent(Long idEvent) {
        return null;
    }
}
