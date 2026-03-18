package com.lunarvoid.authLunar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lunarvoid.authLunar.dtos.request.UserRequestDTO;
import com.lunarvoid.authLunar.dtos.response.UserResponseDTO;
import com.lunarvoid.authLunar.entidades.User;
import com.lunarvoid.authLunar.enums.UserRules;
import com.lunarvoid.authLunar.repositories.UserRepository;
import com.lunarvoid.authLunar.services.exceptions.DatabaseException;
import com.lunarvoid.authLunar.services.exceptions.ResourceNotFoundException;
import com.lunarvoid.authLunar.services.exceptions.UserException;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public List<UserResponseDTO> findAll(){
        return repository.findAll().stream().map((j) -> UserResponseDTO.convertUser(j)).toList();
    }

    public UserResponseDTO findById(Long id){
        return UserResponseDTO.convertUser(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado" + id)));
    }

    public UserResponseDTO insert(UserRequestDTO obj){
        if(!repository.findByUsername(obj.username()).isPresent()){
            String senha = encoder.encode(obj.password());
            return UserResponseDTO.convertUser(repository.save(new User(obj.username(), senha, UserRules.BAIXO)));
        }else{
            throw new UserException("Nome de usuario indisponivel");
        }
    }

    public UserResponseDTO insertWithRule(UserRequestDTO obj, UserRules Rule){
        if(!repository.findByUsername(obj.username()).isPresent()){
            String senha = encoder.encode(obj.password());
            return UserResponseDTO.convertUser(repository.save(new User(obj.username(), senha, Rule)));
        }else{
            throw new UserException("Nome de usuario indisponivel");
        }
    }

    public UserResponseDTO update(Long id, UserRequestDTO obj){
        User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado" + id));
        obj.updateUser(entity);
        return UserResponseDTO.convertUser(entity);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Falha ao deletar o Usuario id:" + id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("não foi possivel encontrar esse usuario"));
    }
}
