package com.sophia.biblioteca.dao;

import java.util.List;

import com.sophia.biblioteca.models.Aluno;

public interface AlunoDao {
    Aluno save(Aluno aluno);
    Aluno findById(int matricula);
    List<Aluno> findAll();
}
