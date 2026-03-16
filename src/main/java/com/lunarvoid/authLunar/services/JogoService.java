package com.lunarvoid.authLunar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lunarvoid.authLunar.dtos.request.JogoRequestDTO;
import com.lunarvoid.authLunar.dtos.response.JogoResponseDTO;
import com.lunarvoid.authLunar.entidades.Jogo;
import com.lunarvoid.authLunar.repositories.JogoRepository;
import com.lunarvoid.authLunar.services.exceptions.DatabaseException;
import com.lunarvoid.authLunar.services.exceptions.ResourceNotFoundException;

@Service
public class JogoService {
    
    @Autowired
    private JogoRepository repository;

    public List<JogoResponseDTO> findAll(){
        return repository.findAll().stream().map((j) -> JogoResponseDTO.convertJogo(j)).toList();
    }

    public JogoResponseDTO findById(Long id){
        return JogoResponseDTO.convertJogo(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado" + id)));
    }

    public JogoResponseDTO insert(JogoRequestDTO obj){
        return JogoResponseDTO.convertJogo(repository.save(obj.toEntity()));
    }

    public JogoResponseDTO update(Long id, JogoRequestDTO obj){
        Jogo entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado" + id));
        obj.updateJogo(entity);
        return JogoResponseDTO.convertJogo(entity);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Falha ao deletar o recurso id:" + id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}
