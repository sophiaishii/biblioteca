package com.sophia.biblioteca.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "autor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String titulacao;
    // @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    // private List<Titulo> titulo;
    @JsonIgnore
    @ManyToMany(mappedBy = "autores")
    private List<Titulo> titulos;
}
