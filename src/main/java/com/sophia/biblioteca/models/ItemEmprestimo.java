package com.sophia.biblioteca.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "item_emprestimo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemEmprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemEmp;
    private Date dataDevolucao;
    private Date dataPrevista;
    @ManyToOne
    @JoinColumn(name = "id_emprestimo", referencedColumnName = "idEmprestimo")
    private Emprestimo emprestimo;
    @ManyToOne
    @JoinColumn(name = "id_livro", referencedColumnName = "idLivro")
    private Livro livro;
    
}
