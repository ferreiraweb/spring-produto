package com.spring.produto.domain;

public class Fornecedores
{

    private int id;
    private String nome;

    private String Localidade;

    private String Telefone;

    public Fornecedores(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
