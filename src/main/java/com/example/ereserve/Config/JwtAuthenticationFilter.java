package com.example.ereserve.Config;

import com.example.ereserve.Services.implement.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * Filtre d'authentification JWT.
 * Ce filtre vérifie le token JWT dans l'en-tête de la requête et authentifie l'utilisateur.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Constructeur pour le filtre d'authentification JWT.
     *
     * @param jwtService               Service de gestion des JWT.
     * @param userDetailsService       Service de détails de l'utilisateur.
     * @param handlerExceptionResolver Résolveur d'exceptions pour gérer les erreurs.
     */
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsService userDetailsService,
            HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    /**
     * Méthode principale pour le filtrage des requêtes.
     *
     * @param request  Requête HTTP.
     * @param response Réponse HTTP.
     * @param filterChain Chaîne de filtres.
     * @throws ServletException En cas d'erreur lors du traitement de la requête.
     * @throws IOException      En cas d'erreur d'entrée/sortie.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Récupération du token JWT depuis l'en-tête de la requête
        final String authHeader = request.getHeader("Authorization");

        // Vérification si l'en-tête contient un token Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraction du token JWT de l'en-tête
        final String token = authHeader.substring(7); // Supprime "Bearer " de l'en-tête

        try {
            // Extraction des informations d'utilisateur depuis le token
            final String username = jwtService.extractUsername(token);

            // Vérification si l'utilisateur est déjà authentifié dans le contexte de sécurité
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Validation du token
                if (jwtService.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    // Configuration des détails de l'authentification
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Définir l'authentification dans le contexte de sécurité
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            // Gestion des exceptions et erreurs
            handlerExceptionResolver.resolveException(request, response, null, e);
        }

        // Passe la requête au prochain filtre dans la chaîne
        filterChain.doFilter(request, response);
    }
}
