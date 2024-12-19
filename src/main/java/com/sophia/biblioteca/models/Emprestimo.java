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

@Entity(name = "emprestimo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmprestimo;
    private Date dataEmprestimo;
    private Date dataPrevista;
    private double multa;
    // private Date dataDevolucao;
    @ManyToOne
    @JoinColumn(name = "aluno_matricula", referencedColumnName = "matricula")
    private Aluno aluno;
    // @OneToMany
    // @JoinColumn(name = "id_itememprestimo")
    // private List<ItemEmprestimo> itemEmprestimo;

    public Emprestimo(Date dataEmprestimo, Date dataPrevista, double multa, Aluno aluno) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.multa = multa;
        this.aluno = aluno;
    }

}
