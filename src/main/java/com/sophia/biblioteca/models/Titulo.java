package com.sophia.biblioteca.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "titulo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Titulo {
    //prazo, isbn, edicao, ano, area, autor
    @Id
    private String isbn;
    private Integer prazo;
    private String edicao;
    private Integer ano;
    private String area;

    @ManyToMany
    @JoinTable(name = "titulo_autor",
    joinColumns = @JoinColumn(name = "titulo_isbn", referencedColumnName = "isbn"),
    inverseJoinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id"))
    private List<Autor> autores;
}
