package com.sophia.biblioteca.dao;

import java.util.List;

import com.sophia.biblioteca.models.Livro;

public interface LivroDao {
    Livro save(Livro livro);
    Livro findByIsbn(String isbn);
    Livro findById(Long id);
    Livro update(Livro livro);
    List<Livro> findAll();
}
