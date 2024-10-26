package com.example.ereserve.Controllers;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/webhooks")
public class StripeWebhookController {

    // Clé secrète du webhook
    private static final String ENDPOINT_SECRET = "whsec_76e92094f80171aaec2e75412299b0bbf90ee0e56db6921030b8d200438cd781"; // Remplacez par votre clé webhook

    // Route pour le webhook Stripe
    @PostMapping("/stripe")
    public Map<String, Object> handleStripeWebhook(@RequestBody String payload,
                                                   @RequestHeader("Stripe-Signature") String sigHeader) {
        Map<String, Object> response = new HashMap<>();
        Event event;

        try {
            // Vérification de la signature du webhook
            event = Webhook.constructEvent(payload, sigHeader, ENDPOINT_SECRET);
        } catch (SignatureVerificationException e) {
            // Signature invalide
            response.put("status", "error");
            response.put("message", "Signature verification failed.");
            return response;
        }

        // Désérialisation de l'événement Stripe
        if ("checkout.session.completed".equals(event.getType())) {
            // Si la session de paiement a été complétée
            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            if (dataObjectDeserializer.getObject().isPresent()) {
                Session session = (Session) dataObjectDeserializer.getObject().get();
                String sessionId = session.getId();

                // Récupérez les métadonnées pour obtenir l'utilisateur et la réservation
                String userId = session.getMetadata().get("userId"); // Assurez-vous d'avoir ce champ
                String transportReservationId = session.getMetadata().get("transportReservationId");

                // Ajouter les sièges à la réservation
                addSeatsToReservation(userId, transportReservationId);

                System.out.println("Paiement complété pour la session ID : " + sessionId);
                response.put("status", "success");
                response.put("session_id", sessionId);
            }
        } else {
            // Gérer d'autres types d'événements ici
            response.put("status", "unhandled_event");
            response.put("event_type", event.getType());
        }

        return response;
    }

    private void addSeatsToReservation(String userId, String transportReservationId) {
        // Logique pour ajouter les sièges réservés à la base de données
        // Exemple fictif : appeler votre service de réservation
        // transportReservationService.addSeats(userId, transportReservationId);
        System.out.println("Ajout des sièges pour l'utilisateur : " + userId + ", réservation : " + transportReservationId);
    }
}
