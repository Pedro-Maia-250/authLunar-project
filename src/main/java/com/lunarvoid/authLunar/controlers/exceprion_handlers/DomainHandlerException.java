package com.lunarvoid.authLunar.controlers.exceprion_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lunarvoid.authLunar.exceptions.ExceptionResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DomainHandlerException {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDTO> databaseException(IllegalArgumentException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponseDTO response = new ExceptionResponseDTO(status.value(), "Erro de servidor", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(response);
    }

}
