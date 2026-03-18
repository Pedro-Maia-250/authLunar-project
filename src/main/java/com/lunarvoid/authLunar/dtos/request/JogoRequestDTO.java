package com.lunarvoid.authLunar.dtos.request;

import java.math.BigDecimal;

import com.lunarvoid.authLunar.entidades.Jogo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogoRequestDTO(@NotBlank String name, @NotNull BigDecimal price) {
    
    public Jogo toEntity(){
        return new Jogo(name(), price());
    }

    public void updateJogo(Jogo obj){
        obj.setName(name());
        obj.setPrice(price());
    }
}
