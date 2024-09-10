package com.example.ereserve.Services.implement;

import com.example.ereserve.DTO.RegisterUserDto;
import com.example.ereserve.DTO.LoginUserDto;
import com.example.ereserve.Entity.User;
import com.example.ereserve.Entity.RoleType;
import com.example.ereserve.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to handle user registration (signup)
    public User signup(RegisterUserDto input) {
        // Check if the user with the provided email already exists
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Create a new user instance and populate it with data from the DTO
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword())); // Secure password storage

        // Set the user's role or use CLIENT as the default
        user.setRole(input.getRole() != null ? input.getRole() : RoleType.CLIENT);

        // Handle contract information if provided, otherwise leave it as null
        user.setContract(input.getContract());

        // Set account defaults (enabled, non-locked, etc.)


        // Save the user to the database
        return userRepository.save(user);
    }

    // Method to handle user login (authentication)
    public User authenticate(LoginUserDto input) {
        // Use the AuthenticationManager to authenticate the user using their email and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        // Retrieve the authenticated user from the database
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
