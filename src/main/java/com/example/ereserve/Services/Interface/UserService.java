package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User updateUser(Long idUser,User user);

    User registerUser(OAuth2User oAuth2User);
    User findById(Long idUser);
    String deleteUpdate(Long idUser);
    List<User> findAllUsers();
}
