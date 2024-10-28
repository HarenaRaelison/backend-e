package com.example.ereserve.Config;

import com.example.ereserve.Services.implement.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
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
    private final JwtService jwtService; // Ensure this is used in your implementation if needed

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF since you're using JWT (stateless)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/oauth2/**", "/login", "/api/**").permitAll()  // Allow public routes
                        .anyRequest().permitAll() // Require authentication for all other routes
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("http://localhost:5173/transports")  // Redirect to this URL after successful login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // URL to log out the user
                        .invalidateHttpSession(true)  // Invalidate the session after logout
                        .deleteCookies("JSESSIONID")  // Remove session cookies
                        .logoutSuccessUrl("/login")  // Redirect to the login page after logout
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // Add JWT filter before UsernamePasswordAuthenticationFilter
                .authenticationProvider(authenticationProvider);  // Custom authentication provider for JWT

        return http.build();
    }
}
