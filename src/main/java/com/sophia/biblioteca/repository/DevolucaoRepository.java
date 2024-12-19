package com.sophia.biblioteca.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sophia.biblioteca.dao.DevolucaoDao;
import com.sophia.biblioteca.models.Devolucao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class DevolucaoRepository implements DevolucaoDao{
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Devolucao save(Devolucao devolucao) {
        entityManager.persist(devolucao);
        return devolucao;
    }

    @Override
    public Devolucao findById(Long id) {
        return entityManager.find(Devolucao.class, id);
    }

    @Override
    public Devolucao findByMatricula(int matricula) {
        return entityManager.createQuery("SELECT d FROM devolucao d WHERE d.emprestimo.aluno.matricula = :matricula", Devolucao.class).setParameter("matricula", matricula).getSingleResult();
    }

    @Override
    public List<Devolucao> findAll() {
        return entityManager.createQuery("SELECT d FROM devolucao d", Devolucao.class).getResultList();
    }
}
