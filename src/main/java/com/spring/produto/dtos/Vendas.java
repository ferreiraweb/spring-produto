package com.spring.produto.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Vendas {
    private int id;
    private LocalDateTime data;
    private BigDecimal valor;

    public Vendas(LocalDateTime data, BigDecimal valor) {
        this.data = data;
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
