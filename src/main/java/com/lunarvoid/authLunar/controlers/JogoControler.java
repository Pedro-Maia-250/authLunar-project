package com.lunarvoid.authLunar.controlers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lunarvoid.authLunar.dtos.request.JogoRequestDTO;
import com.lunarvoid.authLunar.dtos.response.JogoResponseDTO;
import com.lunarvoid.authLunar.services.JogoService;


@RestController
@RequestMapping(value = "/jogos")
public class JogoControler {
    
    @Autowired
    private JogoService service;

    @GetMapping
    public ResponseEntity<List<JogoResponseDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<JogoResponseDTO> insert(@RequestBody @Validated JogoRequestDTO obj){
        JogoResponseDTO response = service.insert(obj);
        URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogoResponseDTO> update(@PathVariable Long id, @RequestBody @Validated JogoRequestDTO obj){
        return ResponseEntity.ok().body(service.update(id, obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.notFound().build();
    }
    
}
