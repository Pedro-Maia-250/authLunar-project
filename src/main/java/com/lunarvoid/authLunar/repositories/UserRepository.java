package com.lunarvoid.authLunar.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.lunarvoid.authLunar.entidades.User;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<UserDetails> findByUsername(String username);
    
} 
