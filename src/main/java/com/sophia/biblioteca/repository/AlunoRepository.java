package com.sophia.biblioteca.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sophia.biblioteca.dao.AlunoDao;
import com.sophia.biblioteca.models.Aluno;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AlunoRepository implements AlunoDao{
    @Autowired
    private EntityManager entityManager;
    @Override
    public Aluno findById(int matricula) {
        return entityManager.createQuery("SELECT a FROM aluno a WHERE a.matricula = :matricula", Aluno.class)
            .setParameter("matricula", matricula).getSingleResult();
    }

    @Override
    public List<Aluno> findAll() {
        return entityManager.createQuery("SELECT a FROM aluno a", Aluno.class).getResultList();
    }

    @Transactional
    @Override
    public Aluno save(Aluno aluno) {
        entityManager.persist(aluno);
        return aluno;
    }
}
