package com.inven.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inven.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

   //ser findByUsernameAndPassword(String username, String password);
    
}
