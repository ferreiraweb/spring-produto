package com.spring.produto.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String nome;
    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Date validade;

    @ManyToOne(optional = false) /* Muitos produtos para uma Categoria */
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;


    /* constructors */

    public Produto() {}

    public Produto(String nome, BigDecimal valor, Date validade, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.validade = validade;
        this.categoria = categoria;
    }

    /* getters and setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /* hashCode,equals, toString */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(valor, produto.valor) && Objects.equals(validade, produto.validade) && Objects.equals(categoria, produto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valor, validade, categoria);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", validade=" + validade +
                ", categoria=" + categoria +
                '}';
    }
}
