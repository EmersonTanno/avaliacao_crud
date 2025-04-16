package com.emerson.avaliacao_crud.dto;

public class PessoaDTO {
    private long id;
    private String cpf;
    private Integer idade;

    public PessoaDTO(){}

    public PessoaDTO(long id, String cpf, Integer idade){
        this.id = id;
        this.cpf = cpf;
        this.idade = idade;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }
}
