package com.example.ereserve.Controllers;

import com.example.ereserve.Entity.PaymentEvent;
import com.example.ereserve.Services.Interface.PayementEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PayementEventController {

    private final PayementEventService payementEventService;

    @PostMapping
    public ResponseEntity<PaymentEvent> createPayment(@RequestBody PaymentEvent paymentEvent) {
        PaymentEvent createdPayment = payementEventService.AddPayment(paymentEvent);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentEvent> updatePayment(@PathVariable Long id, @RequestBody PaymentEvent paymentEvent) {
        PaymentEvent updatedPayment = payementEventService.updatePayment(id, paymentEvent);
        return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        String result = payementEventService.deletePayment(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
