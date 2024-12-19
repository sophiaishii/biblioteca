package com.sophia.biblioteca.dao;

import java.util.List;

import com.sophia.biblioteca.models.Autor;

public interface AutorDao {
    Autor save(Autor autor);
    Autor findById(Long id);
    List<Autor> findAll();
}
