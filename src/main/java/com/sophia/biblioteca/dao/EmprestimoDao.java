package com.sophia.biblioteca.dao;

import java.util.List;

import com.sophia.biblioteca.models.Emprestimo;

public interface EmprestimoDao {
    Emprestimo save(Emprestimo emprestimo);
    Emprestimo findById(Long id);
    List<Emprestimo> findByMatricula(int matricula);
    List<Emprestimo> findAll();
}
