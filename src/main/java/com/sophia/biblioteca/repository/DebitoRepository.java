package com.sophia.biblioteca.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sophia.biblioteca.dao.DebitoDao;
import com.sophia.biblioteca.models.Debito;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class DebitoRepository implements DebitoDao{
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Debito> findByMatricula(int matricula) {
        return entityManager.createQuery("SELECT a FROM debito a WHERE a.aluno.matricula = :matricula", Debito.class).setParameter("matricula", matricula).getResultList();
    }

    @Override
    public Debito findById(Long id) {
        return entityManager.find(Debito.class, id);
    }

    @Override
    public List<Debito> findAll() {
        return entityManager.createQuery("SELECT a FROM debito a", Debito.class).getResultList();
    }

    @Transactional
    @Override
    public Debito save(Debito debito) {
        entityManager.persist(debito);
        return debito;
    }
}
