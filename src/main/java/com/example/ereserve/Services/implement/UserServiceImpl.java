package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.User;
import com.example.ereserve.Repository.UserRepository;
import com.example.ereserve.Services.Interface.UserService;
import lombok.AllArgsConstructor;
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
            // Handle the case where the user is not found (return null or throw an exception)
            return null;
        }
    }

    @Override
    public String deleteUpdate(Long idUser) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


}
