package com.example.ereserve.Controllers;

import com.example.ereserve.Client.StripeClient;
import com.example.ereserve.Entity.PaymentEvent;
import com.example.ereserve.Entity.ReservationEvent;
import com.example.ereserve.Repository.PayementEventRepository;
import com.example.ereserve.Repository.ReservationEventRepository;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class StripeController {

    @Autowired
    private StripeClient stripeClient;

    @Autowired
    private ReservationEventRepository reservationEventRepository;

    @Autowired
    private PayementEventRepository payementEventRepository;

    @PostMapping("/charge")
    public ResponseEntity<?> chargeCard(@RequestParam("token") String token, @RequestParam("reservationId") Long reservationId) {
        try {
            // Récupérer la réservation
            Optional<ReservationEvent> optionalReservationEvent = reservationEventRepository.findById(reservationId);
            if (optionalReservationEvent.isEmpty()) {
                return ResponseEntity.badRequest().body("La réservation n'existe pas.");
            }
            ReservationEvent reservationEvent = optionalReservationEvent.get();
            // Effectuer le paiement via Stripe
            BigDecimal amountToCharge = reservationEvent.getMontantTotal();  // Montant total à payer
            Charge charge = stripeClient.chargeNewCard(token, amountToCharge.doubleValue());

            // Créer un nouvel enregistrement de paiement
            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setReservationEvent(reservationEvent);
            paymentEvent.setMontant(amountToCharge);
            paymentEvent.setDevise("USD");
            paymentEvent.setModePaiement("stripe");
            paymentEvent.setStatut("payé");  // Modifier le statut du paiement
            paymentEvent.setPayerEmail(charge.getReceiptEmail());
            paymentEvent.setPaypalTransactionId(charge.getId()); // Utiliser le champ pour stocker l'ID de la transaction Stripe
            paymentEvent.setPaypalFee(new BigDecimal(charge.getBalanceTransactionObject().getFee()));


            // Sauvegarder le paiement dans la base de données
            payementEventRepository.save(paymentEvent);

            // Mettre à jour le statut de la réservation
            reservationEvent.setStatus(true);  // Mettre le statut à "true" pour indiquer que le paiement est fait
            reservationEventRepository.save(reservationEvent);

            return ResponseEntity.ok("Paiement effectué avec succès.");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors du traitement du paiement: " + e.getMessage());
        }
    }
}
