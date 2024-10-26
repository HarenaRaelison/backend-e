package com.example.ereserve.Controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    // Initialisation de la clé API Stripe
    public PaymentController() {
        Stripe.apiKey = "sk_test_51Q0JBGKRZ1jB7FLU2vxoZZnHLA6MJkRAGUGs6ec7MFaDMPEG9yQswXSvlqYzL4TvTYRlfp1R4YrTqoP108X2ITiq00y7CB4WEa";
    }

    @PostMapping("/create-checkout-session")
    public Map<String, String> createCheckoutSession(@RequestBody Map<String, Object> requestData) throws StripeException {
        // Récupérer les places et le prix en ariary depuis la requête
        List<Integer> places = (List<Integer>) requestData.get("place");
        int priceInAriary = Integer.parseInt(requestData.get("price").toString()); // Prix en ariary donné dans la requête

        // Taux de conversion MGA -> USD (exemple: 1 USD = 4500 MGA)
        double conversionRate = 4500.0;
        int priceInUsdCents = (int) ((priceInAriary / conversionRate) * 100); // Converti en centimes USD

        // Construire les LineItems pour chaque place
        SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5173/")
                .setCancelUrl("http://localhost:5173/transportreserve");

        // Boucle sur chaque place pour créer un LineItem correspondant
        for (int place : places) {
            paramsBuilder.addLineItem(
                    SessionCreateParams.LineItem.builder()
                            .setQuantity(1L) // 1 article par place
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("usd") // Devise en USD
                                            .setUnitAmount((long) priceInUsdCents) // Montant en centimes USD
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName("Place n°" + place) // Nom du produit (la place)
                                                            .build()
                                            )
                                            .build()
                            )
                            .build()
            );
        }

        // Création de la session Stripe
        SessionCreateParams params = paramsBuilder.build();
        Session session = Session.create(params);

        // Retourne l'URL de la session de paiement à l'utilisateur
        Map<String, String> response = new HashMap<>();
        response.put("url", session.getUrl());

        return response;
    }
}
