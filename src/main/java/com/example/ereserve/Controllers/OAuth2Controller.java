package com.example.ereserve.Controllers;

import com.example.ereserve.Entity.RoleType;
import com.example.ereserve.Entity.User;
import com.example.ereserve.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class OAuth2Controller {

    private final UserRepository userRepository;

    @GetMapping("/auth/success")
    public RedirectView handleOAuth2Success(OAuth2AuthenticationToken authentication) {
        OAuth2User oAuth2User = authentication.getPrincipal();

        // Récupérer les informations de l'utilisateur à partir de Google OAuth2
        String email = oAuth2User.getAttribute("email");
        String fullName = oAuth2User.getAttribute("name");

        // Vérifier si l'utilisateur existe déjà dans la base de données
        Optional<User> existingUser = (userRepository.findByEmail(email));
        if (existingUser.isEmpty()) {
            // Créer un nouvel utilisateur avec le rôle CLIENT
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setFullName(fullName);
            newUser.setPassword("0000");
            newUser.setRole(RoleType.CLIENT);
            userRepository.save(newUser);
        }

        // Rediriger vers l'URL du frontend après enregistrement
        return new RedirectView("http://localhost:5173/events");
    }
}
