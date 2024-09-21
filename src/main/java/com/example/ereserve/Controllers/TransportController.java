package com.example.ereserve.Controllers;

import com.example.ereserve.Entity.TransportReservation;
import com.example.ereserve.Repository.TransportRepository;
import com.example.ereserve.Services.Interface.TransportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/transport")
@AllArgsConstructor
public class TransportController {
    @Autowired
    private TransportService transportService;
    private final TransportRepository transportRepository;

    // Endpoint to add a new transport reservation
    @PostMapping("/add")
    public ResponseEntity<TransportReservation> addTransport(@RequestBody TransportReservation transportReservation) {
        System.out.println("Date de départ: " + transportReservation.getDateDepart());
        TransportReservation newTransport = transportRepository.save(transportReservation);
        return ResponseEntity.ok(newTransport);
    }

    // Endpoint to update an existing transport reservation by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<TransportReservation> updateTransport(@PathVariable("id") Long idTrans, @RequestBody TransportReservation transportReservation) {
        return transportRepository.findById(idTrans)
                .map(t -> {
                    t.setServiceId(t.getServiceId());
                    t.setNombrePlace(t.getNombrePlace());
                    t.setPrix(t.getPrix());
                    t.setLieuDepart(t.getLieuDepart());
                    t.setName(t.getName());
                    t.setLieuArriver(t.getLieuArriver());

                    t.setDateDepart(t.getDateDepart());
                    t.setCategorie(t.getCategorie());

                    t.setHeureDepart(t.getHeureDepart());
                    TransportReservation updatedTransport = transportRepository.save(t);
                    return ResponseEntity.ok(updatedTransport);
                })
                .orElseThrow(() -> new RuntimeException("Update Error: Transport not found"));
    }
    @GetMapping
    public List<TransportReservation> getAllTransports() {
        return transportService.getAllTransports(); // Call service to get all transports
    }
    // Endpoint to delete a transport reservation by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransport(@PathVariable("id") Long idTrans) {
        if (transportRepository.existsById(idTrans)) {
            transportRepository.deleteById(idTrans);
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Transport not found");
        }
    }
}
