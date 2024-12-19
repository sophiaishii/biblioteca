package com.sophia.biblioteca.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "aluno")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Aluno {
    @Id
    private Integer matricula;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private String endereco;

    public Aluno(int matricula){
        this.matricula = matricula;
    }
}
