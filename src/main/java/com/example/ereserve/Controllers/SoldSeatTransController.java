package com.example.ereserve.Controllers;

import com.example.ereserve.Entity.SoldSeatTrans;
import com.example.ereserve.Services.implement.SoldSeatTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sold-seats")
public class SoldSeatTransController {

    @Autowired
    private SoldSeatTransService soldSeatTransService;

    // Endpoint to add sold seats to a reservation
    @PostMapping("/add")
    public ResponseEntity<SoldSeatTrans> addSoldSeats(
            @RequestParam Long userId,
            @RequestParam Long transportReservationId,
            @RequestBody List<Integer> seatNumbers) {

        if (seatNumbers == null || seatNumbers.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Validate input
        }

        SoldSeatTrans soldSeatTrans = soldSeatTransService.addSoldSeats(userId, transportReservationId, seatNumbers);
        return ResponseEntity.status(HttpStatus.CREATED).body(soldSeatTrans); // Return 201 Created
    }

    // Endpoint to retrieve sold seat numbers by reservation ID
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<List<Integer>> getSoldSeatsByReservationId(@PathVariable Long reservationId) {
        List<Integer> seatNumbers = soldSeatTransService.getSoldSeatsByReservationId(reservationId);
        return ResponseEntity.ok(seatNumbers);
    }

    // Endpoint to update the status to true
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatusToTrue(@PathVariable Long id) {
        boolean updated = soldSeatTransService.updateStatusToTrue(id);

        if (updated) {
            return ResponseEntity.ok("Status updated to true");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SoldSeatTrans entity not found");
        }
    }
}
