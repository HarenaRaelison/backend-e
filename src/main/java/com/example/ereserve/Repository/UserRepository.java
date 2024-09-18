package com.example.ereserve.Repository;

import com.example.ereserve.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String email);
}
