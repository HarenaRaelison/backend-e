package com.example.ereserve.Controllers;

import com.example.ereserve.Entity.ReservationEvent;
import com.example.ereserve.Services.Interface.ReservationEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/event-reservations")
@AllArgsConstructor
public class ReservationEventController {

    private final ReservationEventService reservationEventService;

    @PostMapping
    public ResponseEntity<ReservationEvent> createReservation(@RequestBody ReservationEvent reservationEvent) {
        ReservationEvent createdReservation = reservationEventService.reserveEvent(reservationEvent);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationEvent> updateReservation(@PathVariable Long id, @RequestBody ReservationEvent reservationEvent) {
        ReservationEvent updatedReservation = reservationEventService.UpdateReserveEvent(id, reservationEvent);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        String result = reservationEventService.deleteReservation(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
