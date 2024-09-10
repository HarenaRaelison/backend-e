package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User updateUser(Long idUser,User user);
    String deleteUpdate(Long idUser);
    List<User> findAllUsers();
}
