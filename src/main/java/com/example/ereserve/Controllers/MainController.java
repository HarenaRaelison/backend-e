package com.example.ereserve.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class MainController {

    @GetMapping("/")
    public String main() {
        return "Welcome, let's get started!";
    }
    @GetMapping("/user")
    public ResponseEntity<?> user(OAuth2AuthenticationToken authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        OAuth2User principal = authentication.getPrincipal();
        Map<String, Object> userDetails = principal.getAttributes();

        return ResponseEntity.ok(userDetails);
    }

}
