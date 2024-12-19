package com.sophia.biblioteca.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sophia.biblioteca.dao.LivroDao;
import com.sophia.biblioteca.models.Livro;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class LivroRepository implements LivroDao {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Livro save(Livro livro) {
        entityManager.persist(livro);
        return livro;
    }

    @Override
    public Livro findByIsbn(String isbn) {
        return entityManager.createQuery("SELECT a FROM livro a WHERE a.titulo.isbn = :isbn", Livro.class)
            .setParameter("isbn", isbn).getSingleResult();
    }

    @Override
    public Livro findById(Long id) {
        return entityManager.find(Livro.class, id);
    }

    @Override
    public List<Livro> findAll() {
        return entityManager.createQuery("SELECT a FROM livro a", Livro.class).getResultList();
    }

    @Transactional
    @Override
    public Livro update(Livro livro) {
        return entityManager.merge(livro);
    }

}
