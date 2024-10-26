package com.example.ereserve.Controllers;

import com.example.ereserve.Entity.RoleType;
import com.example.ereserve.Entity.User;
import com.example.ereserve.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@AllArgsConstructor
public class OAuth2Controller {

    private final UserRepository userRepository;

    @GetMapping("/auth/success")
    public ResponseEntity<?> handleOAuth2Success() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verify if authentication is OAuth2AuthenticationToken
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauthUser = oauthToken.getPrincipal();

            String name = oauthUser.getAttribute("name");
            String email = oauthUser.getAttribute("email");

            // Check if the user exists in the database
            Optional<User> existingUser = userRepository.findByEmail(email);
            User user;
            if (existingUser.isEmpty()) {
                // Create a new user with CLIENT role
                user = new User();
                user.setEmail(email);
                user.setFullName(name);
                user.setPassword("0000");
                user.setRole(RoleType.CLIENT);
                userRepository.save(user);
            } else {
                user = existingUser.get();
            }

            return ResponseEntity.ok(user);  // Return the user object with HTTP 200 status
        }

        // Handle unauthenticated case, return HTTP 401 Unauthorized
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated via OAuth2");
    }
}
