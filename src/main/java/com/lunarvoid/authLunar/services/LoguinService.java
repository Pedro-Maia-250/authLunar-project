package com.lunarvoid.authLunar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.lunarvoid.authLunar.dtos.request.UserRequestDTO;
import com.lunarvoid.authLunar.dtos.response.TokenResponseDTO;
import com.lunarvoid.authLunar.entidades.User;

@Service
public class LoguinService {
    
    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenService service;

    public TokenResponseDTO loguin(UserRequestDTO obj){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(obj.username(), obj.password());
        User user = (User) manager.authenticate(token).getPrincipal();
        return new TokenResponseDTO(service.gerarToken(user));
    }
}
