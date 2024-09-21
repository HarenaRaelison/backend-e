package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.RoleType;
import com.example.ereserve.Entity.User;
import com.example.ereserve.Repository.UserRepository;
import com.example.ereserve.Services.Interface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long idUser, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(idUser);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setContract(updatedUser.getContract());

            return userRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'est pas trouvé (par exemple, lancer une exception)
            throw new RuntimeException("Utilisateur non trouvé");
        }
    }

    @Override
    public User registerUser(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        String fullName = oAuth2User.getAttribute("name");

        // Vérifie si l'utilisateur existe déjà dans la base de données
        Optional<User> existingUserOptional = userRepository.findByEmail(email);
        if (existingUserOptional.isPresent()) {
            return existingUserOptional.get(); // Retourne l'utilisateur existant
        }

        // Si l'utilisateur n'existe pas, créer un nouvel utilisateur
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFullName(fullName);
        newUser.setRole(RoleType.CLIENT);  // Assigne le rôle CLIENT par défaut
        return userRepository.save(newUser);
    }

    @Override
    public User findById(Long idUser) {
        return userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public String deleteUpdate(Long idUser) {
        // Implement delete logic if needed
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
