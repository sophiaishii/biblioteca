package com.sophia.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophia.biblioteca.models.Debito;
import com.sophia.biblioteca.repository.DebitoRepository;

@RestController
@RequestMapping("/debito")
public class DebitoController {

    //Requisições http
    @Autowired
    private DebitoRepository repository;
    @PostMapping
    public ResponseEntity<Debito> cadastrarDebito(@RequestBody Debito debito) {
        return ResponseEntity.ok(repository.save(debito));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Debito> listarPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping("aluno/{matricula}")
    public ResponseEntity<List<Debito>> listarPorMatricula(@PathVariable(value = "matricula") int matricula) {
        return ResponseEntity.ok(repository.findByMatricula(matricula));
    }
    @GetMapping
    public ResponseEntity<List<Debito>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    //Metodos de negocio
    public boolean verificarDebito(int matricula){
        List<Debito> debito = repository.findByMatricula(matricula);
        if(debito.size()>0){
            return false;
        }
        return true;
    }

}
