package com.sophia.biblioteca.dao;


import java.util.List;

import com.sophia.biblioteca.models.Debito;

public interface DebitoDao {
    Debito save(Debito debito);
    Debito findById(Long id);
    List<Debito> findByMatricula(int matricula);
    List<Debito> findAll();
}
