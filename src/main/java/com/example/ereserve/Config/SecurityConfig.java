package com.example.ereserve.Config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF protection for APIs (especially stateless JWT-based)
                .authorizeHttpRequests(auth -> {
                    // Allow public access to signup, login, and OAuth2 routes
                    auth.requestMatchers("/auth/signup", "/auth/login", "/oauth2/**").permitAll();
                    // Protect all other routes and require authentication
                    auth.anyRequest().authenticated();
                })
                // Set session management to stateless for JWT-based authentication
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Configure OAuth2 login handling
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login")  // Set a custom OAuth2 login page
                        .permitAll()  // Allow public access to the login page
                        .successHandler((request, response, authentication) -> {
                            // Redirection after successful login
                            response.sendRedirect("/");
                        })
                )
                // Attach the JWT authentication filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // Attach the custom authentication provider
                .authenticationProvider(authenticationProvider);

        return http.build();
    }
}
