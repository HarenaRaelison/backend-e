package com.example.ereserve.Controllers;

import com.example.ereserve.Entity.SoldSeatTrans;
import com.example.ereserve.Services.implement.SoldSeatTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sold-seats")
public class SoldSeatTransController {

    @Autowired
    private SoldSeatTransService soldSeatTransService;

    // Endpoint to add sold seats to a reservation, creating it if necessary
    @PostMapping("/add")
    public ResponseEntity<String> addSoldSeats(
            @RequestParam Long userId,
            @RequestParam Long transportReservationId,
            @RequestBody List<Integer> seatNumbers) {

        // Appel au service pour ajouter des sièges vendus et créer une réservation si nécessaire
        soldSeatTransService.addSoldSeats(userId, transportReservationId, seatNumbers);
        return ResponseEntity.ok("Sièges vendus ajoutés avec succès, réservation créée si nécessaire.");
    }

    // Endpoint to retrieve sold seat numbers by reservation ID
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<List<Integer>> getSoldSeatsByReservationId(@PathVariable Long reservationId) {
        List<Integer> seatNumbers = soldSeatTransService.getSoldSeatsByReservationId(reservationId);
        return ResponseEntity.ok(seatNumbers);
    }
}
