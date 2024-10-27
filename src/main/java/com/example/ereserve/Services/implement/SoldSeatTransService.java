package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.ReservationTrans;
import com.example.ereserve.Entity.SoldSeatTrans;
import com.example.ereserve.Entity.TransportReservation;
import com.example.ereserve.Entity.User;
import com.example.ereserve.Repository.ReservationTransRepository;
import com.example.ereserve.Repository.SoldSeatTransRepository;
import com.example.ereserve.Repository.TransportRepository;
import com.example.ereserve.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoldSeatTransService {

    @Autowired
    private SoldSeatTransRepository soldSeatTransRepository;

    @Autowired
    private ReservationTransRepository reservationTransRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransportRepository transportRepository;

    public SoldSeatTrans addSoldSeats(Long userId, Long transportReservationId, List<Integer> seatNumbers) {
        // Récupération de l'utilisateur
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + userId));

        // Récupération de la réservation de transport
        TransportReservation transportReservation = transportRepository.findById(transportReservationId)
                .orElseThrow(() -> new RuntimeException("Réservation de transport introuvable avec l'ID : " + transportReservationId));

        // Vérification si une réservation existe déjà pour cet utilisateur et cette réservation de transport
        ReservationTrans reservation = reservationTransRepository
                .findByUserAndTransportReservation(user, transportReservation)
                .orElseGet(() -> {
                    ReservationTrans newReservation = new ReservationTrans();
                    newReservation.setUser(user);
                    newReservation.setTransportReservation(transportReservation);
                    newReservation.setStatus(true); // Définir l'état selon vos besoins
                    return reservationTransRepository.save(newReservation);
                });

        // Création des sièges vendus
        SoldSeatTrans soldSeatTrans = new SoldSeatTrans();
        soldSeatTrans.setReservationTrans(reservation);
        soldSeatTrans.setSeatNumbersFromList(seatNumbers); // Assurez-vous que cette méthode est définie dans SoldSeatTrans

        return soldSeatTransRepository.save(soldSeatTrans); // Sauvegarde des sièges vendus
    }

    public List<Integer> getSoldSeatsByReservationId(Long reservationId) {
        List<List<Integer>> seatNumbersList = soldSeatTransRepository.findSeatNumbersByReservationId(reservationId);
        return seatNumbersList.stream().flatMap(List::stream).toList(); // Récupération de tous les sièges vendus
    }

    public boolean updateStatusToTrue(Long id) {
        Optional<SoldSeatTrans> soldSeatTransOptional = soldSeatTransRepository.findById(id);

        if (soldSeatTransOptional.isPresent()) {
            SoldSeatTrans soldSeatTrans = soldSeatTransOptional.get();
            soldSeatTrans.setStatus(true);
            soldSeatTransRepository.save(soldSeatTrans);
            return true;
        } else {
            return false;  // Return false if entity not found
        }
    }
}
