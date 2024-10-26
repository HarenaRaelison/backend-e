package com.example.ereserve.Controllers;

import com.example.ereserve.Entity.ReservationTrans;
import com.example.ereserve.Services.Interface.ReservationTransService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/transport-reservations")
@AllArgsConstructor
public class ReservationTransController {

    private final ReservationTransService reservationTransService;

    @PostMapping
    public ResponseEntity<ReservationTrans> createReservation(@RequestBody ReservationTrans reservationTrans) {
        ReservationTrans createdReservation = reservationTransService.reserveEvent(reservationTrans);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationTrans> updateReservation(@PathVariable Long id, @RequestBody ReservationTrans reservationTrans) {
        ReservationTrans updatedReservation = reservationTransService.UpdateReserveEvent(id, reservationTrans);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        String result = reservationTransService.deleteReservation(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
