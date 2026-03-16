package com.lunarvoid.authLunar.dtos.request;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lunarvoid.authLunar.entidades.User;
import com.lunarvoid.authLunar.enums.UserRules;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(@NotBlank String username, @NotBlank String password) {
    
    public void updateUser(User obj){
        obj.setUsername(username());
        obj.setPassword(emcryptedPassword());
    }

    public void updateUser(User obj, UserRules rule){
        obj.setUsername(username());
        obj.setPassword(emcryptedPassword());
        obj.setRule(rule);
    }

    private String emcryptedPassword(){
        return new BCryptPasswordEncoder().encode(password());
    }
}
