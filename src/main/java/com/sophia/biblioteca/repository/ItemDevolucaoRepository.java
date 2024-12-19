package com.sophia.biblioteca.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sophia.biblioteca.dao.ItemDevolucaoDao;
import com.sophia.biblioteca.models.ItemDevolucao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class ItemDevolucaoRepository implements ItemDevolucaoDao {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public ItemDevolucao save(ItemDevolucao itemDevolucao) {
        entityManager.persist(itemDevolucao);
        return itemDevolucao;
    }

    @Override
    public ItemDevolucao findById(Long id) {
        return entityManager.find(ItemDevolucao.class, id);
    }

    @Override
    public ItemDevolucao findByLivroId(Long id) {
        return entityManager.createQuery("SELECT a FROM item_devolucao a WHERE a.livro.id = :id", ItemDevolucao.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<ItemDevolucao> findAll() {
        return entityManager.createQuery("SELECT a FROM item_devolucao a", ItemDevolucao.class).getResultList();
    }
}
