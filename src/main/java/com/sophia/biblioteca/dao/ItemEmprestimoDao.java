package com.sophia.biblioteca.dao;

import java.util.List;

import com.sophia.biblioteca.models.ItemEmprestimo;

public interface ItemEmprestimoDao {
    public ItemEmprestimo save(ItemEmprestimo itemEmprestimo);
    public ItemEmprestimo findById(Long id);
    public ItemEmprestimo findByLivroId(Long id);
    public List<ItemEmprestimo> findByEmprestimosId(Long id);
    public List<ItemEmprestimo> findAll();
}
