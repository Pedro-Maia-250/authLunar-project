package com.lunarvoid.authLunar.services.exceptions;

public class TokenException extends RuntimeException {
    public TokenException(String msg){
        super(msg);
    }
}
