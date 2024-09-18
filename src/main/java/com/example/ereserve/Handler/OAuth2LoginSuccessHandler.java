package com.example.ereserve.Handler;

import com.example.ereserve.Entity.User;
import com.example.ereserve.Services.implement.AuthenticationService;
import com.example.ereserve.Services.implement.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final AuthenticationService authenticationService; // Votre service d'authentification


    private final JwtService jwtService; // Service JWT pour générer des tokens

    public OAuth2LoginSuccessHandler(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        // Vérifier ou enregistrer l'utilisateur
        User user = authenticationService.findOrRegisterOAuth2User(email);

        // Générer le JWT
        String jwtToken = jwtService.generateToken(user);

        // Créer un cookie JWT
        Cookie jwtCookie = new Cookie("jwt", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setMaxAge((int) jwtService.getExpirationTime() / 1000);
        jwtCookie.setPath("/");

        // Ajouter le cookie à la réponse
        response.addCookie(jwtCookie);

        // Redirection vers le tableau de bord utilisateur
        response.sendRedirect("http://localhost:5173/events");
    }
}
