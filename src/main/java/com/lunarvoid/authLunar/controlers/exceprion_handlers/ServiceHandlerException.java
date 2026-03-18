package com.lunarvoid.authLunar.controlers.exceprion_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lunarvoid.authLunar.exceptions.ExceptionResponseDTO;
import com.lunarvoid.authLunar.services.exceptions.DatabaseException;
import com.lunarvoid.authLunar.services.exceptions.LoguinExeption;
import com.lunarvoid.authLunar.services.exceptions.ResourceNotFoundException;
import com.lunarvoid.authLunar.services.exceptions.TokenException;
import com.lunarvoid.authLunar.services.exceptions.UserException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ServiceHandlerException {
    
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ExceptionResponseDTO> databaseException(DatabaseException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponseDTO response = new ExceptionResponseDTO(status.value(), "Database error", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> resorceNotFound(ResourceNotFoundException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseDTO response = new ExceptionResponseDTO(status.value(), "Resource not found", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> usernameNotFound(UsernameNotFoundException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseDTO response = new ExceptionResponseDTO(status.value(), "Resource not found", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionResponseDTO> usernameIndisponivel(UserException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionResponseDTO response = new ExceptionResponseDTO(status.value(), "User conflicted error", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ExceptionResponseDTO> tokenIndisponivel(TokenException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionResponseDTO response = new ExceptionResponseDTO(status.value(), "Token conflicted error", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(LoguinExeption.class)
    public ResponseEntity<ExceptionResponseDTO> loguinError(LoguinExeption exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.LOCKED;
        ExceptionResponseDTO response = new ExceptionResponseDTO(status.value(), "Not logued", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(response);
    }

}
