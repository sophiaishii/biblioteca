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

import com.sophia.biblioteca.models.ItemDevolucao;
import com.sophia.biblioteca.repository.ItemDevolucaoRepository;

@RestController
@RequestMapping("/itemdevolucao")
public class ItemDevolucaoController {
    @Autowired
    private ItemDevolucaoRepository repository;

    @PostMapping
    public ResponseEntity<ItemDevolucao> cadastrarItemDevolucao(@RequestBody ItemDevolucao itemDevolucao) {
        return ResponseEntity.ok(repository.save(itemDevolucao));
    }

    @GetMapping
    public ResponseEntity<List<ItemDevolucao>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDevolucao> buscarPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    
}
