package com.lunarvoid.authLunar.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunarvoid.authLunar.dtos.request.UserRequestDTO;
import com.lunarvoid.authLunar.dtos.response.TokenResponseDTO;
import com.lunarvoid.authLunar.services.LoguinService;

@RestController
@RequestMapping("/auth")
public class LoguinControler {
    @Autowired
    private LoguinService service;

    @PostMapping
    public ResponseEntity<TokenResponseDTO> loguin(@RequestBody @Validated UserRequestDTO obj){
        return ResponseEntity.ok().body(service.loguin(obj)); 
    }
}
