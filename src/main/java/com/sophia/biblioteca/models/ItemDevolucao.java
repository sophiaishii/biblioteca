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

@Entity(name = "item_devolucao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDevolucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemDev;
    private Date dataDevolucao;
    private Integer diasAtraso;
    private double multa;
    private double valor;
    @ManyToOne
    @JoinColumn(name = "id_devolucao", referencedColumnName = "idDevolucao")
    private Devolucao devolucao;
    @ManyToOne
    @JoinColumn(name = "id_livro", referencedColumnName = "idLivro")
    private Livro livro;

    
}
