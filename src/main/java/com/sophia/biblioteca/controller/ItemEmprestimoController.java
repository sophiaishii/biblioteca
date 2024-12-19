package com.sophia.biblioteca.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophia.biblioteca.models.ItemEmprestimo;
import com.sophia.biblioteca.repository.ItemEmprestimoRepository;

@RestController
@RequestMapping("/itememprestimo")
public class ItemEmprestimoController {
    @Autowired
    private ItemEmprestimoRepository repository;

    @PostMapping
    public ResponseEntity<ItemEmprestimo> cadastrarItemEmprestimo(@RequestBody ItemEmprestimo itemEmprestimo) {
        return ResponseEntity.ok(repository.save(itemEmprestimo));
    }

    @GetMapping
    public ResponseEntity<List<ItemEmprestimo>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEmprestimo> buscarPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    // Metodos de negocio
    public Date calculaDataDevolucaoItem(Date data, ItemEmprestimo item) {
        Integer prazo = item.getLivro().getTitulo().getPrazo();
        Date dataDevolucao = data;
        Calendar c = Calendar.getInstance();
        c.setTime(dataDevolucao);
        c.add(Calendar.DATE, prazo);
        dataDevolucao = c.getTime();
        item.setDataDevolucao(dataDevolucao);
        return dataDevolucao;
    }
}
