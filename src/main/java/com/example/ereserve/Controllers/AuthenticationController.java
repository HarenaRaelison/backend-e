package com.example.ereserve.Controllers;

import com.example.ereserve.DTO.LoginResponse;
import com.example.ereserve.DTO.LoginUserDto;
import com.example.ereserve.DTO.RegisterUserDto;
import com.example.ereserve.Entity.User;
import com.example.ereserve.Services.implement.AuthenticationService;
import com.example.ereserve.Services.implement.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody LoginUserDto loginUserDto,
            HttpServletResponse response
    ) {
        // Authentifie l'utilisateur
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        // Génére un token JWT pour l'utilisateur authentifié
        String jwtToken = jwtService.generateToken(authenticatedUser);

        // Crée un LoginResponse avec le token et le temps d'expiration
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setUserId(authenticatedUser.getId());
        loginResponse.setRoleUser(String.valueOf(authenticatedUser.getRole()));
        // Crée un cookie avec le token JWT

        // Ajoute le cookie à la réponse


        // Retourne la réponse avec le token et le temps d'expiration
        return ResponseEntity.ok(loginResponse);
      }
}
