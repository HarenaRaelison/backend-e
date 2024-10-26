package com.example.ereserve.Client;

import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class StripeClient {

    @Value("${stripe.apikey}")
    private String apiKey;

    // Cette méthode sera appelée après l'injection des valeurs
    @PostConstruct
    public void init() {
        // Initialise Stripe avec la clé API injectée
        Stripe.apiKey = apiKey;
    }

    // Méthode pour créer un client Stripe
    public Customer createCustomer(String name, String email, String phone) throws Exception {
        CustomerCreateParams params = CustomerCreateParams.builder()
                .setName(name)
                .setEmail(email)
                .setPhone(phone)
                .build();

        return Customer.create(params);
    }
}
