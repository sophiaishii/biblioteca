package com.sophia.biblioteca.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sophia.biblioteca.dao.AutorDao;
import com.sophia.biblioteca.models.Autor;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AutorRepository implements AutorDao{
    @Autowired
    private EntityManager entityManager;
    @Override
    public Autor findById(Long id) {
        return entityManager.find(Autor.class, id);
    }

    @Override
    public List<Autor> findAll() {
        return entityManager.createQuery("SELECT a FROM autor a", Autor.class).getResultList();
    }

    @Transactional
    @Override
    public Autor save(Autor autor) {
        entityManager.persist(autor);
        return autor;
    }
}
