package com.example.ereserve.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class OAuth2CallbackController {

    @GetMapping("/oauth2/callback")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam String code) {
        // Logique pour échanger le code avec un token (Google OAuth2)
        String token = "fake-jwt-token"; // Génère ou récupère un vrai token JWT ici

        return ResponseEntity.ok(Map.of("token", token));
    }
}
