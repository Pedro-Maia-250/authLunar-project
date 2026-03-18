package com.lunarvoid.authLunar.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lunarvoid.authLunar.entidades.User;
import com.lunarvoid.authLunar.services.exceptions.TokenException;

@Service
public class TokenService {
    
    @Value("${token.secret}")
    private String secret;

    public String gerarToken(User user){
        try {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
            .withIssuer("AUTH-LUNAR")
            .withSubject(user.getUsername())
            .withExpiresAt(Instant.now().plusSeconds(7200))
            .sign(algorithm);
        return token;
        } catch (JWTCreationException exception){
            throw new TokenException("Falha ao gerar Token");
        }
    }

    public String verifyTokenAndGetUsername(String token){
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("AUTH-LUNAR").build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException e){
            throw new TokenException("Token invalido - " + e.getMessage());
        }
    }

}
