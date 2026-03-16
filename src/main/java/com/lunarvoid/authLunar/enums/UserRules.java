package com.lunarvoid.authLunar.enums;

public enum UserRules {
    BAIXO(1),
    MEDIO(2),
    ALTO(3);

    private Integer code;
    
    private UserRules(Integer code){
        this.code = code;
    }
    
    public Integer getCode(){
        return this.code;
    }

    public static UserRules valueOf(Integer code){
        for (UserRules e : UserRules.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Codigo de rule invalido");
    }
}
