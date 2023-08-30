package com.spring.produto.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name ="fornecedor")
public class Fornecedor implements Serializable
{
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", length = 50, unique = true)
    private String nome;

    @Column(name = "localidade", length = 50)
    private String Localidade;

    @Column(name = "telefone", length = 30)
    private String Telefone;


    /* ------------------------------------------- */
    public Fornecedor() {
    }

    public Fornecedor(int id, String nome, String localidade, String telefone) {
        this.id = id;
        this.nome = nome;
        Localidade = localidade;
        Telefone = telefone;
    }
    /* ------------------------------------------- */


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

    public String getLocalidade() {
        return Localidade;
    }

    public void setLocalidade(String localidade) {
        Localidade = localidade;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(Localidade, that.Localidade) && Objects.equals(Telefone, that.Telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, Localidade, Telefone);
    }

    @Override
    public String toString() {
        return "Fornecedores{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", Localidade='" + Localidade + '\'' +
                ", Telefone='" + Telefone + '\'' +
                '}';
    }
}
