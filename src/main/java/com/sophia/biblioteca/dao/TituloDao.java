package com.sophia.biblioteca.dao;

import java.util.List;

import com.sophia.biblioteca.models.Titulo;

public interface TituloDao {
    Titulo save(Titulo titulo);
    Titulo findByIsbn(String isbn);
    List<Titulo> findAll();
}
