package com.spring.produto.exceptions;

public class ProdutoNomeMustBeNotRepeatedException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public ProdutoNomeMustBeNotRepeatedException(String msg) {
        super(msg);
    }
}
