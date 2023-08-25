package com.spring.produto.exceptions;

public class CategoriaNotFoundException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public CategoriaNotFoundException(String msg) {
        super(msg);
    }
}
