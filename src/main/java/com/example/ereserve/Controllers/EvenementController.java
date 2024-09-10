package com.example.ereserve.Controllers;

import com.example.ereserve.Entity.EvenementReservation;

import com.example.ereserve.Services.Interface.EvenementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")


public class EvenementController {
   public final EvenementService evenementService;

    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }


    @PostMapping
    public ResponseEntity<EvenementReservation> createEvent(@RequestBody EvenementReservation evenementReservation) {
        EvenementReservation createdEvent = evenementService.addEvent(evenementReservation);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvenementReservation> updateEvent(@PathVariable Long id, @RequestBody EvenementReservation evenementReservation) {
        EvenementReservation updatedEvent = evenementService.updateEvent(id, evenementReservation);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        String result = evenementService.deleteEvent(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
