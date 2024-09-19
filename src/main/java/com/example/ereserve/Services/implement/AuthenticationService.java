package com.example.ereserve.Services.implement;

import com.example.ereserve.DTO.RegisterUserDto;
import com.example.ereserve.DTO.LoginUserDto;
import com.example.ereserve.Entity.RoleType;
import com.example.ereserve.Entity.ServiceReservation;
import com.example.ereserve.Entity.User;
import com.example.ereserve.Entity.Contract;
import com.example.ereserve.Repository.ContractRepository;
import com.example.ereserve.Repository.ServiceRepository;
import com.example.ereserve.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ContractRepository contractRepository;
    private final ServiceRepository serviceRepository;
    // Méthode pour l'inscription des utilisateurs
    public User signup(RegisterUserDto input) {
        // Vérifier si un utilisateur avec cet email existe déjà
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }

        // Création de l'utilisateur
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword())); // Encodage sécurisé du mot de passe

        // Détermination du rôle avec prise en charge des rôles insensibles à la casse
        RoleType role = input.getRole() != null ? RoleType.valueOf(input.getRole().toUpperCase()) : RoleType.CLIENT;
        user.setRole(role);

        // Sauvegarder l'utilisateur dans la base de données
        User savedUser = userRepository.save(user);

        // Gestion des rôles après la sauvegarde de l'utilisateur
        if (role == RoleType.ADMIN) {
            // Créer et sauvegarder le contrat
            Contract contract = new Contract();
            contract.setStart(input.getContractStart());
            contract.setEndDate(input.getContractEnd());
            contractRepository.save(contract);

            // Associer le contrat à l'utilisateur
            savedUser.setContract(contract);
            userRepository.save(savedUser);  // Mettre à jour l'utilisateur avec le contrat
            // Créer et sauvegarder un ServiceReservation pour l'utilisateur ADMIN
            ServiceReservation serviceReservation = new ServiceReservation();
            serviceReservation.setUser(savedUser);  // Associer l'utilisateur au ServiceReservation
            serviceRepository.save(serviceReservation);
        }

        // Aucun contrat ou ServiceReservation pour SUPERADMIN ou CLIENT
        if (role == RoleType.SUPERADMIN || role == RoleType.CLIENT) {
            savedUser.setContract(null);  // Aucun contrat pour SUPERADMIN ou CLIENT
            userRepository.save(savedUser);  // Mettre à jour l'utilisateur si nécessaire
        }

        return savedUser;
    }

    // Méthode pour gérer l'authentification des utilisateurs
    public User authenticate(LoginUserDto input) {
        // Authentifier l'utilisateur avec son email et mot de passe
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        // Retourner l'utilisateur authentifié
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // Méthode pour gérer l'authentification et l'enregistrement des utilisateurs via OAuth2
    public User findOrRegisterOAuth2User(String email) {
        // Vérifie si un utilisateur avec cet email existe déjà
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            // Si l'utilisateur existe déjà, le retourne
            return existingUser.get();
        } else {
            // Si l'utilisateur n'existe pas, en crée un nouveau avec le rôle CLIENT par défaut
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setRole(RoleType.CLIENT); // Attribue un rôle par défaut
            // Sauvegarde le nouvel utilisateur dans la base de données
            return userRepository.save(newUser);
        }
    }
}
