package com.example.ereserve.Controllers;

import com.example.ereserve.Entity.ServiceReservation;
import com.example.ereserve.Services.Interface.ServiceReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services")
@AllArgsConstructor
public class ServiceReservationController {

    private final ServiceReservationService serviceReservationService;

    @PostMapping
    public ResponseEntity<ServiceReservation> createService(@RequestBody ServiceReservation serviceReservation) {
        ServiceReservation createdService = serviceReservationService.addService(serviceReservation);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        String result = serviceReservationService.deleteService(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
