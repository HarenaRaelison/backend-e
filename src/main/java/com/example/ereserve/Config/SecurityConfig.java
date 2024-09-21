package com.example.ereserve.Config;

import com.example.ereserve.Services.implement.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**","/oauth2/**","/login","/api/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(Customizer.withDefaults())
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/auth/success", true) // Remplacez par l'URL de votre page frontend
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL pour déconnecter l'utilisateur
                        .invalidateHttpSession(true) // Invalider la session après déconnexion
                        .deleteCookies("JSESSIONID") // Supprimer les cookies de session
                        .logoutSuccessUrl("/login") // Redirection après la déconnexion
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);

        return http.build();
    }
}



