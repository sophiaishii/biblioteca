package com.sophia.biblioteca.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sophia.biblioteca.dao.EmprestimoDao;
import com.sophia.biblioteca.models.Emprestimo;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class EmprestimoRepository implements EmprestimoDao{
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Emprestimo save(Emprestimo emprestimo) {
        entityManager.persist(emprestimo);
        return emprestimo;
    }

    @Override
    public Emprestimo findById(Long id) {
        return entityManager.find(Emprestimo.class, id);
    }

    @Override
    public List<Emprestimo> findByMatricula(int matricula) {
        return entityManager.createQuery("SELECT e FROM emprestimo e WHERE e.aluno.matricula = :matricula", Emprestimo.class).setParameter("matricula", matricula).getResultList();
    }

    @Override
    public List<Emprestimo> findAll() {
        return entityManager.createQuery("SELECT e FROM emprestimo e", Emprestimo.class).getResultList();
    }
}
