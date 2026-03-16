package com.lunarvoid.authLunar.dtos.response;

import java.math.BigDecimal;

import com.lunarvoid.authLunar.entidades.Jogo;

public record JogoResponseDTO(Long id, String name, BigDecimal price) {
    
    public static JogoResponseDTO convertJogo(Jogo jogo){
        return new JogoResponseDTO(jogo.getId(), jogo.getName(), jogo.getPrice());
    }
}
