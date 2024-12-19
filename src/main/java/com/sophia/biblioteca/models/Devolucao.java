package com.sophia.biblioteca.models;

import java.util.Date;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "devolucao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Devolucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDevolucao;
    private Date dataDevolucao;
    private double multa;
    private boolean atraso;
    private double valorTotal;
    @OneToOne
    @Nullable
    private Emprestimo emprestimo;
}
