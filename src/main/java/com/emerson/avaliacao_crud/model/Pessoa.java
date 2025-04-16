package com.emerson.avaliacao_crud.model;

import jakarta.persistence.*;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private Integer idade;

    @ManyToOne
    @JoinColumn(name = "trabalho_id")
    private Trabalho trabalho;

    public Pessoa() {}

    public Long getId() {
        return id;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public Trabalho getTrabalho() {
        return trabalho;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }
}
