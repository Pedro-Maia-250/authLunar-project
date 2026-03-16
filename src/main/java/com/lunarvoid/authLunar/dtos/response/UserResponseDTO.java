package com.lunarvoid.authLunar.dtos.response;

import com.lunarvoid.authLunar.entidades.User;

public record UserResponseDTO(Long id, String username) {
    
    public static UserResponseDTO convertUser(User User){
        return new UserResponseDTO(User.getId(), User.getUsername());
    }
}
