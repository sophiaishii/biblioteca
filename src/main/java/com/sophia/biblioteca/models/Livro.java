package com.sophia.biblioteca.models;

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

@Entity(name = "livro")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;
    private Boolean disponivel;
    private Boolean exemplarBiblioteca;

    @ManyToOne
    @JoinColumn(name = "titulo_isbn", referencedColumnName = "isbn")
    private Titulo titulo;

    //Construtor que recebe isbn
    // public Livro(String isbn, Boolean disponivel, Boolean exemplarBiblioteca) {
    //     TituloController tituloController = new TituloController();
    //     this.titulo = tituloController.encontrarPorIsbn(isbn).getBody();
    //     this.disponivel = disponivel;
    //     this.exemplarBiblioteca = exemplarBiblioteca;
    // }

}
